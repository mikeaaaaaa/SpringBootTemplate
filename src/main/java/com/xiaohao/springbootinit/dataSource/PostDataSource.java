package com.xiaohao.springbootinit.dataSource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaohao.springbootinit.model.dto.post.PostQueryRequest;
import com.xiaohao.springbootinit.model.entity.Post;
import com.xiaohao.springbootinit.model.vo.PostVO;
import com.xiaohao.springbootinit.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO> {

    @Resource
    private PostService postService;

    @Override
    public Page<PostVO> doSearch(String searchText, long pageNum, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(pageNum);
        postQueryRequest.setPageSize(pageSize);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        // 使用 ES 搜索
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return postService.getPostVOPage(postPage, request);

        // 普通搜索
        // Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest, request);
        // return postVOPage;
    }

}