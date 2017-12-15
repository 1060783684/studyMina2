package codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Created by root on 17-12-13.
 */
public class StringEncoder extends ProtocolEncoderAdapter {
    @Override
    public void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput out) throws Exception {
        String body = obj.toString();

        byte[] data = body.getBytes();

        IoBuffer ioBuffer = IoBuffer.allocate(data.length);
        ioBuffer.put(data,0,data.length);
        ioBuffer.flip();
        //这里不能用ioSession.write,若使用ioSession.write消息会在这循环
        out.write(ioBuffer);
//        ioSession.write(data);
    }
}
