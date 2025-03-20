package com.xiaohao.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaohao.springbootinit.model.dto.post.PostQueryRequest;
import com.xiaohao.springbootinit.model.entity.Post;
import com.xiaohao.springbootinit.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 帖子服务测试
 */
// @SpringBootTest
class PostServiceTest {

    @Resource
    private PostService postService;

    @Test
    void searchFromEs() {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setUserId(1L);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        Assertions.assertNotNull(postPage);
    }

}