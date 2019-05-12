package dawsonlee1790.example.biyesheji.dao;

import dawsonlee1790.example.biyesheji.entity.Sop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SopRepository extends JpaRepository<Sop,Long> {
}
