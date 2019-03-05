package ir.vira.travelagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @RequestMapping("/error")
    public String error() {
        logger.warn("error");

        return getErrorPath();
    }

    @Override
    public String getErrorPath() {
        return "/jsf/error.xhtml";
    }

    @RequestMapping("/message")
    public String message() {
        logger.warn("message");

        return "/jsf/message.xhtml";
    }
}
