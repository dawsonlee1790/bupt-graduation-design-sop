package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.dto.MakePlanDTO;
import bupt.dawsonlee1790.sop.service.MakeProductionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MakeProductionPlanController")
public class MakeProductionPlanController {

    @Autowired
    private MakeProductionPlanService makeProductionPlanService;

    @PutMapping("/make")
    public void makePlan(@RequestBody MakePlanDTO makePlanDTO) {
        makeProductionPlanService.makePlan(makePlanDTO);
    }

}
