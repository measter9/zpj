package pl.pollub.zpj.DTOs;


import lombok.Getter;
import lombok.Setter;
import pl.pollub.zpj.models.Price;

import java.sql.Date;
@Getter @Setter
public class PriceDTO {
    private double price;
    private Date begins;
    private Date ends;

    public Price toEntity(){
        return Price.builder()
                .price(getPrice())
                .begins(getBegins())
                .ends(getEnds())
                .build();
    }
}
