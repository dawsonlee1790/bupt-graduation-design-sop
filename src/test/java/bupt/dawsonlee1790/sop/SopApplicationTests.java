package bupt.dawsonlee1790.sop;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class SopApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    // jwt 要实时更新
    private String JWT = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2IiwiVXNlck5hbWUiOiJhZG1pbiIsImlzcyI6Imh0dHA6Ly91c2VyLWRldi5kZWJ1Z3lhLmNuOjMwMDgwL1VzZXJDb250cm9sbGVyL2xvZ2luIiwiaWF0IjoxNTU4ODY1MDIzLCJleHAiOjE1NTg4NjY4MjMsIkhvc3ROYW1lIjoidXNlci1kZXYtNjk3OTY5ZDg0OS02cnFsOCIsIkhvc3RBZGRyZXNzIjoiMTAuMjQ0LjMuMzkiLCJSb2xlIjpbIlBsYW5uZXIiLCJSZXNlYXJjaGVyIiwiRm9ya2xpZnQiLCJXb3Jrc2hvcE1hbmFnZXIiLCJQcm9kdWN0aW9uTGVhZGVyIl19.v0pRtx5ehcldHhcd4ej_CL-9_Bzjzgzr5l1soi9SZnadNKC7iwtQ0GhOr5U5XqQrqhi_u3Bu8yv-iO6ioncSTg";

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mapper = new ObjectMapper();
    }

//    @Test
    public void contextLoads() throws Exception {
        this.test001MakeSop();
        this.test002MakeProductionPlan();
        this.test003ReviewPlan();
        this.test004ExecuteOrder();
        this.test005ReportException();
        this.test006HandleException();
    }

    @Test
    public void test001MakeSop() throws Exception {
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
    @Test
    public void test002MakeProductionPlan() throws Exception {
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
    @Test
    public void test003ReviewPlan() throws Exception {
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
    @Test
    public void test004ExecuteOrder() throws Exception {
        String planListUrl = "/ExecuteOrderController/";
        MvcResult planListResult = get(planListUrl);
        Assert.assertEquals(200, planListResult.getResponse().getStatus());
        Assert.assertNotEquals("", planListResult.getResponse().getContentAsString());

        String executeOrderUrl = "/ExecuteOrderController/1";
        MvcResult executeResult = post(executeOrderUrl, "");
        Assert.assertEquals(200, executeResult.getResponse().getStatus());
    }
    @Test
    public void test005ReportException() throws Exception {
        String url = "/ReportExceptionController/report/1";
        String content = "车间玉米粉量却100千克";
        MvcResult result = post(url, content);
        Assert.assertEquals(200, result.getResponse().getStatus());
    }
    @Test
    public void test006HandleException() throws Exception {
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
