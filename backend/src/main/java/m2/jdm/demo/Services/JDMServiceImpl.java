package m2.jdm.demo.Services;

import m2.jdm.demo.Comparator.ComparatorRelation;
import m2.jdm.demo.Models.Relation;
import m2.jdm.demo.Models.Terme;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Service
public class JDMServiceImpl implements JDMService{
    public static final int POIDS_RELATION_MINIMUM = 5;
    public static final int POIDS_TERME_MINIMUM = 50;

    private List<Relation> relationsToSave = new ArrayList<>();
    private List<Terme> termesToSave = new ArrayList<Terme>();



    @Override
    public ArrayList<String> extract(String terme) throws IOException {

        //Contienderont les relations/termes récup de jeux de mots
        Map<Long, Terme> mapTemre = new HashMap<>();
        List< Relation> listRelation = new ArrayList<Relation>();


        //coder le terme avant la recherche
        terme = this.raffinementTerme(terme);
        System.out.println("Appel avec le terme  : "+terme);

        //Liste des terme en String pour l'affichage
        ArrayList<String> listTerme = new ArrayList<String>();


        //Lecture à partire d'une URL construite avec le terme
        String urlString = "http://www.jeuxdemots.org/rezo-dump.php?gotermsubmit=Chercher&gotermrel="+terme;
        URL url = new URL(urlString);

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String s;
        Boolean existeTerme = false;
        Terme firstTerme =null;

        //Lecture du code ligne par ligne
        while ((s = in.readLine()) != null) {

            //Recherche de la balise <CODE> qui stipule le début des termes/relations
            if(s.equals("<CODE>")){
                existeTerme = true;
            }

            //Si on est à l'interieur des termes/relation
            if(existeTerme) {

                //Si une ligne non vide
                if(!s.equals("")) {

                    //Ligne d'une relation
                    if(s.charAt(0) == 'r' && s.charAt(1) != 't') {
                        String[] detailelation = s.split(";");
                        if(Integer.parseInt(detailelation[5]) >= POIDS_TERME_MINIMUM ) {
                            Relation relation = new Relation();
                            Terme t1 = new Terme();
                            t1.setId(Integer.parseInt(detailelation[2]));
                            Terme t2 = new Terme();
                            t2.setId(Integer.parseInt(detailelation[3]));
                            relation.setTerme1(t1);
                            relation.setTerme2(t2);
                            relation.setPoids(Integer.parseInt(detailelation[5]));

                            listRelation.add(relation);
                        }

                        //Ligne d'un terme
                    }else if(s.charAt(0) == 'e') {
                        String[] detailTerme = s.split(";");
                        //Terme Lui même
                        String nom = detailTerme[2].substring(1, detailTerme[2].length()-1).toLowerCase();
                        if(nom.equals(terme)) {
                            firstTerme = new Terme();
                            firstTerme.setId(Integer.parseInt(detailTerme[1]));
                            firstTerme.setTerme(nom);

                            mapTemre.put(firstTerme.getId(), firstTerme);
                            //Ajout pour la sauvegarde dans la bdd
                            firstTerme.setImporte(true);
                            termesToSave.add(firstTerme);
                        }else {

                            Terme termeI = new Terme();
                            //Terme ne possède pas des raffinements
                            if(detailTerme.length > 0 && !detailTerme[2].contains("=") && !detailTerme[2].contains(">") && detailTerme[3].equals("1")) {

                                if(Integer.parseInt(detailTerme[4]) >= POIDS_TERME_MINIMUM) {
                                    //nom = detailTerme[2].substring(1, detailTerme[2].length()-1).toLowerCase();

                                    termeI.setId(Integer.parseInt(detailTerme[1]));
                                    termeI.setTerme(nom);
                                    mapTemre.put(termeI.getId(), termeI);
                                }

                                //terme avec raffinement
                            }else if(!detailTerme[2].contains("=") && detailTerme[3].equals("1")){
                                if(detailTerme.length == 6 ) {
                                    nom = detailTerme[5].substring(1, detailTerme[5].length()-1).toLowerCase();

                                    termeI.setId(Integer.parseInt(detailTerme[1]));
                                    termeI.setTerme(nom);
                                    mapTemre.put(termeI.getId(), termeI);
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Est-ce que le terme existe ? "+existeTerme);
        in.close();

        Collections.sort(listRelation, new ComparatorRelation());
        System.out.println("taille r : "+listRelation.size());
        System.out.println("taille t : "+mapTemre.size());
        for(Relation relation : listRelation) {
            if(mapTemre.containsKey(relation.getTerme1().getId()) && mapTemre.containsKey(relation.getTerme2().getId())) {
                Terme t1 = mapTemre.get(relation.getTerme1().getId());
                Terme t2 = mapTemre.get(relation.getTerme2().getId());


                //relationsToSave.add(new Relation(t1, t2, relation.getPoids()));
                if(t1.getTerme().equals(terme)) {
                    t1 = t2;
                }


                /*if(!(t1.getTerme().equals(terme))) {
                    //Ajouter tout terme différents de celui recherché dans la liste des termes a sauvegarder
                    //termeToSave.add(new Terme(t1.getId(), t1.getNom(), 0));
                }*/

                //Ajout dans la liste de string pour affichage si existe pas deja
                if(!listTerme.contains(t1.getTerme()))
                    listTerme.add(t1.getTerme());
            }
        }

        //Appel pour la sauvegarde dans la bdd
        //saveInBase(relationsToSave, termeToSave);

        System.out.println("taille retour : "+listTerme.size());
        //Retourne les termes en string pour l'affichage
        return listTerme;
    }



    //Methode qui régle le terme entré avant la recherche dans JeuxDeMot
    private String raffinementTerme(String terme) {
        terme = terme.toLowerCase();
        terme = terme.trim();
        terme = terme.replace("é","%E9").replace("è","%E8").replace("ê","%EA").replace("à","%E0").replace("ç","%E7").replace("û","%FB").replace(" ","+");

        return terme;
    }
}
