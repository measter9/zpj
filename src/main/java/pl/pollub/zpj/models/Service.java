package pl.pollub.zpj.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.util.Date;

@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue
    private int Id;
    private String Name;
    private String Description;
    private double price;
    private Date date;
    @ManyToOne
    private Kamper kamper;
}
