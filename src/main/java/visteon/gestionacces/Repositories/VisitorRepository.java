package visteon.gestionacces.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import visteon.gestionacces.Entities.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
