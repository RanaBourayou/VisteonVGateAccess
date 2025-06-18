package visteon.gestionacces.ServicesImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import visteon.gestionacces.Entities.VisitRequest;
import visteon.gestionacces.Entities.Visitor;
import visteon.gestionacces.IServices.IVisitRequestServices;
import visteon.gestionacces.Repositories.UserRepository;
import visteon.gestionacces.Repositories.VisitRequestRepository;
import visteon.gestionacces.Repositories.VisitorRepository;

import java.util.List;
@Service
public class VisitorRequestServiceImpl implements IVisitRequestServices {
    private final VisitRequestRepository reqRepo;
    private final VisitorRepository visRepo;
    private final UserRepository userRepo;

    public VisitorRequestServiceImpl(VisitRequestRepository reqRepo, VisitorRepository visRepo, UserRepository userRepo) {
        this.reqRepo = reqRepo;
        this.visRepo = visRepo;
        this.userRepo = userRepo;
    }

    @Override
    public VisitRequest create(VisitRequest request) {
        return reqRepo.save(request);
    }

    @Override
    public VisitRequest findById(Long id) {
        return reqRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("VisitRequest not found"));
    }

    @Override
    public List<VisitRequest> findAll() { return reqRepo.findAll(); }

    @Override
    public VisitRequest update(Long id, VisitRequest request) {
        VisitRequest existing = findById(id);
        BeanUtils.copyProperties(request, existing, "idVisitRequest");
        return reqRepo.save(existing);
    }

    @Override
    public void delete(Long id) { reqRepo.deleteById(id); }

    @Override
    public VisitRequest assignVisitor(Long requestId, Long visitorId) {
        VisitRequest vr = findById(requestId);
        Visitor v = visRepo.findById(visitorId)
                .orElseThrow(() -> new EntityNotFoundException("Visitor not found"));
        vr.setVisitor(v);
        return reqRepo.save(vr);
    }
    @Override
    public List<VisitRequest> getRequestsByRequesterId(Long userId) {
        return reqRepo.findByRequesterId(userId);
    }


}
