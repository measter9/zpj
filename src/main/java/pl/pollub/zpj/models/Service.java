package pl.pollub.zpj.models;

import lombok.Builder;
import lombok.Setter;

import java.util.Date;
@Builder
public class Service {
    private int Id;
    private String Name;
    private String Description;
    private double price;
    private Date date;
    private Kamper kamper;
}
