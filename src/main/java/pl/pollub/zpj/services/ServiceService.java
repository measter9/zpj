package pl.pollub.zpj.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.pollub.zpj.DTOs.ServiceDTO;
import pl.pollub.zpj.repository.KamperRepository;
import pl.pollub.zpj.repository.ServiceRepository;

@Service
@RequiredArgsConstructor
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    KamperRepository kamperRepository;

    public Iterable<pl.pollub.zpj.models.Service> findAll(){
        return serviceRepository.findAll();
    }

    public pl.pollub.zpj.models.Service createService(ServiceDTO serviceDTO){
        pl.pollub.zpj.models.Service s = serviceDTO.toEntity();
        s.setKamper(kamperRepository.findById(serviceDTO.getKamperid()));
        serviceRepository.save(s);
        return s;
    }

    public pl.pollub.zpj.models.Service updateService(int id, ServiceDTO serviceDTO) throws ChangeSetPersister.NotFoundException {
        pl.pollub.zpj.models.Service s = serviceRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        s.setKamper(kamperRepository.findById(serviceDTO.getKamperid()));
        s.setDate(serviceDTO.getDate());
        s.setName(serviceDTO.getName());
        s.setDescription(serviceDTO.getDescription());
        s.setPrice(serviceDTO.getPrice());

        serviceRepository.save(s);
        return s;

    }

    public String deleteService(int id) throws ChangeSetPersister.NotFoundException {
        serviceRepository.delete( serviceRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
        return "Success";
    }

}
