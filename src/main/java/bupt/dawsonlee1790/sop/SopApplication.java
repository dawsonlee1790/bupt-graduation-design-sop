package bupt.dawsonlee1790.sop;

import bupt.dawsonlee1790.sop.certification.Role;
import bupt.dawsonlee1790.sop.dto.MakePlanDTO;
import bupt.dawsonlee1790.sop.entity.Sop;
import bupt.dawsonlee1790.sop.entity.SopStep;
import bupt.dawsonlee1790.sop.service.ExecuteOrderService;
import bupt.dawsonlee1790.sop.service.MakeProductionPlanService;
import bupt.dawsonlee1790.sop.service.MakeSopService;
import bupt.dawsonlee1790.sop.service.ReviewPlanService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.text.SimpleDateFormat;

@SpringBootApplication
@EnableJpaAuditing
public class SopApplication {

    @Bean
    @Profile("dev")
    public CommandLineRunner init(
            MakeSopService makeSopService,
            MakeProductionPlanService makeProductionPlanService,
            ReviewPlanService reviewPlanService,
            ExecuteOrderService executeOrderService) {
        return args -> {
            SopStep sopStep4 = new SopStep("操作4", Role.Forklift.name(), null);
            SopStep sopStep3 = new SopStep("操作3", Role.Forklift.name(), sopStep4);
            SopStep sopStep2 = new SopStep("操作2", Role.Forklift.name(), sopStep3);
            SopStep sopStep1 = new SopStep("操作1", Role.WorkshopManager.name(), sopStep2);
            Sop sop = new Sop("NewMedicine", 100L, sopStep1);
            makeSopService.makeSop(sop);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            MakePlanDTO makePlanDTO = new MakePlanDTO(
                    "生产批次-1",
                    1,
                    1000,
                    sdf.parse("2019-06-01"),
                    sdf.parse("2019-06-30"),
                    "admin");
            makeProductionPlanService.makePlan(makePlanDTO);
            reviewPlanService.reviewPlan(1, true);
            executeOrderService.executeOrder(1, "admin");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SopApplication.class, args);
    }

}
