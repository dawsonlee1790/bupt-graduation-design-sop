package bupt.dawsonlee1790.sop.service;

import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.repopsitory.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewPlanService {

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    public List<ProductionPlan> findAll() {
        return productionPlanRepository.findAll();
    }

    public ProductionPlan getPlan(long planId) {
        return productionPlanRepository.getOne(planId);
    }

    public void reviewPlan(long planId, boolean flag) {
        ProductionPlan productionPlan = productionPlanRepository.getOne(planId);
        if (flag) {
            productionPlan.setStatus("批准");
        } else {
            productionPlan.setStatus("不批准");
        }
        productionPlanRepository.save(productionPlan);
    }

}
