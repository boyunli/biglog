package com.jianke.biglog.hbase;

import com.jianke.biglog.model.LogRecord;

public class AccessLogParser {
    public static LogRecord parse(String line) {
        String[] data = line.split("&&&");
        if (data.length > 0) {
            LogRecord logRecord = new LogRecord();
            logRecord.setD(data[0]);
            logRecord.setE(data[1]);
            logRecord.setUid(data[2]);
            logRecord.setU(data[2]);
            logRecord.setR(data[2]);
            logRecord.setEt(data[2]);
            logRecord.setSi(data[2]);
            logRecord.setS(data[2]);
            logRecord.setF(data[2]);
            logRecord.setHumanTime(data[3]);
            return logRecord;
        }
        return  null;
    }
}
