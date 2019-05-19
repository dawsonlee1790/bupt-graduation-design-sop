package bupt.dawsonlee1790.sop.service;

import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.repopsitory.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportExceptionService {

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    public void reportException(long planId, String operationContent, String executorGroup, String executor) {
        ProductionPlan plan = productionPlanRepository.findById(planId).get();
        plan.reportException(operationContent, executorGroup, executor);
        productionPlanRepository.save(plan);
    }
}
