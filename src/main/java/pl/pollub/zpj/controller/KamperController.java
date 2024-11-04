package pl.pollub.zpj.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.services.KamperService;

import java.util.List;

@RestController
@RequestMapping("/kampery")
public class KamperController {

    private final KamperService kamperService;

    @Autowired
    public KamperController(KamperService kamperService) {
        this.kamperService = kamperService;
    }

    @PostMapping
    public Kamper createKamper(@RequestBody Kamper kamper) {
        return kamperService.saveKamper(kamper);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kamper> getKamperById(@PathVariable int id) {
        Kamper kamper = kamperService.getKamperById(id);
        return kamper != null ? ResponseEntity.ok(kamper) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Kamper> getAllKampers() {
        return kamperService.getAllKampers();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Kamper> updateKamper(@PathVariable int id, @RequestBody Kamper kamperDetails) {
        kamperService.updateKamper(id, kamperDetails);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKamper(@PathVariable int id) {
        kamperService.deleteKamperById(id);
        return ResponseEntity.noContent().build();
    }
}