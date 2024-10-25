package pl.pollub.zpj.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Order")
public class Order {
    private int orderId;
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
