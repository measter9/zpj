package pl.pollub.zpj.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.pollub.zpj.DTOs.InspectionDto;
import pl.pollub.zpj.models.Inspection;
import pl.pollub.zpj.repository.InspectionRepostiory;
import pl.pollub.zpj.repository.KamperRepository;

@Service
@RequiredArgsConstructor
public class InspectionService {
    @Autowired
    InspectionRepostiory inspectionRepostiory;
    @Autowired
    KamperRepository kamperRepository;
    public Iterable<Inspection> findByKamperId(int id) {
        return inspectionRepostiory.findByKamperId(id);
    }
    public Iterable<Inspection> findAll() {
        return inspectionRepostiory.findAll();
    }
    public Inspection createInspection(InspectionDto inspectionDto) {
        Inspection i = inspectionDto.toEntity();
        i.setKamper(kamperRepository.findById(inspectionDto.getKamperId()));
        inspectionRepostiory.save(i);
        return i;
    }

    public String deleteInspection(int id) throws ChangeSetPersister.NotFoundException {
        inspectionRepostiory.delete(inspectionRepostiory.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
        return "Success";
    }

    public Inspection updateInspection(int id, InspectionDto inspectionDto) throws ChangeSetPersister.NotFoundException {
        Inspection i = inspectionRepostiory.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        inspectionRepostiory.delete(i);
        i.setDescription(inspectionDto.getDescription());
        i.setName(inspectionDto.getName());
        i.setValidUntil(inspectionDto.getValidUntil());
        i.setId(id);
        i.setKamper( kamperRepository.findById(inspectionDto.getKamperId()));
        inspectionRepostiory.save(i);
        return i;
    }
}
