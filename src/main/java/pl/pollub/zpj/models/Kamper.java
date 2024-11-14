package pl.pollub.zpj.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Kamper")
@Table( name = "kampery")
public class Kamper implements Serializable {
    @Id
    @GeneratedValue
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

