package com.perfree.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 定义归档实体类
 * @author Perfree
 */
@ApiModel(value="Archive-文章归档数据",description="文章归档数据")
public class Archive implements Serializable {
    private static final long serialVersionUID = 4900274588193382136L;

    @ApiModelProperty(value="日期",name="date",example="2021年12月")
    private String date;

    @ApiModelProperty(value="文章数据",name="articles")
    private List<Article> articles;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
