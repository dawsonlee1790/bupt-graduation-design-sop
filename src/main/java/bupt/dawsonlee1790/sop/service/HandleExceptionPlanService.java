package bupt.dawsonlee1790.sop.service;

import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.repopsitory.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandleExceptionPlanService {

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    public void handleEPlan(long planId, String content, String executorGroup) {
        ProductionPlan plan = productionPlanRepository.findById(planId).get();
        plan.handleException(content, executorGroup);
        productionPlanRepository.save(plan);
    }

}
