package pl.pollub.zpj.models;

import lombok.Builder;
import lombok.Singular;

import java.sql.Date;
import java.util.List;

@Builder
public class Price {
private int Id;
private double price;
private Date begins;
private Date ends;
@Singular("kamper")
List<Kamper> kamperList;
}
