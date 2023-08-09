package com.whyxian.springbootinit.controller;

import com.whyxian.springbootinit.model.dto.search.AggregateSearchRequest;
import com.whyxian.springbootinit.model.vo.AggregateSearchVO;
import com.whyxian.springbootinit.service.PictureService;
import com.whyxian.springbootinit.service.PostService;
import com.whyxian.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    private PictureService pictureService;

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

//    @PostMapping("/all")
//    public List<AggregateSearchVO> aggregateSearch(@RequestBody AggregateSearchRequest searchRequest){
//
//        String searchText = searchRequest.getSearchText();
//        pictureService.searchPicture(searchText, 1, 10);
//
//    }

}
