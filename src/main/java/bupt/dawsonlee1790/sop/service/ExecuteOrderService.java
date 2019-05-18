package bupt.dawsonlee1790.sop.service;

import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.repopsitory.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecuteOrderService {

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    public List<ProductionPlan> getPlanList(){
        return productionPlanRepository.findAll();
    }

    public void executeOrder(long planId) {
        ProductionPlan productionPlan = productionPlanRepository.findById(planId).get();
        productionPlan.goToNextOrder();
        productionPlanRepository.save(productionPlan);
    }

}
