package pl.pollub.zpj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Kamper")
public class Kamper {
    private int id;
    private String name;
    private double price;

    @Override
    public String toString() {
        return "Kamper{" +
                "id= " + id +
                ", name= '" + name + ", price= '" + price + '\'' +
                '}';
    }
}

