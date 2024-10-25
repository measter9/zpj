package pl.pollub.zpj;

import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Orders")
public class OrderListWrapper {
    private List<Order> orders = new ArrayList<>();
    public OrderListWrapper() {
        // Required by JAXB
    }
    @XmlElement(name = "Order")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
