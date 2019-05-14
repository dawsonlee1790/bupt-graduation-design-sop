package bupt.dawsonlee1790.sop.service;

import bupt.dawsonlee1790.sop.entity.Sop;
import bupt.dawsonlee1790.sop.repopsitory.SopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeSopService {

    @Autowired
    private SopRepository sopRepository;

    public void makeSop(Sop sop) {
        sopRepository.save(sop);
    }

}
