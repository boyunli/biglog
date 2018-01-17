package com.jianke.biglog.controller;

import com.jianke.biglog.service.AsyncFlumeSourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class AsyncLogEventController {

    @Autowired
    private AsyncFlumeSourceService asyncFlumeSourceService;

    @RequestMapping(value = "/record/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity listAllLogRecord(@RequestParam(value = "d", required = false) String d,
                                           @RequestParam(value = "e", required = false) String e,
                                           @RequestParam(value = "uid", required = false) String uid,
                                           @RequestParam(value = "u", required = false) String u,
                                           @RequestParam(value = "r", required = false) String r,
                                           @RequestParam(value = "et", required = false) String et,
                                           @RequestParam(value = "si", required = false) String si,
                                           @RequestParam(value = "s", required = false) String s,
                                           @RequestParam(value = "f", required = false) String f,
                                           @RequestParam(value = "p", required = false) String p,
                                           HttpServletResponse rsp) {
        rsp.addHeader("Content-Type", "image/png");
        asyncFlumeSourceService.asyncLog(d, e, uid, u, r, et, si, s, f, p);
        return new ResponseEntity(HttpStatus.OK);
    }
}
