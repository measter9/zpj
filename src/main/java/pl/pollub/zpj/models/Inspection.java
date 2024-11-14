package pl.pollub.zpj.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inspection {
        @Id
        @GeneratedValue
        private int Id;
        private String name;
        private Date ValidUntil;
        private String description;
        @ManyToOne
        private Kamper kamper;

}
