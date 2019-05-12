package dawsonlee1790.example.biyesheji.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dawsonlee1790.example.biyesheji.entity.Sop;
import dawsonlee1790.example.biyesheji.entity.SopStep;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MakeSopControllerTest {

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
    public void makeSop() throws Exception {
        String url = "/MakeSopController/make";
        SopStep sopStep2 = new  SopStep("操作2", "group2", null);
        SopStep sopStep1 = new  SopStep("操作1", "group1", sopStep2);
        Sop sop = new Sop();
        sop.setName("NewMedicine");
        sop.setNumber(100L);
        sop.setStartStep(sopStep1);
        put(url, mapper.writeValueAsString(sop));
    }

}
