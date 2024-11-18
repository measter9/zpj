package pl.pollub.zpj.repository;

import org.springframework.data.repository.CrudRepository;

import pl.pollub.zpj.models.Price;

public interface PriceRepository extends CrudRepository<Price, Integer> {
}
