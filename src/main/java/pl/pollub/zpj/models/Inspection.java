package pl.pollub.zpj.models;

import lombok.Builder;

import java.sql.Date;
import java.util.List;

@Builder
public class Inspection {
        private int Id;
        private String name;
        private Date ValidUntil;
        private String description;
        private List<Kamper> kamperList;

}
