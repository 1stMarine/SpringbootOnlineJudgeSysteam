package com.ckw.blog_sys.pojo;

import com.ckw.user.pojo.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Blog帖子对象
 * @author Echo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String context;
    private String uid;
    private String title;
    private String type;
    private int like;
    private int star;
    private String tag;
    private String adminTags;
    private String faceImage;
    private String time;
    private int comment;
    private int likeState;
    private int starState;
    private int subscribeState;
    private User user;
}
