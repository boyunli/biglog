package com.jianke.biglog.controller;

import com.jianke.biglog.service.FlumeRpcClientFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class AsyncLogEventController {
    public static final Logger logger = LoggerFactory.getLogger(AsyncLogEventController.class);

    @RequestMapping(value = "/record/", method = RequestMethod.GET)
    public ResponseEntity listAllLogRecord(@RequestParam("accountId") String accountId,
                                           @RequestParam("eventId") String eventId,
                                           @RequestParam("eventTime") String eventTime) {
        final String DELIMITER = "$$$";
        String logRecord = accountId + DELIMITER + eventId + DELIMITER + eventTime;
        logger.info("logRecord: " + logRecord);
        FlumeRpcClientFacade flumeClient = new FlumeRpcClientFacade();
        flumeClient.init("172.21.57.149", 41414);
        flumeClient.sendDataToFlume(logRecord);
        flumeClient.cleanUp();
        return new ResponseEntity(HttpStatus.OK);
    }

}
