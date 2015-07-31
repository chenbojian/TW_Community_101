package com.community101.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MiffyLiye on 31/07/2015.
 */

@RestController
@RequestMapping("/api/env")
public class EnvironmentVariablesController {
    @RequestMapping("/env.js")
    public ModelAndView getEnvJs(HttpServletRequest request) {
        return new ModelAndView("env");
    }
}
