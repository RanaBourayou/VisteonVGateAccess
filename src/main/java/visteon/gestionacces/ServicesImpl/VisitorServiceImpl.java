package visteon.gestionacces.ServicesImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import visteon.gestionacces.Entities.Visitor;
import visteon.gestionacces.IServices.IVisitorServices;
import visteon.gestionacces.Repositories.VisitorRepository;

import java.util.List;

@Service
public class VisitorServiceImpl implements IVisitorServices {
    private final VisitorRepository repo;
    public VisitorServiceImpl(VisitorRepository repo) { this.repo = repo; }

    @Override
    public Visitor create(Visitor visitor) { return repo.save(visitor); }

    @Override
    public Visitor findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Visitor not found"));
    }

    @Override
    public List<Visitor> findAll() { return repo.findAll(); }

    @Override
    public Visitor update(Long id, Visitor visitor) {
        Visitor existing = findById(id);
        BeanUtils.copyProperties(visitor, existing, "idVisitor");
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) { repo.deleteById(id); }
}
