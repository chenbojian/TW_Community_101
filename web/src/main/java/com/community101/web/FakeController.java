package com.community101.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenbojian on 7/16/15.
 */
@RestController
public class FakeController {
    @RequestMapping("/fake")
    public String fake() {
        return "fakehaha";
    }
}
