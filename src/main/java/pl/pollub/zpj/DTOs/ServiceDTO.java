package pl.pollub.zpj.DTOs;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.zpj.models.Service;

import java.util.Date;

@Getter
@Setter
public class ServiceDTO{
    private String Name;
    private String Description;
    private double price;
    private Date date;
    private int kamperid;

    public Service toEntity(){
        return Service.builder()
                .Name(getName())
                .Description(getDescription())
                .price(getPrice())
                .date(getDate())
                .build();
    }
}
