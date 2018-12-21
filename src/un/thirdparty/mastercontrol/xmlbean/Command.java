package un.thirdparty.mastercontrol.xmlbean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 2018/12/21.
 */
@XmlRootElement(name="COMMAND")
public class Command {


    private int cmdID;

    /**
     * CONNECT              连接建立
     * CLOSE                连接关闭
     * HEART_BEAT           心疼
     * READY                就绪通知
     * FINISHED             放行
     * BASE_POINT_CORRECT   坐标标定
     * CAMERA_MOVE          相机移位
     * LIGHT_CONTROL        光源控制
     * COMMAND_FINISHED     命令完成
     * MESSAGE              消息传递
     * TRIAL_RESULT         检测数据上传
     */
    private String cmdName;


    private CmdParams cmdParams;

    public int getCmdID() {
        return cmdID;
    }

    @XmlElement(name="COMMAND_ID")
    public void setCmdID(int cmdID) {
        this.cmdID = cmdID;
    }

    public String getCmdName() {
        return cmdName;
    }

    @XmlElement(name="COMMAND_NAME")
    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public CmdParams getCmdParams() {
        return cmdParams;
    }

    @XmlElement(name="COMMAND_PARAMS")
    public void setCmdParams(CmdParams cmdParams) {
        this.cmdParams = cmdParams;
    }

    @Override
    public String toString() {
        return "Command{" +
                "cmdID=" + cmdID +
                ", cmdName='" + cmdName + '\'' +
                ", cmdParams=" + cmdParams +
                '}';
    }
}
