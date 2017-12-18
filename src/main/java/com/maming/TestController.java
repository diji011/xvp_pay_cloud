package com.maming;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by RS on 2017-12-18.
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
