package com.tracy.task.repositories;

import com.tracy.task.model.FinTechArticleEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by lurenjie on 2017/1/8
 */
public interface ArticleRepository extends ElasticsearchRepository<FinTechArticleEs, String> {
    Page<FinTechArticleEs> findByTitleOrContent(String title, String content, Pageable pageable);

}

