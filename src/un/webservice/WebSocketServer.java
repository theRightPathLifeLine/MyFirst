package un.webservice;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by admin on 2018/4/13.
 */
@ServerEndpoint(value = "/msgServer")
public class WebSocketServer {

    private static Logger log = Logger.getLogger(WebSocketServer.class);

    public static Session session = null;

    @OnMessage
    public void onMessage(String msg){
        sendMsg(msg);
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
//        WebSocketServer.sendMsg(new AnimoteStaObj(AnimoteSocketServer.isOpen).toString());
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        this.session = null;
    }

    public static void sendMsg(String msg) {
        log.info(msg);
        if (session != null) {
            synchronized (session) {
                try {
                    session.getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendConsoleMsg(String str, String color) {
//        sendMsg(new ConsoleDataObj(str, color, MsgType.CONSOLE_MSG).toString());

    }

}
