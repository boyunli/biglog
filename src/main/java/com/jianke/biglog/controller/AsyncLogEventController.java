package com.jianke.biglog.controller;

import com.jianke.biglog.service.AsyncLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class AsyncLogEventController {

    @Autowired
    private AsyncLogService asyncLogService;

    @RequestMapping(value = "/record/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity listAllLogRecord(@RequestParam("d") String d,
                                           @RequestParam("e") String e,
                                           @RequestParam("uid") String uid,
                                           @RequestParam("u") String u,
                                           @RequestParam("r") String r,
                                           @RequestParam("et") String et,
                                           @RequestParam("si") String si,
                                           @RequestParam("s") String s,
                                           @RequestParam("f") String f,
                                           HttpServletResponse rsp) {
        rsp.addHeader("Content-Type", "image/png");
        asyncLogService.asyncLog(d, e, uid, u, r, et, si, s, f);
        System.out.println("请求完成！");
        return new ResponseEntity(HttpStatus.OK);
    }
}
