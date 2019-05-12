package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.dao.SopRepository;
import bupt.dawsonlee1790.sop.entity.Sop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MakeSopController")
public class MakeSopController {

    @Autowired
    private SopRepository sopRepository;

    @PutMapping("/make")
    public void makeSop(@RequestBody Sop sop) {
        sopRepository.save(sop);
    }

}
