package pl.pollub.zpj;

import pl.pollub.zpj.DTOs.KamperDTO;
import pl.pollub.zpj.DTOs.OrderDTO;
import pl.pollub.zpj.DTOs.UserDTO;
import pl.pollub.zpj.models.*;

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

        System.out.println(Service.builder()
                .price(321.1)
                .date(new Date(System.currentTimeMillis()))
                .Name("Naprawa")
                .kamper(kamper)
                .Description("Prosta naprawa kamepra")
                .build());

        Order order = new Order(12,kamper,12);
        KamperDTO kamperDTO = new KamperDTO(kamper);
        System.out.println(kamperDTO);
        Kamper k2 = kamperDTO.toEntity();
        System.out.println(k2);

        OrderDTO orderDTO = new OrderDTO(order);
        System.out.println(orderDTO);
        System.out.println(orderDTO.toEntity());

        User user = new User(1,"username");
        UserDTO userDTO = new UserDTO(user);
        System.out.println(userDTO);
        System.out.println(userDTO.toEntity());


    }
}
