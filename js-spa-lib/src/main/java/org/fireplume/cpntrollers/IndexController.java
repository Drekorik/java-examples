package org.fireplume.cpntrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = "/*")
    public String getIndex(Model model) {
        return "index";
    }
}
