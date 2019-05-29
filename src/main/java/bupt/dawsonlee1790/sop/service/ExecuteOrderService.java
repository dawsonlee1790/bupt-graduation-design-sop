package bupt.dawsonlee1790.sop.service;

import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.repopsitory.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExecuteOrderService {

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    public List<ProductionPlan> getPlanList(){
        List<ProductionPlan> planList = productionPlanRepository.findAll();
        for (ProductionPlan plan: planList
             ) {
            if("不批准".equals(plan.getStatus())){
                planList.remove(plan);
            }
        }
        return planList;
    }

    public ProductionPlan getPlan(long planId){
        return productionPlanRepository.findById(planId).get();
    }

    public void executeOrder(long planId,String executor) throws Exception {
        ProductionPlan productionPlan = productionPlanRepository.findById(planId).get();
        productionPlan.goToNextOrder(executor);
        productionPlanRepository.save(productionPlan);
    }

}
