package org.fireplume.cpntrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PagesController {

    @GetMapping(path = "/pages/{page}")
    public String getPage1(@PathVariable String page) {
        return "pages/" + page;
    }
}
