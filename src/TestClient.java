import client.TimeClient;

/**
 * Created by root on 17-12-13.
 */
public class TestClient {
    public static void main(String[] args){
        TimeClient client = new TimeClient("127.0.0.1",9999);
        client.run();
    }
}
