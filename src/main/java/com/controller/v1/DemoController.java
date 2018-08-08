package com.controller.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @RequestMapping("/alive")
    public String alive() {
        return "yes";
    }
}
