package m2.jdm.demo.Repositories;

import m2.jdm.demo.Models.Terme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermeRepository extends JpaRepository<Terme, Long> {
}
