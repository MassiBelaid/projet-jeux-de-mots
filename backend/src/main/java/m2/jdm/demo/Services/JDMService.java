package m2.jdm.demo.Services;

import m2.jdm.demo.Models.Terme;


import java.io.IOException;
import java.util.Collection;

public interface JDMService {
    Collection<Terme> extract(String terme) throws IOException;
}
