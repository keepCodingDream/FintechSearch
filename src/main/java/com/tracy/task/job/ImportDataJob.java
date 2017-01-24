package com.tracy.task.job;

import com.cashbus.mybatis.Condition;
import com.tracy.task.base.CommonUtils;
import com.tracy.task.dbconfig.ITaskBaseService;
import com.tracy.task.model.FinTechArticle;
import com.tracy.task.model.FinTechArticleEs;
import com.tracy.task.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lurenjie on 2017/1/8
 */
@Slf4j
@Component
public class ImportDataJob {
    @Resource
    private ITaskBaseService taskBaseService;
    @Resource
    private SearchService searchService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void refreshFinTechData() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, -3);
        Date time = now.getTime();
        Condition condition = new Condition(FinTechArticle.class);
        condition.createCriteria().andGreaterThanOrEqualTo("created", time).andIsNull("isNotify");
        List<FinTechArticle> finTechArticles = taskBaseService.listQueryByCondition(condition);
        if (!CollectionUtils.isEmpty(finTechArticles)) {
            try {
                searchService.builderSearchIndex(FinTechArticleEs.class);
                for (FinTechArticle item : finTechArticles) {
                    searchService.refreshAndUpdate(item);
                }
            } catch (Exception e) {
                log.error("数据导入出错", e);
            }
        }
    }

}
