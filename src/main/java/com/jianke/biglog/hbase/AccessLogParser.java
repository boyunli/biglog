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
            logRecord.setU(data[3]);
            logRecord.setR(data[4]);
            logRecord.setEt(data[5]);
            logRecord.setSi(data[6]);
            logRecord.setS(data[7]);
            logRecord.setF(data[8]);
            logRecord.setHumanTime(data[9]);
            return logRecord;
        }
        return  null;
    }
}
