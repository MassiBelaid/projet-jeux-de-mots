package m2.jdm.demo.Repositories;

import m2.jdm.demo.Models.Relation;
import m2.jdm.demo.Models.Terme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationRepository extends JpaRepository<Relation, Long> {

    List<Relation> findByTerme1OrTerme2(Terme terme1, Terme terme2);
}
