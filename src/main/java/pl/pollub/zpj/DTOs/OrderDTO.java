package pl.pollub.zpj.DTOs;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.models.Orders;

@RequiredArgsConstructor
@Getter
@ToString
public class OrderDTO {
    final private @NonNull Kamper kamper;
    final @NonNull int duration;

    public OrderDTO(Orders order) {
        this.kamper = order.getKamper();
        this.duration = order.getDuration();
    }

    public Orders toEntity() {
        Orders order = new Orders();
        order.setKamper(this.kamper);
        order.setDuration(this.duration);
        return order;
    }
}
