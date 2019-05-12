package dawsonlee1790.example.biyesheji.controller;

import dawsonlee1790.example.biyesheji.dao.ProductionOrderRepository;
import dawsonlee1790.example.biyesheji.entity.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/HandleExceptionController")
public class HandleExceptionController {

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    @PostMapping("/{orderId}")
    public void report(@PathVariable("orderId") long orderId, @RequestBody ProductionOrder content) {
        ProductionOrder exceptionOrder = productionOrderRepository.getOne(orderId);
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setOperationContent(content.getOperationContent());
        productionOrder.setNext(productionOrder.getNext());
        productionOrder.setExecutor(content.getExecutor());
        productionOrderRepository.save(productionOrder);
        exceptionOrder.setNext(productionOrder);
        productionOrderRepository.save(exceptionOrder);
    }
}
