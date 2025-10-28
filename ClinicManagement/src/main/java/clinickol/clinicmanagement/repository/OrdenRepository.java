package clinickol.clinicmanagement.repository;

import clinickol.clinicmanagement.domain.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, String> {
    List<Orden> findByCedulaPaciente(String cedulaPaciente);

    List<Orden> findByCedulaMedico(String cedulaMedico);
}
