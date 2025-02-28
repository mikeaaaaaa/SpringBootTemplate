package com.xiaohao.springbootinit.model.enums;

import com.xiaohao.springbootinit.model.entity.Picture;
import com.xiaohao.springbootinit.model.vo.PostVO;
import com.xiaohao.springbootinit.model.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchVO implements Serializable {
    private List<UserVO> userList;
    private List<Picture> pictureList;
    private List<PostVO> postList;
    private List<?> dataList;
    private static final long serialVersionUID = 1L;
}