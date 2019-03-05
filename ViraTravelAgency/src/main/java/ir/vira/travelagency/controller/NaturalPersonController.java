package ir.vira.travelagency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NaturalPersonController {
    private static final Logger logger = LoggerFactory.getLogger(NaturalPersonController.class);

    @GetMapping({"/naturalPerson", "/naturalPerson/list"})
    public String list() {
        logger.warn("NaturalPersonController");

        return "/jsf/natural_person_list.xhtml";
    }


}
