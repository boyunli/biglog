package com.jianke.biglog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AsyncLogService {
    public static final Logger logger = LoggerFactory.getLogger(AsyncLogService.class);

    @Async
    public void asyncLog(String d, String e, String uid,
                         String u, String r, String et,
                         String si, String s, String f) {
        Date now = new Date();
        FlumeRpcClientFacade flumeClient = new FlumeRpcClientFacade();
        final String DELIMITER = "&&&";

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String humanTime = dt.format(now);

        String logRecord = d + DELIMITER + e + DELIMITER + uid + DELIMITER
                + u + DELIMITER + r + DELIMITER + et + DELIMITER
                + si + DELIMITER + s + DELIMITER + f + DELIMITER + humanTime;
        logger.info("logRecord: " + logRecord);
        flumeClient.init();
        flumeClient.sendDataToFlume(logRecord);
        flumeClient.cleanUp();
    }
}
