package dawsonlee1790.example.biyesheji.dao;

import dawsonlee1790.example.biyesheji.entity.ProductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, Long> {

}
