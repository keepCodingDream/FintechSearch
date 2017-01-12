package com.tracy.task.rest;

import com.tracy.task.model.FinTechArticleEs;
import com.tracy.task.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lurenjie on 2017/1/12
 */
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchRest extends BaseRest {
    @Resource
    private SearchService searchService;

    @ResponseBody
    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> disPatcher(@RequestParam(value = "value") String value) throws Exception {
        Map<String, Object> response = new HashMap<>();
        if (StringUtils.isEmpty(value)) {
            return response;
        }
        Page<FinTechArticleEs> pages = searchService.searchInfo(value);
        response.put("result", pages.getContent());
        response.put("totalElements", pages.getTotalElements());
        response.put("totalPages", pages.getTotalPages());
        response.put("number", pages.getNumber());
        return response;
    }
}
