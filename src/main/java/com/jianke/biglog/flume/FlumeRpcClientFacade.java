package com.jianke.biglog.flume;

import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;

public class FlumeRpcClientFacade {
    private RpcClient client;
    private Properties props;
    private static String host1 = "172.21.57.149:41416";
    private static String host2 = "172.21.57.148:41416";
    private static String host3 = "172.21.57.147:41416";

    public void init() {
        this.props = new Properties();
        this.props.put("client.type", "default_loadbalance");
        this.props.put("hosts", "h1 h2 h3");
        this.props.put("hosts.h1", this.host1);
        this.props.put("hosts.h2", this.host2);
        this.props.put("hosts.h3", this.host3);
        this.props.put("host-selector", "random");
        this.props.put("backoff", "true");
        this.props.put("maxBackoff", "10000");
        this.props.put("batch-size", "100");
        this.client = RpcClientFactory.getInstance(this.props);
    }

    public void sendDataToFlume(String data) {
        Event event =  EventBuilder.withBody(data, Charset.forName("UTF-8"));
        try {
            client.append(event);
        } catch (EventDeliveryException e) {
            client.close();
            client = null;
            client = RpcClientFactory.getInstance(this.props);
        }
    }

    public void cleanUp() {
        client.close();
    }
}
