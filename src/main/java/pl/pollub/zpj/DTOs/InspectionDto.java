package pl.pollub.zpj.DTOs;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import pl.pollub.zpj.models.Inspection;

import java.sql.Date;

@Getter
public class InspectionDto {
    private String name;
    private Date ValidUntil;
    private String description;
    private int kamperId;

    public Inspection toEntity(){
        return Inspection.builder()
                .name(name)
                .ValidUntil(ValidUntil)
                .description(description)
                .build();
    }
}
