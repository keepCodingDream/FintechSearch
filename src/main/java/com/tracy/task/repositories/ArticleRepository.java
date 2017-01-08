package com.tracy.task.repositories;

import com.tracy.task.model.FinTechArticleEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by lurenjie on 2017/1/8
 */
public interface ArticleRepository extends ElasticsearchRepository<FinTechArticleEs, String> {
}

