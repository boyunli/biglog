package com.jianke.biglog.controller;

import com.jianke.biglog.service.AsyncFlumeSourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;

@RestController
@RequestMapping("/")
public class AsyncLogEventController {

    @Autowired
    private AsyncFlumeSourceService asyncFlumeSourceService;

    @RequestMapping(value = "/record", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity listAllLogRecord(@RequestParam(value = "d", required = false) String d,
                                           @RequestParam(value = "e", required = false) String e,
                                           @RequestParam(value = "uid", required = false) String uid,
                                           @RequestParam(value = "u", required = false) String u,
                                           @RequestParam(value = "r", required = false) String r,
                                           @RequestParam(value = "ua", required = false) String ua,
                                           @RequestParam(value = "et", required = false) String et,
                                           @RequestParam(value = "si", required = false) String si,
                                           @RequestParam(value = "s", required = false) String s,
                                           @RequestParam(value = "f", required = false) String f,
                                           @RequestParam(value = "p", required = false) String p,
                                           HttpServletResponse rsp, HttpServletRequest request) {
        rsp.addHeader("Content-Type", "image/png");
        String ip = getIpAddr(request);
        System.out.println("request ip: " + ip);
        asyncFlumeSourceService.asyncLog(d, e, uid, u, r, ua, et, si, s, f, p, ip);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @Description: 获取客户端IP地址
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
