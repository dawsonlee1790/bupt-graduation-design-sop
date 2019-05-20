package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.certification.Actor;
import bupt.dawsonlee1790.sop.certification.Role;
import bupt.dawsonlee1790.sop.components.User;
import bupt.dawsonlee1790.sop.service.ReportExceptionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/ReportExceptionController")
@Api(tags = "报告异常")
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
