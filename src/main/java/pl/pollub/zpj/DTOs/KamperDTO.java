package pl.pollub.zpj.DTOs;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KamperDTO {
    final private @NonNull String name;
    final private @NonNull double price;
}
