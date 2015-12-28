package com.alfa.tracking.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller fetches just the index page right now.
 * Some more functionalities can be stuffed into this controller.
 * @author ChandanK
 *
 */
@Controller
public class IndexController  {
    protected final Log logger = LogFactory.getLog(getClass());
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String get(Model model){
        return "home";
    }
}