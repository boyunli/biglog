package com.jianke.biglog.model;

public class LogRecord {
    /**
     * 日志数据格式
     */
    private String accountId;
    private String eventId;
    private String eventTime;

    @Override
    public String toString() {
        return "LogRecord{ uuid: " + accountId + ", eventId: " + eventId + ", eventTime: " + eventTime ;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}