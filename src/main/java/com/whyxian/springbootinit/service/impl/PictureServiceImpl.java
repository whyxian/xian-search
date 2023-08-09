package com.whyxian.springbootinit.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whyxian.springbootinit.common.ErrorCode;
import com.whyxian.springbootinit.exception.BusinessException;
import com.whyxian.springbootinit.model.vo.PictureVO;
import com.whyxian.springbootinit.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: xian-search-project
 * @description: 图片service实现类
 * @author: whyxian
 * @create: 2023-05-17 19:06
 **/
@Service
public class PictureServiceImpl implements PictureService {
    @Override
    public Page<PictureVO> searchPicture(String searchText, long pageNum, long pageSize) {
        List<PictureVO> pictureVOList = new ArrayList<>();
        long current = (pageNum - 1) * pageSize;
        String url = "https://www.bing.com/images/search?q="+searchText+"&form=HDRSC2&first=" + current;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据获取异常");
        }
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
            if(pictureVOList.size() == pageSize){
                break;
            }
        }
        Page<PictureVO> picturePage = new Page<>(pageNum, pageSize);
        picturePage.setRecords(pictureVOList);
        return picturePage;
    }
}
