package com.perfree.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * comment table
 *
 * @author Perfree
 */
@Schema(description = "评论数据")
public class Comment implements Serializable {
    private static final long serialVersionUID = 7817277417501762472L;

    @Schema(description = "评论ID", name = "id")
    private Long id;

    @Schema(description = "父级评论ID", name = "pid")
    private Long pid;

    @Schema(description = "顶级评论ID", name = "topPid")
    private Long topPid;

    @Schema(description = "文章id", name = "articleId")
    @NotNull(message = "文章id不允许为空")
    private Long articleId;

    @Schema(description = "评论人id", name = "userId")
    private Long userId;

    @Schema(description = "评论内容", name = "content")
    @NotBlank(message = "评论内容不允许为空")
    @Length(max = 1500, message = "评论内容过长")
    private String content;

    @Schema(description = "头像", name = "avatar")
    private String avatar;

    @Schema(description = "网址", name = "website")
    @Length(max = 200, message = "网站地址过长")
    private String website;

    @Schema(description = "邮箱", name = "email")
    @Length(max = 100, message = "邮箱长度过长")
    private String email;

    @Schema(description = "用户名", name = "userName")
    @Length(max = 16, message = "用户名长度要在1-16字之间")
    private String userName;

    @Schema(hidden = true)
    private Boolean readAvatar = true;

    @Schema(description = "状态", name = "status", example = "0正常,1:待审核")
    private Integer status;

    @Schema(description = "创建时间", name = "createTime")
    private Date createTime;

    @Schema(description = "更新时间", name = "updateTime")
    private Date updateTime;

    @Schema(description = "用户信息", name = "user")
    private User user;

    @Schema(description = "文章信息", name = "article")
    private Article article;

    @Schema(description = "子评论", name = "child")
    private List<Comment> child;

    @Schema(description = "父级评论", name = "parent")
    private Comment parent;

    @Schema(description = "文章类型", name = "articleType")
    private String articleType;

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Boolean getReadAvatar() {
        return readAvatar;
    }

    public void setReadAvatar(Boolean readAvatar) {
        this.readAvatar = readAvatar;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }



    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Comment> getChild() {
        return child;
    }

    public void setChild(List<Comment> child) {
        this.child = child;
    }

    public Long getTopPid() {
        return topPid;
    }

    public void setTopPid(Long topPid) {
        this.topPid = topPid;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
}