package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.certification.Actor;
import bupt.dawsonlee1790.sop.certification.Role;
import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.service.ExecuteOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/ExecuteOrderController")
public class ExecuteOrderController {

    @Autowired
    private ExecuteOrderService executeOrderService;

    @GetMapping("/")
    @Actor({Role.Forklift, Role.WorkshopManager})
    public List<ProductionPlan> getPlanList() {
        return executeOrderService.getPlanList();
    }

    @PostMapping("/{planId}")
    @Actor({Role.Forklift, Role.WorkshopManager})
    public String executeOrder(@PathVariable("planId") long planId, HttpServletResponse response) {
        try {
            executeOrderService.executeOrder(planId);
            return "";
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return e.getMessage();
        }
    }
}
