package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.certification.Actor;
import bupt.dawsonlee1790.sop.certification.Role;
import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.service.ReviewPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ReviewPlanController")
public class ReviewPlanController {

    @Autowired
    private ReviewPlanService reviewPlanService;

    @GetMapping
    @Actor(Role.ProductionLeader)
    public List<ProductionPlan> findAll() {
        return reviewPlanService.findAll();
    }

    @GetMapping("/{planId}")
    @Actor(Role.ProductionLeader)
    public ProductionPlan getPlan(@PathVariable("planId") long planId) {
        return reviewPlanService.getPlan(planId);
    }

    @PostMapping("/{planId}/review")
    @Actor(Role.ProductionLeader)
    public void reviewPlan(@PathVariable("planId") long planId, @RequestBody boolean flag) {
        reviewPlanService.reviewPlan(planId, flag);
    }

}
