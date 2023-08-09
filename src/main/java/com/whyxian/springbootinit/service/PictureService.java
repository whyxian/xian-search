package com.whyxian.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whyxian.springbootinit.model.vo.PictureVO;

/**
 * @program: xian-search-project
 * @description: 图片service接口
 * @author: whyxian
 * @create: 2023-05-17 19:06
 **/
public interface PictureService {
    Page<PictureVO> searchPicture(String searchText, long pageNum, long pageSize);
}
