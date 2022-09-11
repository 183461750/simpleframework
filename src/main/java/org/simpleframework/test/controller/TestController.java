package org.simpleframework.test.controller;

import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.annotation.RequestParam;
import org.simpleframework.mvc.annotation.ResponseBody;
import org.simpleframework.test.service.TestService;

import java.util.HashMap;
import java.util.Map;

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

    @ResponseBody
    @RequestMapping(value = "/helloWorld/body")
    public Map<String, String> helloWorldBody(@RequestParam("name") String name) {
        Map<String, String> map = new HashMap<>(1);
        map.put(name, " say: helloWorld body");
        return map;
    }

}
