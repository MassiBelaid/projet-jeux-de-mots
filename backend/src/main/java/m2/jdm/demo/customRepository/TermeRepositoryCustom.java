package m2.jdm.demo.customRepository;

import m2.jdm.demo.Models.Terme;

import java.util.List;

public interface TermeRepositoryCustom {
    List<Terme> getWithMultCriteres(String s);
}
