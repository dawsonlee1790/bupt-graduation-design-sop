package bupt.dawsonlee1790.sop;

import bupt.dawsonlee1790.sop.controller.MakeProductionPlanControllerTest;
import bupt.dawsonlee1790.sop.controller.MakeSopControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BiyeshejiApplicationTests {

    private MakeSopControllerTest makeSopControllerTest;
    private MakeProductionPlanControllerTest makeProductionPlanControllerTest;


    @Before
    public void init(){
        makeSopControllerTest = new MakeSopControllerTest();
        makeProductionPlanControllerTest = new MakeProductionPlanControllerTest();
    }


    @Test
    public void contextLoads() throws Exception {
        makeSopControllerTest.makeSop();
        makeProductionPlanControllerTest.makeProductionPlan();
    }

}
