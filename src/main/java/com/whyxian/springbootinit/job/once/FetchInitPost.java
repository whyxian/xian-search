package com.whyxian.springbootinit.job.once;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.whyxian.springbootinit.esdao.PostEsDao;
import com.whyxian.springbootinit.model.entity.Post;
import com.whyxian.springbootinit.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: xian-search-project
 * @description: 爬虫获取初始化的帖子
 * @author: whyxian
 * @create: 2023-05-16 22:43
 **/
//@Component
@Slf4j
public class FetchInitPost implements CommandLineRunner {


    @Resource
    private PostService postService;

    @Resource
    private PostEsDao postEsDao;
    @Override
    public void run(String... args) throws Exception {
        // 1. 获取数据
        String url = "https://www.code-nav.cn/api/post/list/page/vo";
        String json = "{\"sortField\":\"createTime\",\"sortOrder\":\"descend\",\"reviewStatus\":1,\"current\":1}";
        String result = HttpRequest.post(url)
                .body(json)
                .execute()
                .body();
        // 2. Json 转对象
        Map<String, Object> map = JSONUtil.toBean(result, Map.class);
        JSONObject date = (JSONObject) map.get("data");
        JSONArray records = (JSONArray) date.get("records");
        List<Post> postList = new ArrayList<>();
        for (Object record : records) {
            Post post = new Post();
            JSONObject tempRecord = (JSONObject) record;
            post.setTitle(tempRecord.getStr("title"));
            post.setContent(tempRecord.getStr("content"));
            JSONArray tags = (JSONArray) tempRecord.get("tags");
            List<String> tagsList = tags.toList(String.class);
            post.setTags(JSONUtil.toJsonStr(tagsList));
            post.setUserId(1L);
            postList.add(post);
        }
        // 3. 插入数据库
        boolean b = postService.saveBatch(postList);
        if (b){
            log.info("获取初始帖子成功,条数:{}",postList.size());
        }else {
            log.error("获取初始帖子失败!");
        }
    }
}
