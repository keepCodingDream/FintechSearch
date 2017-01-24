package com.tracy.task.model;

import com.cashbus.mybatis.GenericReadOnlyModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Table;

/**
 * Created by lurenjie on 2017/1/7
 */
@Table(name = "fin_tech_article")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NameStyle
@Document(indexName = "finTech", type = "article")
public class FinTechArticle extends GenericReadOnlyModel {
    private String domain;
    private String title;
    private String url;
    private String content;
    private String hashs;
}
