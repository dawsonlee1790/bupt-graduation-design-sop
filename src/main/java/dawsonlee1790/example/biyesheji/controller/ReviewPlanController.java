package dawsonlee1790.example.biyesheji.controller;

import dawsonlee1790.example.biyesheji.dao.ProductionPlanRepository;
import dawsonlee1790.example.biyesheji.entity.ProductionPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ReviewPlanController")
public class ReviewPlanController {

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    @GetMapping
    public List<ProductionPlan> findAll() {
        return productionPlanRepository.findAll();
    }

    @GetMapping("/{planId}")
    public ProductionPlan getPlan(@PathVariable("planId") long planId) {
        return productionPlanRepository.getOne(planId);
    }

    @PostMapping("/{planId}/review")
    public void reviewPlan(@PathVariable("planId") long planId, @RequestBody boolean flag) {
        ProductionPlan productionPlan = productionPlanRepository.getOne(planId);
        if (flag) {
            productionPlan.setStatus("批准");
        } else {
            productionPlan.setStatus("不批准");
        }
        productionPlanRepository.save(productionPlan);
    }

}
