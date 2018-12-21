package un.thirdparty.mastercontrol.xmlbean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 2018/12/21.
 */
@XmlRootElement(name="LIGHT")
public class Light {


    private String position;


    private String on;

    public String getPosition() {
        return position;
    }

    @XmlAttribute(name="POSITION")
    public void setPosition(String position) {
        this.position = position;
    }

    public String getOn() {
        return on;
    }

    @XmlAttribute(name="ON")
    public void setOn(String on) {
        this.on = on;
    }

    @Override
    public String toString() {
        return "Light{" +
                "position='" + position + '\'' +
                ", on='" + on + '\'' +
                '}';
    }
}
