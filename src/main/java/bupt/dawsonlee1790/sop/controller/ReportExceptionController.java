package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.certification.Actor;
import bupt.dawsonlee1790.sop.certification.Role;
import bupt.dawsonlee1790.sop.components.User;
import bupt.dawsonlee1790.sop.service.ReportExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/ReportExceptionController")
public class ReportExceptionController {

    @Autowired
    private ReportExceptionService reportExceptionService;

    @Autowired
    private User user;

    @PostMapping("/report/{planId}")
    @Actor({Role.Forklift, Role.WorkshopManager})
    public void reportException(@PathVariable("planId") long planId, @RequestBody String content) throws IOException {
        reportExceptionService.reportException(planId, content, user.getGroupList().toString(), user.getUserName());
    }

}
