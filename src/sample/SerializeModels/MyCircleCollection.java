package sample.SerializeModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 25.10.17.
 */
@XmlRootElement(name = "Collection")
public class MyCircleCollection {

    private ObservableList<MyCircleModel> collection = FXCollections.observableArrayList();


    public void add(MyCircleModel error) {
        collection.add(error);
    }


    public void delete(MyCircleModel error) {
        collection.remove(error);
    }


    public void update(MyCircleModel error) {

    }
    @XmlElement(name = "MyCircle")
    public ObservableList<MyCircleModel> getCollection() {
        return collection;
    }

    public void setCollection(ObservableList<MyCircleModel> collection) {
        this.collection = collection;
    }
}
