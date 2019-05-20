package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.certification.Actor;
import bupt.dawsonlee1790.sop.certification.Role;
import bupt.dawsonlee1790.sop.entity.Sop;
import bupt.dawsonlee1790.sop.service.MakeSopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MakeSopController")
@Api(tags = "制定Sop")
public class MakeSopController {

    @Autowired
    public MakeSopService makeSopService;

    @PutMapping("/make")
    @Actor(Role.Planner)
    public void makeSop(@RequestBody @Validated Sop sop) {
        makeSopService.makeSop(sop);
    }

}
