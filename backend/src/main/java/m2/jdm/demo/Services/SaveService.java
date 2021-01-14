package m2.jdm.demo.Services;

import m2.jdm.demo.Models.Relation;
import m2.jdm.demo.Models.Terme;
import m2.jdm.demo.Repositories.RelationRepository;
import m2.jdm.demo.Repositories.TermeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveService implements Runnable{
    private List<Relation> relationsToSave;
    private List<Terme> termesToSave;

    @Autowired
    private RelationRepository relationRepository;

    @Autowired
    private TermeRepository termeRepository;


    public void run () {
        System.out.println("DEBUT THREAD");

        /*for (Terme t : this.termesToSave) {
            //System.out.println(t.getId());
            try{
                termeRepository.save(t);
            }catch (NullPointerException e){
                System.out.println("Terme null: "+t.getId());
            }

        }*/

        for(Relation r : this.relationsToSave) {
            //System.out.println(r.getId());
            try {
                //relationRepository.save(r);

                termeRepository.save(r.getTerme1());
                termeRepository.save(r.getTerme2());
                relationRepository.save(r);
            }catch (NullPointerException e){
                System.out.println("relation null: "+r.getId());
            }
        }

        System.out.println("FIN");
    }

    public void setRelationsToSave(List<Relation> relationsToSave) {
        this.relationsToSave = relationsToSave;
    }

    public void setTermesToSave(List<Terme> termesToSave) {
        this.termesToSave = termesToSave;
    }
}
