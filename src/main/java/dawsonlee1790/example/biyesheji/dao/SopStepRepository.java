package dawsonlee1790.example.biyesheji.dao;

import dawsonlee1790.example.biyesheji.entity.SopStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SopStepRepository extends JpaRepository<SopStep, Long> {
}
