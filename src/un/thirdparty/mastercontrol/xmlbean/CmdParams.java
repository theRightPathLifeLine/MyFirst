package un.thirdparty.mastercontrol.xmlbean;

import un.controller.Department;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/12/21.
 */
@XmlRootElement(name="COMMAND_PARAMS")
public class CmdParams {

    private Integer lightID;

    private List<Light> lights;

    private Integer cameraID;

    private Integer stepOne;

    private Integer stepTwo;

    private Double x;

    private Double y;

    private Double angle;

    private Integer finishedID;

    private String trialData;

    private String message;

    public int getLightID() {
        return lightID;
    }

    @XmlElement(name="LIGHT_ID")
    public void setLightID(int lightID) {
        this.lightID = lightID;
    }

    public List<Light> getLights() {
        return lights;
    }

    @XmlElement(name = "LIGHT")
    public void setLights(List<Light> lights) {
        this.lights = lights;
    }

    public Integer getCameraID() {
        return cameraID;
    }

    @XmlElement(name = "CAMER_ID")
    public void setCameraID(Integer cameraID) {
        this.cameraID = cameraID;
    }

    public Integer getStepOne() {
        return stepOne;
    }

    @XmlElement(name = "STEP_ONE")
    public void setStepOne(Integer stepOne) {
        this.stepOne = stepOne;
    }

    public Integer getStepTwo() {
        return stepTwo;
    }

    @XmlElement(name = "STEP_TWO")
    public void setStepTwo(Integer stepTwo) {
        this.stepTwo = stepTwo;
    }

    public Double getX() {
        return x;
    }

    @XmlElement(name = "X")
    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    @XmlElement(name = "Y")
    public void setY(Double y) {
        this.y = y;
    }

    public Double getAngle() {
        return angle;
    }

    @XmlElement(name = "ANGLE")
    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public Integer getFinishedID() {
        return finishedID;
    }

    @XmlElement(name = "FINISHED_COMMAND_ID")
    public void setFinishedID(Integer finishedID) {
        this.finishedID = finishedID;
    }

    public String getTrialData() {
        return trialData;
    }

    @XmlElement(name = "TRIAL_DATA")
    public void setTrialData(String trialData) {
        this.trialData = trialData;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement(name = "MESSAGE")
    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Param.class);    // 获取上下文对象
        Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象

        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进

//        marshaller.marshal(getSimpleDepartment(),System.out);   // 打印到控制台

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(getSimpleDepartment(), baos);
        String xmlObj = new String(baos.toByteArray());         // 生成XML字符串
        System.out.println(xmlObj);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ByteArrayInputStream in = new ByteArrayInputStream(xmlObj.getBytes());
        Param department = (Param)unmarshaller.unmarshal(in);
        System.out.println(department);
    }

    public static Param getSimpleDepartment(){
        Param param = new Param();
        param.setFromStationID(12);
        param.setToStationID(21);
        Command command = new Command();
        command.setCmdID(1);
        command.setCmdName("LIGHT_CONTROL");
        CmdParams lightParam = new CmdParams();
        lightParam.setLightID(1);
        Light light = new Light();
        light.setOn("1");
        light.setPosition("1");
        List<Light> lights = new ArrayList<>();
        lights.add(light);
        lightParam.setLights(lights);
        command.setCmdParams(lightParam);
        param.setCommand(command);
        return param;
    }

    @Override
    public String toString() {
        return "CmdParams{" +
                "lightID=" + lightID +
                ", lights=" + lights +
                ", cameraID=" + cameraID +
                ", stepOne=" + stepOne +
                ", stepTwo=" + stepTwo +
                ", x=" + x +
                ", y=" + y +
                ", angle=" + angle +
                ", finishedID=" + finishedID +
                ", trialData='" + trialData + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
