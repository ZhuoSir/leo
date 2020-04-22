import com.chen.leo.client.NettyTransportClient;
import com.chen.leo.client.Session;
import com.chen.leo.client.TransportClient;
import com.chen.leo.exeception.ServerConnectionException;
import com.chen.leo.proto.TransportRequest;

public class ClientTestCase {


    public static void main(String[] args) {

        TransportClient transportClient = new NettyTransportClient();
        transportClient.init(1000);
        try {
            Session session = transportClient.connect("127.0.0.1", 7000, true);

            Thread.sleep(5000);

            TransportRequest.Builder builder = new TransportRequest.Builder();
            builder.cmd("CMD");
            builder.header("content-format", "application/json");
            builder.body("name=chen&age=18");

            session.writeAndFlush(builder.build());

        } catch (ServerConnectionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
