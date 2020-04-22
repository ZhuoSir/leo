import com.chen.leo.proto.TransportRequest;
import com.chen.leo.proxy.TransportEventProxy;
import com.chen.leo.session.Session;
import org.apache.log4j.Logger;

public class SimpleTransportEventProxy implements TransportEventProxy {

    private Logger logger = Logger.getLogger(SimpleTransportEventProxy.class);

    @Override
    public void connect(Session session) {
        logger.info(session.getId() + " connected...");
    }

    @Override
    public void transportRequest(Session session, TransportRequest transportRequest) {

        logger.info("received msg from " + session.getId());
        logger.info("request :" + transportRequest);
    }
}
