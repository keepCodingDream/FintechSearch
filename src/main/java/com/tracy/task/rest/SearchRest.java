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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> disPatcher(@RequestParam(value = "value") String value, @RequestParam(required = false, value = "index") Integer index) throws Exception {
        Map<String, Object> response = new HashMap<>();
        if (StringUtils.isEmpty(value)) {
            return response;
        }
        if (index == null) {
            index = 0;
        }
        Page<FinTechArticleEs> pages = searchService.searchInfo(value, index);
        List<FinTechArticleEs> contents = pages.getContent();
        for (FinTechArticleEs item : contents) {
            if (item.getContent().length() > 200) {
                item.setContent(item.getContent().substring(0, 200));
            }
            if (StringUtils.isEmpty(item.getTitle())) {
                int length = item.getContent().length() > 16 ? 16 : item.getContent().length();
                item.setTitle(item.getContent().substring(0, length - 1));
            }
        }
        String baseUrl = "/search/list?value=" + value + "&index=";
        Integer totalPages = pages.getTotalPages() > 10 ? 10 : pages.getTotalPages();
        List<Map<String, String>> nextPages = new ArrayList<>(totalPages);
        for (int i = 0; i < totalPages; i++) {
            Map<String, String> item = new HashMap<>();
            item.put("url", baseUrl + i);
            item.put("index", i + 1 + "");
            if (i == index) {
                item.put("current", "1");
            }
            nextPages.add(item);
        }
        response.put("result", contents);
        response.put("totalElements", pages.getTotalElements());
        response.put("totalPages", totalPages);
        response.put("number", pages.getNumber());
        response.put("next", nextPages);
        return response;
    }


    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView list(@RequestParam(value = "value") String value, @RequestParam(required = false, value = "index") Integer index) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/list.jsp");
        modelAndView.addObject("response", disPatcher(value, index));
        return modelAndView;
    }
}
