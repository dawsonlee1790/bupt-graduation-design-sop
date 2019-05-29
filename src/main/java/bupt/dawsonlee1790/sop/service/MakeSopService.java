package bupt.dawsonlee1790.sop.service;

import bupt.dawsonlee1790.sop.entity.Sop;
import bupt.dawsonlee1790.sop.exception.BadRequestException;
import bupt.dawsonlee1790.sop.repopsitory.SopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeSopService {

    @Autowired
    private SopRepository sopRepository;

    public void makeSop(Sop sop) throws BadRequestException {
        if(!sopRepository.existsByName(sop.getName())) {
            sopRepository.save(sop);
        }else {
            throw new BadRequestException("SOP的名称已经存在");
        }
    }

}
