package pl.pollub.zpj.DTOs;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import pl.pollub.zpj.models.Inspection;

import java.sql.Date;
import java.sql.SQLOutput;

@Getter
public class InspectionDto {
    private String name;
    private Date validUntil;
    private String description;
    private int kamperId;

    public Inspection toEntity(){
        return Inspection.builder()
                .name(name)
                .ValidUntil(validUntil)
                .description(description)
                .build();
    }
}
