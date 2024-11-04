package pl.pollub.zpj.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.repository.KamperRepository;

import java.util.List;

@Service
public class KamperService {

    private final KamperRepository kamperRepository;

    @Autowired
    public KamperService(KamperRepository kamperRepository) {
        this.kamperRepository = kamperRepository;
    }

    public Kamper saveKamper(Kamper kamper) {
        return kamperRepository.save(kamper);
    }

    public Kamper getKamperById(int id) {
        return kamperRepository.findById(id);
    }

    public int updateKamper(int id, Kamper kamper) {
        return kamperRepository.update(id, kamper);
    }
    public List<Kamper> getAllKampers() {
        return kamperRepository.findAll();
    }

    public void deleteKamperById(int id) {
        kamperRepository.deleteById(id);
    }
}