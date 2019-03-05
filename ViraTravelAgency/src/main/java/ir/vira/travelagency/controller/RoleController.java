package ir.vira.travelagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @GetMapping({"/role", "/role/list"})
    public String list() {
        logger.warn("RoleController");

        return "/jsf/role_list.xhtml";
    }

}
