package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.certification.Actor;
import bupt.dawsonlee1790.sop.certification.Role;
import bupt.dawsonlee1790.sop.dto.MakePlanDTO;
import bupt.dawsonlee1790.sop.service.MakeProductionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MakeProductionPlanController")
public class MakeProductionPlanController {

    @Autowired
    private MakeProductionPlanService makeProductionPlanService;

    @PutMapping("/make")
    @Actor(Role.Researcher)
    public void makePlan(@RequestBody MakePlanDTO makePlanDTO) {
        makeProductionPlanService.makePlan(makePlanDTO);
    }

}
