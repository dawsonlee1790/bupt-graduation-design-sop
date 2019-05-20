package bupt.dawsonlee1790.sop;

import com.fasterxml.jackson.databind.JsonNode;
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

    // jwt 要实时更新
    private String JWT = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2IiwiaXNzIjoiaHR0cDovL3VzZXItZGV2LmRlYnVneWEuY246MzAwODAvVXNlckNvbnRyb2xsZXIvbG9naW4iLCJpYXQiOjE1NTgzNzc5MzIsImV4cCI6MTU1ODM3OTczMiwiSG9zdE5hbWUiOiJ1c2VyLWRldi01Yjg4OWM5Njc2LXdsZmp2IiwiSG9zdEFkZHJlc3MiOiIxMC4yNDQuMy4zNSIsIlJvbGUiOlsiUGxhbm5lciIsIlJlc2VhcmNoZXIiLCJGb3JrbGlmdCIsIldvcmtzaG9wTWFuYWdlciIsIlByb2R1Y3Rpb25MZWFkZXIiXX0.BH1Exedg3Up5ysk3IXY1BkFECsfPEal7-ornrVGf_oVOC9QDg_zTDk3ELknDVGSOMTXbKEgayLbldEWzBM4zfA";

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void contextLoads() throws Exception {
        this.makeSop();
        this.makeProductionPlan();
        this.reviewPlan();
        this.executeOrder();
        this.reportException();
        this.handleException();
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

    private void reviewPlan() throws Exception {
//         "/ReviewPlanController/{planId}/review";
        String reviewUrl = "/ReviewPlanController/1/review";
        //language=JSON
        String content = "true";
        MvcResult reviewResult = post(reviewUrl, content);
        Assert.assertEquals(200, reviewResult.getResponse().getStatus());
//         "/ReviewPlanController/{planId}";
        String getPlanUrl = "/ReviewPlanController/1";
        MvcResult getPlanResult = get(getPlanUrl);
        String responseBody = getPlanResult.getResponse().getContentAsString();
        JsonNode plan = mapper.readTree(responseBody);
        Assert.assertEquals("批准", plan.get("status").asText());
    }

    private void executeOrder() throws Exception {
        String planListUrl = "/ExecuteOrderController/";
        MvcResult planListResult = get(planListUrl);
        Assert.assertEquals(200, planListResult.getResponse().getStatus());
        Assert.assertNotEquals("", planListResult.getResponse().getContentAsString());

        String executeOrderUrl = "/ExecuteOrderController/1";
        MvcResult executeResult = post(executeOrderUrl, "");
        Assert.assertEquals(200, executeResult.getResponse().getStatus());
    }

    private void reportException() throws Exception {
        String url = "/ReportExceptionController/report/1";
        String content = "车间玉米粉量却100千克";
        MvcResult result = post(url, content);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    private void handleException() throws Exception {
        String url = "/HandleExceptionController/1";
        //language=JSON
        String content = "{\n" +
                "  \"content\": \"请去仓库4拿100千克玉米粉\",\n" +
                "  \"executorGroup\": \"Forklift\"\n" +
                "}";
        MvcResult result = post(url, content);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    private MvcResult put(String url, String content) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .put(url)
                        .header("AUTHORIZATION", JWT)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
    }

    private MvcResult post(String url, String content) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post(url)
                        .header("AUTHORIZATION", JWT)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
    }

    private MvcResult get(String url) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .get(url)
                        .header("AUTHORIZATION", JWT)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
    }


}
