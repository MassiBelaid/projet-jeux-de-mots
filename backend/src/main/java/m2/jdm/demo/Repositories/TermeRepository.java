package m2.jdm.demo.Repositories;

import m2.jdm.demo.Models.Terme;
import m2.jdm.demo.customRepository.TermeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermeRepository extends JpaRepository<Terme, Long> , TermeRepositoryCustom {
    List<Terme> findByTermeAndImporte(String terme, boolean importe);
}
