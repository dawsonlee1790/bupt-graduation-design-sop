package bupt.dawsonlee1790.sop.repopsitory;

import bupt.dawsonlee1790.sop.entity.Sop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SopRepository extends JpaRepository<Sop,Long> {
}
