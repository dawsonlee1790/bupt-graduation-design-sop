package dawsonlee1790.example.biyesheji.controller;

import dawsonlee1790.example.biyesheji.dao.ProductionOrderRepository;
import dawsonlee1790.example.biyesheji.dao.ProductionPlanRepository;
import dawsonlee1790.example.biyesheji.dao.SopRepository;
import dawsonlee1790.example.biyesheji.dto.MakePlanDTO;
import dawsonlee1790.example.biyesheji.entity.ProductionOrder;
import dawsonlee1790.example.biyesheji.entity.ProductionPlan;
import dawsonlee1790.example.biyesheji.entity.Sop;
import dawsonlee1790.example.biyesheji.entity.SopStep;
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
