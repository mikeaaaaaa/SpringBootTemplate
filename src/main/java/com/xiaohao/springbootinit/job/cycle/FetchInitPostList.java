package com.xiaohao.springbootinit.job.cycle;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xiaohao.springbootinit.model.entity.Post;
import com.xiaohao.springbootinit.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取初始帖子列表
 * */
// 取消注释后，每次启动 springboot 项目时会执行一次 run 方法
@Component
@Slf4j
public class FetchInitPostList implements CommandLineRunner {

    @Resource
    private PostService postService;


    @Override
    public void run(String... args) {
        // 1. 获取数据
        List<Post> postList = null;
        boolean b = false;
        try {
            String json = "{\"current\":1,\"pageSize\":8,\"sortField\":\"createTime\",\"sortOrder\":\"descend\",\"category\":\"文章\",\"reviewStatus\":1}";
            String url = "https://www.code-nav.cn/api/post/search/page/vo";
            String result = HttpRequest
                    .post(url)
                    .body(json)
                    .execute()
                    .body();
//        System.out.println(result);
            // 2. json 转对象
            Map<String, Object> map = JSONUtil.toBean(result, Map.class);
            JSONObject data = (JSONObject) map.get("data");
            JSONArray records = (JSONArray) data.get("records");
            postList = new ArrayList<>();
            for (Object record : records) {
                JSONObject tempRecord = (JSONObject) record;
                Post post = new Post();
                post.setTitle(tempRecord.getStr("title"));
                post.setContent(tempRecord.getStr("content"));
                JSONArray tags = (JSONArray) tempRecord.get("tags");
                List<String> tagList = tags.toList(String.class);
                post.setTags(JSONUtil.toJsonStr(tagList));
                post.setUserId(1L);
                postList.add(post);
            }
//        System.out.println(postList);
            // 3. 数据入库
            postService.saveBatch(postList);
            log.info("获取初始化帖子列表成功，条数 = {}", postList.size());
        } catch (Exception e) {
            log.error("获取初始化帖子列表失败");
        }

    }


}
