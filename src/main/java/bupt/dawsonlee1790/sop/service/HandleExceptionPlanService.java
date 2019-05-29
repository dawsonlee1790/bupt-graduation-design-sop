package bupt.dawsonlee1790.sop.service;

import bupt.dawsonlee1790.sop.dto.EPlanDetail;
import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.repopsitory.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandleExceptionPlanService {

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    public List<ProductionPlan> findEPlan(){
        List<ProductionPlan> ePlanList = new ArrayList<>();
        for (ProductionPlan plan: productionPlanRepository.findAll()) {
            if (plan.isException()) {
                ePlanList.add(plan);
            }
        }
        return ePlanList;
    }

    public EPlanDetail getEPlanDetail(long planId){
        ProductionPlan ePlan = productionPlanRepository.findById(planId).get();
        EPlanDetail ePlanDetail = new EPlanDetail();
        ePlanDetail.setId(ePlan.getId());
        ePlanDetail.seteContent(ePlan.getExecutedOrder().getOperationContent());
        ePlanDetail.setReportPerson(ePlan.getExecutedOrder().getExecutor());
        ePlanDetail.setReportTime(ePlan.getExecutedOrder().getExecuteTime());
        ePlanDetail.setSopName(ePlan.getSop().getName());
        ePlanDetail.setPlanName(ePlan.getName());
        return ePlanDetail;
    }

    public void handleEPlan(long planId, String content, String executorGroup) {
        ProductionPlan plan = productionPlanRepository.findById(planId).get();
        plan.handleException(content, executorGroup);
        productionPlanRepository.save(plan);
    }

}
