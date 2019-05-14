package bupt.dawsonlee1790.sop.repopsitory;

import bupt.dawsonlee1790.sop.entity.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
}
