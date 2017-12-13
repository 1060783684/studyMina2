package handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Created by root on 17-12-13.
 */
public class TimeClientHandler extends IoHandlerAdapter {
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        String body = "query";
        session.write(body);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.err.println("Mina client exception!");
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String data = message.toString();
        System.out.println(data);

        if(!data.equalsIgnoreCase("bye")) {
            String closeData = "quit";
            session.write("quit");
        }else {
            session.closeOnFlush();
            System.exit(0);
        }
    }
}
