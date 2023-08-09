package com.whyxian.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whyxian.springbootinit.common.BaseResponse;
import com.whyxian.springbootinit.common.ErrorCode;
import com.whyxian.springbootinit.common.ResultUtils;
import com.whyxian.springbootinit.exception.ThrowUtils;
import com.whyxian.springbootinit.model.dto.picture.PictureQueryRequest;
import com.whyxian.springbootinit.model.vo.PictureVO;
import com.whyxian.springbootinit.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: xian-search-project
 * @description: 网页图片api
 * @author: whyxian
 * @create: 2023-05-17 18:59
 **/
@RestController
@RequestMapping("picture")
@Slf4j
public class PictureController {


    @Resource
    private PictureService pictureService;

    /**
     * 分页获取列表（封装类）
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<PictureVO>> listPostVOByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                          HttpServletRequest request) {

        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();

        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<PictureVO> picturePage = pictureService.searchPicture(pictureQueryRequest.getSearchText(), current, size);
        return ResultUtils.success(picturePage);
    }

}
