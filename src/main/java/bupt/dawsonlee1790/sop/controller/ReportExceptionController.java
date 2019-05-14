package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.repopsitory.ProductionOrderRepository;
import bupt.dawsonlee1790.sop.entity.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ReportExceptionController")
public class ReportExceptionController {

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    @PostMapping("/report/{orderId}")
    public void report(@PathVariable("orderId") long orderId, @RequestBody String content){
        ProductionOrder productionOrder = productionOrderRepository.getOne(orderId);
        ProductionOrder exceptionOrder = new ProductionOrder();
        exceptionOrder.setOperationContent(content);
        exceptionOrder.setNext(productionOrder.getNext());
        productionOrderRepository.save(exceptionOrder);
        productionOrder.setNext(exceptionOrder);
        productionOrderRepository.save(productionOrder);
    }
}
