package pl.pollub.zpj.models;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Kamper")
public class Kamper {
    private int id;
    private @NonNull String name;
    private  double price;

    @Override
    public String toString() {
        return "Kamper{" +
                "id= " + id +
                ", name= '" + name + ", price= '" + price + '\'' +
                '}';
    }
}

