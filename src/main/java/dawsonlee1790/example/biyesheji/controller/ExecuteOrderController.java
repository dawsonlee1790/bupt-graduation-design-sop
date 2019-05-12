package dawsonlee1790.example.biyesheji.controller;

import dawsonlee1790.example.biyesheji.dao.ProductionOrderRepository;
import dawsonlee1790.example.biyesheji.entity.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/ExecuteOrderController")
public class ExecuteOrderController {

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    @GetMapping("/{orderId}")
    public ProductionOrder find(@PathVariable("orderId") long orderId){
        return productionOrderRepository.getOne(orderId);
    }

    @PostMapping("/{orderId}")
    public void executeOrder(@PathVariable("orderId") long orderId){
        ProductionOrder productionOrder = productionOrderRepository.getOne(orderId);
        productionOrder.setExecuteTime(new Date());
        productionOrderRepository.save(productionOrder);
    }
}
