package pl.pollub.zpj.DTOs;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.models.Order;

@RequiredArgsConstructor
@Getter
@ToString
public class OrderDTO {
    final private @NonNull Kamper kamper;
    final @NonNull int duration;

    public OrderDTO(Order order) {
        this.kamper = order.getKamper();
        this.duration = order.getDuration();
    }

    public Order toEntity() {
        Order order = new Order();
        order.setKamper(this.kamper);
        order.setDuration(this.duration);
        return order;
    }
}
