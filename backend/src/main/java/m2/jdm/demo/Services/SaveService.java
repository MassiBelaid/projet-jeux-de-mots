package m2.jdm.demo.Services;

import m2.jdm.demo.Models.Relation;
import m2.jdm.demo.Models.Terme;
import m2.jdm.demo.Repositories.RelationRepository;
import m2.jdm.demo.Repositories.TermeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SaveService implements Runnable{
    private List<Relation> relationsToSave;
    private List<Terme> termesToSave;

    @Autowired
    private RelationRepository relationRepository;

    @Autowired
    private TermeRepository termeRepository;

    public SaveService(List<Relation> listRel, List<Terme> lisTerm){
        this.relationsToSave = listRel;
        this.termesToSave = lisTerm;
    }

    public void run () {
        System.out.println("DEBUT THREAD");
        for(Relation r : this.relationsToSave) {
            //System.out.println(r.getId());
            try {
                relationRepository.save(r);
            }catch (NullPointerException e){
                System.out.println("relation null");
            }
        }
        for (Terme t : this.termesToSave) {
            //System.out.println(t.getId());
            try{
                termeRepository.save(t);
            }catch (NullPointerException e){
                System.out.println("Terme null");
            }

        }
        System.out.println("FIN");
    }


}
