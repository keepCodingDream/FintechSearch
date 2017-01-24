package com.tracy.task.base;

import com.tracy.task.model.FinTechArticle;
import com.tracy.task.model.FinTechArticleEs;

/**
 * Created by lurenjie on 2017/1/24
 */
public class CommonUtils {
    public static FinTechArticleEs convertFinTech2Es(FinTechArticle finTechArticle) {
        FinTechArticleEs finTechArticleEs = null;
        if (finTechArticle != null) {
            finTechArticleEs = new FinTechArticleEs();
            finTechArticleEs.setContent(finTechArticle.getContent());
            finTechArticleEs.setTitle(finTechArticle.getTitle());
            finTechArticleEs.setUrl(finTechArticle.getUrl());
        }
        return finTechArticleEs;
    }
}
