package org.fireplume.controllers;

import org.fireplume.events_handling.events.CustomEvent;
import org.fireplume.events_handling.publishers.CustomEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private CustomEventPublisher customEventPublisher;

    @ResponseBody
    @GetMapping(path = "/")
    public String index() {
        CustomEvent customEvent = new CustomEvent(this);
        System.out.println(Thread.currentThread().getName());
        customEventPublisher.publish(customEvent);
        return "Index";
    }
}
