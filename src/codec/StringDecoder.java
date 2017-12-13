package codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by root on 17-12-13.
 */
public class StringDecoder extends ProtocolDecoderAdapter {
    private String charset;

    public StringDecoder(String charset){
        this.charset = charset;
    }

    //这里和mina 1.x有所不同,这里的的IoBuffer在1.x中为ByteBuffer
    @Override
    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {

        byte[] data = new byte[ioBuffer.limit()];

        ioBuffer.get(data,0,data.length);
        String body = new String(data,"UTF-8");
        //注意,这里用的是out对象而不是session对象,若使用session对象就会将消息写出,而透传不到handler
        out.write(body);
    }
}
