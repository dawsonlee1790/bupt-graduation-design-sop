package dawsonlee1790.example.biyesheji.controller;

import dawsonlee1790.example.biyesheji.dao.SopRepository;
import dawsonlee1790.example.biyesheji.entity.Sop;
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
