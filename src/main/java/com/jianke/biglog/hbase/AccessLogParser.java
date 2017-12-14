package com.jianke.biglog.hbase;

import com.jianke.biglog.model.LogRecord;

public class AccessLogParser {
    public static LogRecord parse(String line) {
        String[] data = line.split("&&&");
        if (data.length > 0) {
            LogRecord logRecord = new LogRecord();
            logRecord.setAccountId(data[0]);
            logRecord.setEventId(data[1]);
            logRecord.setEventTime(data[2]);
            logRecord.setHumanTime(data[3]);
            return logRecord;
        }
        return  null;
    }
}
