package client;

import codec.StringCodecFactory;
import handler.TimeClientHandler;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * Created by root on 17-12-13.
 */
public class TimeClient {
    private int port;
    private String host;

    public TimeClient(String host,int port){
        this.host = host;
        this.port = port;
    }

    public void run(){
        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("logger",new LoggingFilter());
        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new StringCodecFactory()));
        connector.setHandler(new TimeClientHandler());
        connector.connect(new InetSocketAddress(host,port));
    }
}
