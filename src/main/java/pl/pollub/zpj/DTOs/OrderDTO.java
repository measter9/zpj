package pl.pollub.zpj.DTOs;

import lombok.*;
import pl.pollub.zpj.models.Orders;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    final private @NonNull int kamperid;
    final @NonNull int duration;

    public OrderDTO() {
        this.kamperid = 0;
        this.duration = 0;
    }
    public OrderDTO(Orders order) {
        this.kamperid = order.getKamper().getId();
        this.duration = order.getDuration();
    }

    public Orders toEntity() {
        Orders order = new Orders();
//      order.setKamper(this.kamper);
        order.setDuration(this.duration);
        return order;
    }
}
