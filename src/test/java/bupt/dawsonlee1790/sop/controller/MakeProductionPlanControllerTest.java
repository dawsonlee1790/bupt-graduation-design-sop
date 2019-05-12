package bupt.dawsonlee1790.sop.controller;

import bupt.dawsonlee1790.sop.dto.MakePlanDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MakeProductionPlanControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mapper = new ObjectMapper();
    }


    private MvcResult put(String url, String content) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders.put(url).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
    }

    @Test
    public void makeProductionPlan() throws Exception {
        String url = "/MakeProductionPlanController/make";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        MakePlanDTO makePlanDTO = new MakePlanDTO();
        makePlanDTO.setSopId(1);
        makePlanDTO.setStartDate(sdf.parse("2019-04-09"));
        makePlanDTO.setEndDate(sdf.parse("2019-04-19"));
        makePlanDTO.setNumber(100);
        makePlanDTO.setPlanName("生产批次1");
        makePlanDTO.setResponsible(1);
        put(url, mapper.writeValueAsString(makePlanDTO));
    }

}
