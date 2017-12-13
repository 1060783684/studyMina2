package server;

import codec.StringCodecFactory;
import handler.TimeServiceHandler;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by root on 17-12-13.
 */
public class TimeServer {
    private int port;

    public TimeServer(int port){
        this.port = port;
    }

    public void run() throws Exception {
        IoBuffer.setUseDirectBuffer(false);
        IoBuffer.setAllocator(new SimpleBufferAllocator());

        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger",new LoggingFilter());
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new StringCodecFactory()));
        //与mina 1.x不同了,这里可以将handler和绑定端口分开写.
        acceptor.setHandler(new TimeServiceHandler());
        acceptor.bind(new InetSocketAddress(port));
    }
}
