import com.chen.leo.session.GenericSessionManager;
import com.chen.leo.session.SessionManager;
import com.chen.leo.transport.NettyTransportServer;

public class ServerTestCase {

    public static void main(String[] args) {
        NettyTransportServer transportServer = new NettyTransportServer(new SimpleTransportEventProxy());

        SessionManager sessionManager = new GenericSessionManager();
        transportServer.setSessionManager(sessionManager);

        transportServer.init(7000);
        transportServer.start(true);

    }

}
