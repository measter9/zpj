package pl.pollub.zpj.models;

import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Builder
@ToString
public class Service {
    private int Id;
    private String Name;
    private String Description;
    private double price;
    private Date date;
    private Kamper kamper;
}
