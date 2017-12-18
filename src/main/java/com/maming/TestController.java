package com.maming;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by RS on 2017-12-18.
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(HttpServletRequest request, @RequestBody Persion persion){
        try {
            InputStream in  = request.getInputStream();
            String param = IOUtils.toString(in,"UTF-8");
            System.out.println(param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "test";
    }
}
