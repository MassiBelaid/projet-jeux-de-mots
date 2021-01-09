package m2.jdm.demo.Services;

import java.io.IOException;
import java.util.ArrayList;

public interface JDMService {
    ArrayList<String> extract(String terme) throws IOException;
}
