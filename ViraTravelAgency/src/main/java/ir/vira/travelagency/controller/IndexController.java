package ir.vira.travelagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    
    @GetMapping({"/test"})
    public String test() {
        logger.warn("test");
        
        return "/jsf/test.xhtml";
    }
    
    @GetMapping({"/", "/index"})
    public String index() {
        logger.warn("indexController");
        
        return "/jsf/index.xhtml";
    }
    
}
