package com.whyxian.springbootinit.controller;

import com.whyxian.springbootinit.common.BaseResponse;
import com.whyxian.springbootinit.common.ResultUtils;
import com.whyxian.springbootinit.manager.SearchFacade;
import com.whyxian.springbootinit.model.dto.search.SearchRequest;
import com.whyxian.springbootinit.model.vo.SearchVO;
import com.whyxian.springbootinit.service.PictureService;
import com.whyxian.springbootinit.service.PostService;
import com.whyxian.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: xian-search-project
 * @description: 聚合搜索api
 * @author: whyxian
 * @create: 2023-05-17 21:00
 **/
@RestController
@RequestMapping("aggregateSearch")
@Slf4j
public class SearchController {

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

    @Resource
    private PictureService pictureService;

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchRequest, request));
    }



}
