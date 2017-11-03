package sample.Wrappers;

/**
 * Created by Alex on 28.03.2017.
 */



import sample.SerializeModels.MyLineCollection;

import javax.xml.bind.annotation.XmlAnyElement;

public class Wrapper1<T> {

    private MyLineCollection list;



    public Wrapper1(MyLineCollection list) {
        this.list = list;
    }

    @XmlAnyElement(lax = true)
    public MyLineCollection getItems() {
        return list;
    }

}