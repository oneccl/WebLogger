package com.cc.weblogger.controller;

import com.cc.weblogger.annotation.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/2/28
 * Time: 20:29
 * Description:
 */

@CrossOrigin
@RestController
public class UserController {

    //@Log
    @GetMapping("/data")
    public Object getData(String arg){
        return "Ack: "+arg;
    }

}
