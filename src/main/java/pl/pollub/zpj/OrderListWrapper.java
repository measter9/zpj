package pl.pollub.zpj;

import pl.pollub.zpj.models.Orders;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Orders")
public class OrderListWrapper {
    private List<Orders> orders = new ArrayList<>();
    public OrderListWrapper() {
        // Required by JAXB
    }
    @XmlElement(name = "Order")
    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
