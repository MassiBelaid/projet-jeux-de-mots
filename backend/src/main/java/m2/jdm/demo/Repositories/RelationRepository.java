package m2.jdm.demo.Repositories;

import m2.jdm.demo.Models.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<Relation, Long> {
}
