### 项目介绍
基于Spring Boot + Elastic Stack (+ Vue 3)的一站式XX信息聚合搜索平台。用户可在同一页面集中搜索出不同来源、不同类型的内容（建议具体列举具体的数据类别，比如文章、图片、用户、专栏、视频等)，提升搜索体验。

### 技术选型

* Spring Boot 2.7框架+ springboot-init脚手架
* MySQL数据库(8.x版本)
* Elastic Stack
  * Elasticsearch搜索引擎（重点)
  * Logstash 数据管道
  * Kibana数据可视化
* 数据抓取(jsoup、HttpClient爬虫)
  * 离线
  * 实时
* 设计模式
  * 门面模式
  * 适配器模式
  * 注册器模式
* 数据同步(4种同步方式)
  * 定时
  * 编程导航知识星球
  * 双写
  * Logstash。Canal
* JMeter 压力测试

### 项目亮点
1. 基于自己二次开发的Spring Boot初始化模板＋MyBatis X插件，快速生成基本数据源的增删改查。
2. 使用HttpClient请求离线获取外部网站的文章，并使用Hutool的JSONUtil解析和预处理文章，最终入库。
3. 使用jsoup实时请求bing搜索接口获取图片，并使用CSS Selector语法解析和预处理图片信息，最终返回给前端。
4. 使用门面模式在后端对各类数据源的搜索结果进行聚合，统一返回给前端。减少了前端请求次数(N次到1次)以及前端开发复杂度。并通过CompletableFuture并发搜索各数据源进一步提升搜索接口性能。
5. 通过定义数据源接口实现统一的数据源接入标准,当新数据源要接入时，只需使用适配器模式以适配数据源接口，无须修改原有代码，提高了系统的可扩展性。
6. 为减少代码的圈复杂度，使用注册器模式代替 if else来管理多个数据源对象，调用方可根据名称轻松获取对象。
7. 为解决文章搜不出的问题，自主搭建Elasticsearch来代替MySQL的模糊查询，并通过为索引绑定ik分词器实现了更灵活的分词搜索。
8. 构建ES文章索引时，采用动静分离的策略，只在ES中存储要检索的、修改不频繁的字段，从而减少了ES数据更新和同步的成本，保证数据的一致性。
9. 使用Spring Scheduler定时同步近5分钟内发生更新的MySQL的文章数据到ES，通过唯一id 来保证每条数据同步的准确性。