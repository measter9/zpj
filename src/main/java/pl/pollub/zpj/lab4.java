package pl.pollub.zpj;

import pl.pollub.zpj.DTOs.KamperDTO;
import pl.pollub.zpj.models.Inspection;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.models.Price;

import java.sql.Date;

public class lab4 {
    public static void main(String[] args) {
        Kamper kamper = new Kamper(1,"Test",123);
        Inspection inspection = Inspection.builder()
                .ValidUntil(new Date(System.currentTimeMillis()))
                .name("Przegląd")
                .description("przegląd okresowy")
                .kamper(kamper)
                .build();
        System.out.println(inspection);

        System.out.println(Price.builder()
                .price(123)
                .kamper(kamper)
                .kamper(kamper)
                .build());


        KamperDTO kamperDTO = new KamperDTO("test2",123);


    }
}
