package bupt.dawsonlee1790.sop.service;

import bupt.dawsonlee1790.sop.dto.MakePlanDTO;
import bupt.dawsonlee1790.sop.entity.ProductionOrder;
import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.entity.Sop;
import bupt.dawsonlee1790.sop.entity.SopStep;
import bupt.dawsonlee1790.sop.repopsitory.ProductionOrderRepository;
import bupt.dawsonlee1790.sop.repopsitory.ProductionPlanRepository;
import bupt.dawsonlee1790.sop.repopsitory.SopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeProductionPlanService {


    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    @Autowired
    private SopRepository sopRepository;

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    public void makePlan(MakePlanDTO makePlanDTO) {
        Sop sop = sopRepository.findById(makePlanDTO.getSopId()).get();
        ProductionPlan productionPlan = new ProductionPlan();
        productionPlan.setName(makePlanDTO.getPlanName());
        productionPlan.setNumber(makePlanDTO.getNumber());
        productionPlan.setPlanningStartDate(makePlanDTO.getStartDate());
        productionPlan.setPlanningEndDate(makePlanDTO.getEndDate());
        productionPlan.setResponsible(makePlanDTO.getResponsible());
        productionPlan.setSop(sop);
        productionPlan.setStatus("待审核");
        ProductionOrder startOrder = dfs(sop.getStartStep());
        productionPlan.setStartOrder(startOrder);
        productionPlanRepository.save(productionPlan);
    }

    private ProductionOrder dfs(SopStep sopStep) {
        if (sopStep == null) return null;
        ProductionOrder nextOrder = dfs(sopStep.getNext());
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setOperationContent(sopStep.getOperationContent());
        productionOrder.setExecutorGroup(sopStep.getExecutorGroup());
        productionOrder.setNext(nextOrder);
//        productionOrderRepository.save(productionOrder);
        return productionOrder;
    }

}
