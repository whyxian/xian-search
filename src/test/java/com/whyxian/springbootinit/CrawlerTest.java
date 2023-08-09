package com.whyxian.springbootinit;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.whyxian.springbootinit.model.vo.PictureVO;
import com.whyxian.springbootinit.model.entity.Post;
import com.whyxian.springbootinit.service.PostService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: xian-search-project
 * @description: 爬虫测试
 * @author: whyxian
 * @create: 2023-05-16 21:37
 **/
@SpringBootTest
public class CrawlerTest {

    @Resource
    private PostService postService;

    @Test
    public void testHttp() {
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
        System.out.println(b);
    }

    @Test
    public void testHTML() throws IOException {
        List<PictureVO> pictureVOList = new ArrayList<>();
        Integer current = 1;
        String url = "https://www.bing.com/images/search?q=java&form=HDRSC2&first=" + current;
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".iuscp.isv");
        for (Element element : elements) {
            // 取图片地址
            String m = element.select(".iusc").get(0).attr("m");
            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            String murl = (String) map.get("murl");
            // 取标题
            String title = element.select(".inflnk").get(0).attr("aria-label");
            PictureVO pictureVo = new PictureVO();
            pictureVo.setTitle(title);
            pictureVo.setUrl(murl);
            pictureVOList.add(pictureVo);
        }
        pictureVOList.forEach(o -> System.out.println(o));

    }


}
