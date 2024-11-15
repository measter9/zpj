package pl.pollub.zpj.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.pollub.zpj.DTOs.PriceDTO;
import pl.pollub.zpj.models.Price;
import pl.pollub.zpj.repository.PriceRepository;

@Service
@RequiredArgsConstructor
public class PriceService {
    @Autowired
    PriceRepository priceRepository;

    public Iterable<Price> getAll(){
        return priceRepository.findAll();
    }

    public Price createPrice(PriceDTO priceDTO){
        priceRepository.save(priceDTO.toEntity());
        return priceDTO.toEntity();
    }

    public Price updatePric(int id, PriceDTO priceDTO) throws ChangeSetPersister.NotFoundException {
        Price p = priceRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        p.setPrice(priceDTO.getPrice());
        p.setEnds(priceDTO.getEnds());
        p.setBegins(priceDTO.getBegins());
        priceRepository.save(p);
        return p;
    }

    public String deletePrice(int id) throws ChangeSetPersister.NotFoundException {
        priceRepository.delete(priceRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
        return "Success";
    }
}
