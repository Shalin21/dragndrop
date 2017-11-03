package sample.SerializeModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by admin on 25.10.17.
 */
@XmlRootElement(name = "Collection1")
public class MyLineCollection {

    private ObservableList<MyLineModel> collection = FXCollections.observableArrayList();


    public void add(MyLineModel error) {
        collection.add(error);
    }


    public void delete(MyLineModel error) {
        collection.remove(error);
    }


    public void update(MyLineModel error) {

    }
    @XmlElement(name = "MyLine")
    public ObservableList<MyLineModel> getCollection() {
        return collection;
    }

    public void setCollection(ObservableList<MyLineModel> collection) {
        this.collection = collection;
    }
}
