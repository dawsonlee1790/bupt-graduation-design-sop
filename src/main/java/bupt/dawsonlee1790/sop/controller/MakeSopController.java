package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.entity.Sop;
import bupt.dawsonlee1790.sop.service.MakeSopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MakeSopController")
public class MakeSopController {


    @Autowired
    public MakeSopService makeSopService;

    @PutMapping("/make")
    public void makeSop(@RequestBody @Validated  Sop sop) {
        makeSopService.makeSop(sop);
    }

}
