package pl.pollub.zpj.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@ToString
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Price {
    @Id
    @GeneratedValue
    private int Id;
    private double price;
    private Date begins;
    private Date ends;
    @Singular("kamper")
    @OneToMany
    List<Kamper> kamperList;
}
