package com.xiaohao.springbootinit.controller;


import com.xiaohao.springbootinit.common.BaseResponse;
import com.xiaohao.springbootinit.common.ResultUtils;
import com.xiaohao.springbootinit.manager.SearchFacade;
import com.xiaohao.springbootinit.model.dto.search.SearchRequest;
import com.xiaohao.springbootinit.model.enums.SearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片接口
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private SearchFacade searchFacade;


    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchQueryRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchQueryRequest, request));
    }


}
