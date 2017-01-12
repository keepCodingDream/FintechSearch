package com.tracy.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * Created by lurenjie on 2017/1/8
 */
@Data
@Document(indexName = "fintech", type = "article")
public class FinTechArticleEs {
    @Id
    private String id;
    private String title;
    private String content;
    private String url;

    public FinTechArticleEs() {
    }

    public FinTechArticleEs(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
    }

}
