package pl.pollub.zpj;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Order")
public class Order {
    @Getter @Setter
    private int orderId;
    @Getter @Setter
    private Kamper kamper;
    @Getter @Setter
    private int duration;
    public Order() {

    }

    public Order(int orderId, Kamper kamper,int duration) {
        this.orderId = orderId;
        this.kamper = kamper;
        this.duration = duration;
    }
    @Override
    public String toString() {
        return "Order:" +
                "orderId=" + orderId +
                ", kamper=" + kamper +
                ", duration=" + duration +" days"+
                '}';
    }
}
