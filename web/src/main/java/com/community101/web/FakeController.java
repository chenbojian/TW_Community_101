package com.community101.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenbojian on 7/16/15.
 */
@RestController
@RequestMapping("/fake")
public class FakeController {
    @RequestMapping("/")
    public String fake() {
        return "fakehaha";
    }

    @RequestMapping("/echo")
    public long echo(long id) {
        return id;
    }
}
