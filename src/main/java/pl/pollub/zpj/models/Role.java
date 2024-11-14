package pl.pollub.zpj.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public enum Role{
    CUSTOMER,
    ADMIN,
    EMPLOYEE
}
