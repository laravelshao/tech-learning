package com.laravelshao.spring.framework.webmvc;

import lombok.Data;

import java.util.Map;

/**
 * Created by shaoqinghua on 2018/8/18.
 */
@Data
public class ModelAndView {

    private String viewName;
    private Map<String, ?> model;

    public ModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

}
