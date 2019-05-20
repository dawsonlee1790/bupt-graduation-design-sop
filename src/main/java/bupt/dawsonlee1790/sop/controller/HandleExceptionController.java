package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.certification.Actor;
import bupt.dawsonlee1790.sop.certification.Role;
import bupt.dawsonlee1790.sop.dto.HandleEDTO;
import bupt.dawsonlee1790.sop.service.HandleExceptionPlanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/HandleExceptionController")
@Api(tags = "处理生产过程异常")
public class HandleExceptionController {

    @Autowired
    private HandleExceptionPlanService handleExceptionPlanService;

    @PostMapping("/{planId}")
    @Actor(Role.ProductionLeader)
    public void handleEPlan(@PathVariable("planId") long planId, @RequestBody HandleEDTO handleEDTO) {
        handleExceptionPlanService.handleEPlan(planId, handleEDTO.getContent(), handleEDTO.getExecutorGroup());
    }
}
