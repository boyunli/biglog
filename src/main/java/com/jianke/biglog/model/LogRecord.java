package com.jianke.biglog.model;

public class LogRecord {
    /**
     * 日志数据格式
     */
    private String d;
    private String e;
    private String uid;
    private String u;
    private String r;
    private String et;
    private String si;
    private String s;
    private String f;
    private String humanTime;

    @Override
    public String toString() {
        return "LogRecord{" +
                "d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", uid='" + uid + '\'' +
                ", u='" + u + '\'' +
                ", r='" + r + '\'' +
                ", et='" + et + '\'' +
                ", si='" + si + '\'' +
                ", s='" + s + '\'' +
                ", f='" + f + '\'' +
                ", humanTime='" + humanTime + '\'' +
                '}';
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getEt() {
        return et;
    }

    public void setEt(String et) {
        this.et = et;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getHumanTime() {
        return humanTime;
    }

    public void setHumanTime(String humanTime) {
        this.humanTime = humanTime;
    }
}
