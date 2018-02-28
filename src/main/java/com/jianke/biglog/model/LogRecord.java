package com.jianke.biglog.model;

import java.io.Serializable;

public class LogRecord implements Serializable{
    /**
     * 日志数据格式
     */
    private String d;
    private String e;
    private String uid;
    private String u;
    private String r;
    private String ua;
    private String et;
    private String si;
    private String s;
    private String f;
    private String p;
    private String ip;
    private String humanTime;

    @Override
    public String toString() {
        return "LogRecord{" +

                "d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", uid='" + uid + '\'' +
                ", u='" + u + '\'' +
                ", r='" + r + '\'' +
                ", ua='" + ua + '\'' +
                ", et='" + et + '\'' +
                ", si='" + si + '\'' +
                ", s='" + s + '\'' +
                ", f='" + f + '\'' +
                ", p='" + p + '\'' +
                ", ip='" + ip + '\'' +
                ", humanTime='" + humanTime + '\'' +
                '}';
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
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

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHumanTime() {
        return humanTime;
    }

    public void setHumanTime(String humanTime) {
        this.humanTime = humanTime;
    }
}
