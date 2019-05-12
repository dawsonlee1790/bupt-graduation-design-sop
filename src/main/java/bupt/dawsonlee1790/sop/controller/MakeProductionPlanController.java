package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.dao.ProductionOrderRepository;
import bupt.dawsonlee1790.sop.dao.ProductionPlanRepository;
import bupt.dawsonlee1790.sop.dao.SopRepository;
import bupt.dawsonlee1790.sop.dto.MakePlanDTO;
import bupt.dawsonlee1790.sop.entity.ProductionOrder;
import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.entity.Sop;
import bupt.dawsonlee1790.sop.entity.SopStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MakeProductionPlanController")
public class MakeProductionPlanController {

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    @Autowired
    private SopRepository sopRepository;

    @Autowired
    private ProductionOrderRepository productionOrderRepository;


    @PutMapping("/make")
    public void makePlan(@RequestBody MakePlanDTO makePlanDTO) {
        Sop sop = sopRepository.getOne(makePlanDTO.getSopId());
        ProductionPlan productionPlan = new ProductionPlan();
        productionPlan.setName(makePlanDTO.getPlanName());
        productionPlan.setNumber(makePlanDTO.getNumber());
        productionPlan.setPlanningStartDate(makePlanDTO.getStartDate());
        productionPlan.setPlanningEndDate(makePlanDTO.getEndDate());
        productionPlan.setResponsible(makePlanDTO.getResponsible());
        productionPlan.setStatus("待审核");
        ProductionOrder startOrder = dfs(sop.getStartStep());
        productionPlan.setStartOrder(startOrder);
        productionPlanRepository.save(productionPlan);
    }

    private ProductionOrder dfs(SopStep sopStep) {
        SopStep next = sopStep.getNext();
        if (next == null) return null;
        ProductionOrder nextOrder = dfs(sopStep.getNext());
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setOperationContent(sopStep.getOperationContent());
        productionOrder.setExecutor(sopStep.getExecutor());
        productionOrder.setNext(nextOrder);
        productionOrderRepository.save(productionOrder);
        return productionOrder;
    }


}
