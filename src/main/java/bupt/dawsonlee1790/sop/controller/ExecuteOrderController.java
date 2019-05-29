package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.certification.Actor;
import bupt.dawsonlee1790.sop.certification.Role;
import bupt.dawsonlee1790.sop.components.User;
import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import bupt.dawsonlee1790.sop.service.ExecuteOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/ExecuteOrderController")
@Api(tags = "执行生产指令")
public class ExecuteOrderController {

    @Autowired
    private ExecuteOrderService executeOrderService;

    @Autowired
    private User user;

    @GetMapping
    @Actor({Role.Forklift, Role.WorkshopManager})
    public List<ProductionPlan> getPlanList() {
        return executeOrderService.getPlanList();
    }

    @GetMapping("/{planId}")
    @Actor({Role.Forklift, Role.WorkshopManager})
    public ProductionPlan getPlan(@PathVariable long planId) {
        return executeOrderService.getPlan(planId);
    }

    @PostMapping("/{planId}")
    @Actor({Role.Forklift, Role.WorkshopManager})
    public String executeOrder(@PathVariable("planId") long planId, HttpServletResponse response) {
        try {
            executeOrderService.executeOrder(planId, user.getUserName());
            return "";
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return e.getMessage();
        }
    }
}
