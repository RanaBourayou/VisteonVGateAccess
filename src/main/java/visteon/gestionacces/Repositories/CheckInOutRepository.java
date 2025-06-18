package visteon.gestionacces.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import visteon.gestionacces.Entities.CheckInOut;

public interface CheckInOutRepository extends JpaRepository<CheckInOut, Integer> {
}
