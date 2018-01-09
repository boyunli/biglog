package com.jianke.biglog.hbase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jianke.biglog.model.LogRecord;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.conf.ComponentConfiguration;
import org.apache.flume.sink.hbase.HbaseEventSerializer;
import org.apache.hadoop.hbase.client.Increment;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.util.Bytes;

public class AsyncHbaseLogEventSerializer  implements HbaseEventSerializer {
    //列簇
    private byte[] colFam = "biglog".getBytes();
    private Event currentEvent;

    @Override
    public void initialize(Event event, byte[] colFam) {
        this.currentEvent = event;
        this.colFam = colFam;
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<Row> getActions() {

        String eventStr = new String(currentEvent.getBody());
        System.out.println("*****event:" + eventStr);
        LogRecord cols = AccessLogParser.parse(eventStr);
        String req_ts_str = cols.getHumanTime();
        Long currTime = System.currentTimeMillis();
        if(req_ts_str != null && !req_ts_str.equals("")) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                currTime = df.parse(req_ts_str).getTime();
            } catch (ParseException e) {
                System.out.println("parse req time error,using system.current time.");
            }
        }

        // 设置行键
        long revTs = Long.MAX_VALUE - currTime;
        byte[] currentRowKey = (UuidGenerator.getUUID() + Long.toString(revTs)).getBytes();
        List<Row> puts = new ArrayList<Row>();
        Put putReq = new Put(currentRowKey);

        //增加列 add(byte[] family, byte[] qualifier, byte[] value)
        putReq.add(colFam, "d".getBytes(), Bytes.toBytes(cols.getD()));
        putReq.add(colFam, "e".getBytes(), Bytes.toBytes(cols.getE()));
        putReq.add(colFam, "uid".getBytes(), Bytes.toBytes(cols.getUid()));
        putReq.add(colFam, "u".getBytes(), Bytes.toBytes(cols.getU()));
        putReq.add(colFam, "r".getBytes(), Bytes.toBytes(cols.getR()));
        putReq.add(colFam, "et".getBytes(), Bytes.toBytes(cols.getEt()));
        putReq.add(colFam, "si".getBytes(), Bytes.toBytes(cols.getSi()));
        putReq.add(colFam, "s".getBytes(), Bytes.toBytes(cols.getS()));
        putReq.add(colFam, "f".getBytes(), Bytes.toBytes(cols.getF()));
        puts.add(putReq);
        return puts;
    }

    @Override
    public List<Increment> getIncrements() {
        List<Increment> incs = new ArrayList<Increment>();
        return incs;
    }

    @Override
    public void configure(Context arg0) {
    }

    @Override
    public void configure(ComponentConfiguration arg0) {
    }

    @Override
    public void close() {
        colFam = null;
        currentEvent = null;
    }
}

