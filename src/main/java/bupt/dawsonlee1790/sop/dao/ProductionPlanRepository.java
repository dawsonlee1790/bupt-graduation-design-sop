package bupt.dawsonlee1790.sop.dao;

import bupt.dawsonlee1790.sop.entity.ProductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, Long> {

}
