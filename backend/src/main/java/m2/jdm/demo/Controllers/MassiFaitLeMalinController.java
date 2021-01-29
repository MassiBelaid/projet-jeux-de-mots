package m2.jdm.demo.Controllers;

import m2.jdm.demo.Models.Terme;
import m2.jdm.demo.Repositories.TermeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terme")
public class MassiFaitLeMalinController {
    @Autowired
    TermeRepository termeRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @RequestMapping("/{chaine}")
    public List<Terme> getWithMultCrit(@PathVariable String chaine){
        return termeRepository.getWithMultCriteres(chaine);
    }
}
