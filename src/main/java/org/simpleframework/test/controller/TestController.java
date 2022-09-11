package org.simpleframework.test.controller;

import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.annotation.RequestParam;
import org.simpleframework.test.service.TestService;

/**
 * @author fa
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/helloWorld")
    public String helloWorld(@RequestParam("name") String name) {
        return name + " say: helloWorld";
    }

}
