package visteon.gestionacces.RestController;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import visteon.gestionacces.Entities.Visitor;
import visteon.gestionacces.IServices.IVisitorServices;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {
    private final IVisitorServices service;
    public VisitorController(IVisitorServices service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Visitor> create(@RequestBody Visitor visitor) {
        return new ResponseEntity<>(service.create(visitor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Visitor getById(@PathVariable Long id) { return service.findById(id); }

    @GetMapping
    public List<Visitor> getAll() { return service.findAll(); }

    @PutMapping("/{id}")
    public Visitor update(@PathVariable Long id, @RequestBody Visitor visitor) {
        return service.update(id, visitor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
