package com.jianke.biglog.service;

import com.jianke.biglog.flume.FlumeRpcClientFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncFlumeSourceService {
    public static final Logger logger = LoggerFactory.getLogger(AsyncFlumeSourceService.class);

    @Async
    public void asyncLog(String d, String e, String uid,
                         String u, String r, String et,
                         String si, String s, String f, String p) {
        FlumeRpcClientFacade flumeClient = new FlumeRpcClientFacade();
        final String DELIMITER = "&&&";

        String logRecord = "d=" + d + DELIMITER + "p=" + p + DELIMITER
                + "uid=" + uid + DELIMITER + "u=" + u + DELIMITER
                + "r=" + r + DELIMITER + "et=" + et + DELIMITER
                + "si=" + si + DELIMITER + "s=" + s + DELIMITER
                + "f=" + f + DELIMITER + "e=" + e + DELIMITER + System.currentTimeMillis();
        logger.info("logRecord: " + logRecord);
        flumeClient.init();
        flumeClient.sendDataToFlume(logRecord);
        flumeClient.cleanUp();
    }
}
