package pl.pollub.zpj.models;

import lombok.Builder;
import lombok.ToString;

import java.sql.Date;
import java.util.List;
@ToString
@Builder
public class Inspection {
        private int Id;
        private String name;
        private Date ValidUntil;
        private String description;
        private Kamper kamper;

}
