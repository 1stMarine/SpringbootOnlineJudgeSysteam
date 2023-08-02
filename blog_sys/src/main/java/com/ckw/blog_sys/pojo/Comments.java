package com.ckw.blog_sys.pojo;

import com.ckw.user.pojo.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Comments {
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private long bid;
    /**
     * 被评论用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private long blogUid;
    /**
     * 评论用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private long commentsUid;
    private String time;
    private String context;
    private String commentsContext;
    private int like;

    private int star;
    /**
     * 评论用户对象
     */
    private User commentsUser;
    /**
     * 被评论用户对象
     */
    private User user;

    private int likeState;
}
