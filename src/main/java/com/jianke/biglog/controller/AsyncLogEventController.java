package com.jianke.biglog.controller;

import com.jianke.biglog.service.FlumeRpcClientFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/")
public class AsyncLogEventController {
    public static final Logger logger = LoggerFactory.getLogger(AsyncLogEventController.class);

    @Autowired
    private FlumeRpcClientFacade flumeClient;

    @RequestMapping(value = "/record/", method = RequestMethod.GET)
    public ResponseEntity listAllLogRecord(@RequestParam("accountId") String accountId,
                                           @RequestParam("eventId") String eventId,
                                           @RequestParam("eventTime") String eventTime) {
        final String DELIMITER = "&&&";
        Date now = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String humanTime = dt.format(now);

        String logRecord = accountId + DELIMITER + eventId + DELIMITER + eventTime + DELIMITER + humanTime;
        logger.info("logRecord: " + logRecord);

//        flumeClient.init("172.21.57.149", 41414);
        flumeClient.init();
        flumeClient.sendDataToFlume(logRecord);
        flumeClient.cleanUp();
        return new ResponseEntity(HttpStatus.OK);
    }

}
