package m2.jdm.demo.customRepository;

import m2.jdm.demo.Models.Terme;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class TermeRepositoryCustomImpl implements TermeRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Terme> getWithMultCriteres(String entreeUtilisateur) {
        entreeUtilisateur = entreeUtilisateur+'%';
        Query query = entityManager.createQuery("SELECT t FROM terme t WHERE t.terme LIKE ?1", Terme.class);
        query.setParameter(1,entreeUtilisateur);
        return query.getResultList();
    }
}
