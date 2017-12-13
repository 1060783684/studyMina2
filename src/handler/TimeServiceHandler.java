package handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 17-12-13.
 */
public class TimeServiceHandler extends IoHandlerAdapter{
    private static int count;

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("Query client count : " + (++count));
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String body = message.toString();

        System.out.println(session.getRemoteAddress() + " message is : "+body);

        if(body.trim().equalsIgnoreCase("quit") ||
                body.trim().equalsIgnoreCase("quit\r") ||
                body.trim().equalsIgnoreCase("quit\n") ||
                body.trim().equalsIgnoreCase("quit\r\n") ||
                body.trim().equalsIgnoreCase("quit\n\r")){
            session.write("Bye");
            //在mina2.x中这个方法已过时
            //session.close();

            //所有排队的写入请求刷新后关闭此session
            //这个和下面的方法选一个
            session.closeOnFlush();

            //立即关闭此session
            //session.closeNow();
        }

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = dateFormat.format(date);

        session.write(formatDate);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.err.println("Mina service exception!");
        cause.printStackTrace();
    }
}
