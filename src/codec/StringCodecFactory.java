package codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by root on 17-12-13.
 */
public class StringCodecFactory implements ProtocolCodecFactory {
    private static StringEncoder ENCODER = new StringEncoder();
    private static String defaultCharset = "UTF-8";
    private String charset = defaultCharset;

    public StringCodecFactory(){

    }

    public StringCodecFactory(String charset){
        this.charset = charset;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return ENCODER;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return new StringDecoder(charset);
    }
}
