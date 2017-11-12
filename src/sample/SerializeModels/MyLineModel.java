package sample.SerializeModels;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by admin on 26.10.17.
 * МОДЕЛЬ ДЛЯ СЕРИАЛИЗАЦИИ СОЕДИНИТЕЛЬНЫХ ЛИНИЙ
 */
public class MyLineModel implements Serializable {

    private   String startId = new String();
    private   String endId = new String();
    private   int startType;
    private int endType;
    private int lineType;

    public MyLineModel(){}

    public MyLineModel(String startId, String endId, int startType, int endType, int lineType) {
        this.startId = startId;
        this.endId = endId;
        this.startType = startType;
        this.endType = endType;
        this.lineType = lineType;
    }
    @XmlElement(name = "startId")
    public String getStartId() {
        return startId;
    }

    public void setStartId(String startId) {
        this.startId = startId;
    }
    @XmlElement(name = "endId")
    public String getEndId() {
        return endId;
    }

    public void setEndId(String endId) {
        this.endId = endId;
    }
    @XmlElement(name = "startType")
    public int getStartType() {
        return startType;
    }

    public void setStartType(int startType) {
        this.startType = startType;
    }
    @XmlElement(name = "endType")
    public int getEndType() {
        return endType;
    }

    public void setEndType(int endType) {
        this.endType = endType;
    }
    @XmlElement(name = "lineType")
    public int getLineType() {
        return lineType;
    }

    public void setLineType(int lineType) {
        this.lineType = lineType;
    }
}
