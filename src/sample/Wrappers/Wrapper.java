package sample.Wrappers;

/**
 * Created by Alex on 28.03.2017.
 */



import sample.SerializeModels.MyCircleCollection;

import javax.xml.bind.annotation.XmlAnyElement;

public class Wrapper<T> {

    private MyCircleCollection list;



    public Wrapper(MyCircleCollection list) {
        this.list = list;
    }

    @XmlAnyElement(lax = true)
    public MyCircleCollection getItems() {
        return list;
    }

}