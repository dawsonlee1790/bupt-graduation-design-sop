package bupt.dawsonlee1790.sop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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
public class SopApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void contextLoads() throws Exception {
        this.makeSop();
        this.makeProductionPlan();
    }


    private void makeSop() throws Exception {
        String url = "/MakeSopController/make";
        //language=JSON
        String content = "{\n" +
                "  \"name\": \"NewMedicine\",\n" +
                "  \"number\": 100,\n" +
                "  \"startStep\": {\n" +
                "    \"operationContent\": \"操作1\",\n" +
                "    \"executorGroup\": \"Forklift\",\n" +
                "    \"next\": {\n" +
                "      \"operationContent\": \"操作2\",\n" +
                "      \"executorGroup\": \"WorkshopManager\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        MvcResult mvcResult = put(url, content);
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    private void makeProductionPlan() throws Exception {
        String url = "/MakeProductionPlanController/make";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //language=JSON
        String content = "{\n" +
                "  \"sopId\": 1,\n" +
                "  \"startDate\": \"2019-04-19\",\n" +
                "  \"endDate\": \"2019-05-20\",\n" +
                "  \"number\": 100,\n" +
                "  \"planName\": \"New药批次100\",\n" +
                "  \"responsible\": \"生产责任人-339\"\n" +
                "}";
        MvcResult mvcResult = put(url, content);
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    private MvcResult put(String url, String content) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders.put(url).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
    }

    private MvcResult post(String url, String content) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders.post(url).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
    }

    private MvcResult get(String url) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
    }


}
