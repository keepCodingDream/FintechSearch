package com.tracy.task.service;

import com.tracy.task.model.BaseEs;
import com.tracy.task.model.FinTechArticleEs;
import com.tracy.task.repositories.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lurenjie on 2017/1/7
 */
@Slf4j
@Service
public class SearchService {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    private ArticleRepository articleRepository;

    /**
     * 创建索引
     */
    public <T> boolean builderSearchIndex(Class<T> obj) throws Exception {
        return elasticsearchTemplate.createIndex(obj);
    }

    /**
     * 删除索引
     *
     * @param obj 类型
     */
    public <T> boolean deleteIndex(Class<T> obj) throws Exception {
        return elasticsearchTemplate.deleteIndex(obj);
    }

    /**
     * 刷新数据到es
     *
     * @param obj 需要刷新的obj
     */
    public void refreshData(List<FinTechArticleEs> obj, String type) {
        if (StringUtils.isEmpty(obj) || StringUtils.isEmpty(type)) {
            throw new IllegalArgumentException("刷新参数不能为空");
        }
        List<IndexQuery> indexQueries = new ArrayList<>(obj.size());
        elasticsearchTemplate.putMapping(FinTechArticleEs.class);
        elasticsearchTemplate.refresh(FinTechArticleEs.class);
        Document annotation = FinTechArticleEs.class.getAnnotation(Document.class);
        if (annotation == null) {
            throw new RuntimeException("实体类中必须包含Document注解");
        }
        String indexName = annotation.indexName();
        if (StringUtils.isEmpty(indexName)) {
            throw new RuntimeException("Document注解中必须包含indexName");
        }
        for (FinTechArticleEs item : obj) {
            articleRepository.save(item);
        }
        elasticsearchTemplate.refresh(FinTechArticleEs.class);
    }


    /**
     * 搜索
     *
     * @param indexName 索引名称
     * @param type      类型
     * @param param     搜索条件
     * @param obj       obj类型
     * @return 搜索结果
     */
    public <T> T searchInfo(String indexName, String type, String param, Class<T> obj) throws Exception {
        return null;
    }
}