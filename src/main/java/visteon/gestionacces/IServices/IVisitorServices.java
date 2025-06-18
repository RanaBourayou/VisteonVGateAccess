package visteon.gestionacces.IServices;

import visteon.gestionacces.Entities.Visitor;

import java.util.List;

public interface IVisitorServices {
    Visitor create(Visitor visitor);
    Visitor findById(Long id);
    List<Visitor> findAll();
    Visitor update(Long id, Visitor visitor);
    void delete(Long id);
}
