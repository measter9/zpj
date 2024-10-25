package pl.pollub.zpj;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Kamper")
public class Kamper {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double price;

    public Kamper() {

    }

    public Kamper(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    @Override
    public String toString() {
        return "Kamper{" +
                "id= " + id +
                ", name= '" + name + ", price= '" + price + '\'' +
                '}';
    }
}

