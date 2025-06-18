package visteon.gestionacces.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import visteon.gestionacces.Entities.VisitRequest;
import visteon.gestionacces.IServices.IVisitRequestServices;

import java.util.List;

@RestController
@RequestMapping("/api/visit-requests")
public class VisitRequestController {
    private final IVisitRequestServices service;
    public VisitRequestController(IVisitRequestServices service) { this.service = service; }

    @PostMapping
    public ResponseEntity<VisitRequest> create(@RequestBody VisitRequest req) {
        return new ResponseEntity<>(service.create(req), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public VisitRequest getById(@PathVariable Long id) { return service.findById(id); }

    @GetMapping
    public List<VisitRequest> getAll() { return service.findAll(); }

    @PutMapping("/{id}")
    public VisitRequest update(@PathVariable Long id, @RequestBody VisitRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{requestId}/assign/{visitorId}")
    public VisitRequest assignVisitor(@PathVariable Long requestId, @PathVariable Long visitorId) {
        return service.assignVisitor(requestId, visitorId);
    }
    @GetMapping("/by-requester/{userId}")
    public List<VisitRequest> getByRequesterId(@PathVariable Long userId) {
        return service.getRequestsByRequesterId(userId);
    }

}
