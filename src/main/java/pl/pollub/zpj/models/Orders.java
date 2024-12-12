package pl.pollub.zpj.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Order")
public class Orders {
    @Id
    @GeneratedValue
    private int orderId;
    @ManyToOne
    private Kamper kamper;
    private int duration;

    @Override
    public String toString() {
        return "Order:" +
                "orderId=" + orderId +
                ", kamper=" + kamper +
                ", duration=" + duration +" days"+
                '}';
    }
}
