package un.thirdparty.mastercontrol;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.springframework.stereotype.Service;
import un.thirdparty.mastercontrol.xmlbean.Command;
import un.thirdparty.mastercontrol.xmlbean.Param;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;

/**
 * Created by admin on 2018/12/19.
 */
@Service
public class ConnectMaster extends IoHandlerAdapter {

    private static Logger log = Logger.getLogger(ConnectMaster.class);

    private IoConnector connector;
    private IoSession session;

    private String ip = "127.0.0.1";

    private int port=12036;

    public IoConnector getConnector() {
        return connector;
    }

    public void setConnector(IoConnector connector) {
        this.connector = connector;
    }

    public  IoSession getSession() {
        return session;
    }

    public  void setSession(IoSession session) {
        this.session = session;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

//    @PostConstruct
    public ConnectMaster(){
        log.info("连接主控系统 --" + ip + ":" + port);
        connector = new NioSocketConnector();
        connector.setHandler(this);
        ConnectFuture connFuture = connector.connect(new InetSocketAddress(ip, port));
        connFuture.awaitUninterruptibly();
        session = connFuture.getSession();
        Param param = new Param();
        param.setFromStationID(12);
        param.setToStationID(21);
        Command command = new Command();
        command.setCmdID(1);
        command.setCmdName("CONNECT");
        param.setCommand(command);
        try {
            JAXBContext context = JAXBContext.newInstance(Param.class);    // 获取上下文对象
            Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象

            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            marshaller.marshal(param, baos);
            IoBuffer buffer = IoBuffer.allocate(baos.size());
            buffer.put(baos.toByteArray());
            buffer.flip();
            String xmlObj = new String(baos.toByteArray());
            session.write(buffer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ConnectMaster client = new ConnectMaster();
//        client.connectMaster();
    }

    @Override
    public void sessionCreated(IoSession iosession) throws Exception {
        log.info("客户端会话创建");
        super.sessionCreated(iosession);
    }
    @Override
    public void sessionOpened(IoSession iosession) throws Exception {
        log.info("客户端会话打开");
        super.sessionOpened(iosession);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        IoBuffer bbuf = (IoBuffer) message;
        byte[] byten = new byte[bbuf.limit()];
        bbuf.get(byten, bbuf.position(), bbuf.limit());
        String xml = new String(byten);
        log.info("发送消息:\n" + xml);
        super.messageSent(session, message);
    }

    @Override
    public void messageReceived(IoSession iosession, Object message)
            throws Exception {
        IoBuffer bbuf = (IoBuffer) message;
        byte[] byten = new byte[bbuf.limit()];
        bbuf.get(byten, bbuf.position(), bbuf.limit());
        String xml = new String(byten);
        log.info("接收主控消息：\n" + xml);
//        getXmlAttribute(xml);
    }



}
