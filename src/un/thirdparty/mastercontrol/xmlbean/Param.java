package un.thirdparty.mastercontrol.xmlbean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 2018/12/21.
 */
@XmlRootElement(name="PARAM")
public class Param {


    private int fromStationID;


    private int toStationID;


    private Command command;

    public int getFromStationID() {
        return fromStationID;
    }

    @XmlElement(name="FROM_STATION_ID")
    public void setFromStationID(int fromStationID) {
        this.fromStationID = fromStationID;
    }

    public int getToStationID() {
        return toStationID;
    }

    @XmlElement(name="TO_STATION_ID")
    public void setToStationID(int toStationID) {
        this.toStationID = toStationID;
    }

    public Command getCommand() {
        return command;
    }

    @XmlElement(name="COMMAND")
    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "Param{" +
                "fromStationID=" + fromStationID +
                ", toStationID=" + toStationID +
                ", command=" + command +
                '}';
    }
}
