package com.jianke.biglog.service;

import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;
import org.springframework.stereotype.Service;

@Service
public class FlumeRpcClientFacade {
    private RpcClient client;
//    private String hostname;
//    private Integer port;
    private Properties props;
    private static String host1 = "172.21.57.149:41414";
    private static String host2 = "172.21.57.148:41414";
    private static String host3 = "172.21.57.147:41414";


//    public void init(String hostname, Integer port) {
    public void init() {
//        this.hostname = hostname;
//        this.port = port;
//        this.client = RpcClientFactory.getDefaultInstance(hostname, port);
        this.props = new Properties();
        this.props.put("client.type", "default_loadbalance");
        this.props.put("hosts", "h1 h2 h3");
        this.props.put("hosts.h1", this.host1);
        this.props.put("hosts.h2", this.host2);
        this.props.put("hosts.h3", this.host3);
        this.props.put("host-selector", "random");
        this.props.put("backoff", "true");
        this.props.put("maxBackoff", "10000");
        this.client = RpcClientFactory.getInstance(this.props);
    }

    public void sendDataToFlume(String data) {
        Event event =  EventBuilder.withBody(data, Charset.forName("UTF-8"));
        try {
            client.append(event);
        } catch (EventDeliveryException e) {
            client.close();
            client = null;
//            client = RpcClientFactory.getDefaultInstance(hostname, port);
            client = RpcClientFactory.getInstance(this.props);
        }
    }

    public void cleanUp() {
        client.close();
    }
}
