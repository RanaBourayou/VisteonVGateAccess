package visteon.gestionacces.IServices;

import visteon.gestionacces.Entities.VisitRequest;

import java.util.List;

public interface IVisitRequestServices {

    VisitRequest create(VisitRequest request);
    VisitRequest findById(Long id);
    List<VisitRequest> findAll();
    VisitRequest update(Long id, VisitRequest request);
    void delete(Long id);
    VisitRequest assignVisitor(Long requestId, Long visitorId);
    List<VisitRequest> getRequestsByRequesterId(Long userId);

}
