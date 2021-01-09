package m2.jdm.demo.Controllers;

import m2.jdm.demo.Models.Terme;
import m2.jdm.demo.Services.JDMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/get")
public class MainControllers {

    @Autowired
    JDMService jdmService;


    @CrossOrigin(origins = "*")
    @GetMapping
    @RequestMapping("{terme}")
    public Collection<Terme> get(@PathVariable String terme){
        try {
            return jdmService.extract(terme);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
