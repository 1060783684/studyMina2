import server.TimeServer;

/**
 * Created by root on 17-12-13.
 */
public class TestServer {
    public static void main(String[] args){
        TimeServer server = new TimeServer(9999);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
