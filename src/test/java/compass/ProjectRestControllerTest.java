package compass;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.compass.microservices.Application;
import com.compass.microservices.model.Project;
import com.compass.microservices.repository.ProjectRepository;
import com.compass.microservices.services.ProjectService;
import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.*;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@WebAppConfiguration   
@IntegrationTest("server.port:0")  
public class ProjectRestControllerTest {
	@Autowired
	ProjectService service;
	@Autowired
	ProjectRepository projectRepository;
	
    Project fNf;
    Project horizon;


    @Value("${local.server.port}")   // 6
    int port;
	
    @Before
    public void setUp() {
        // 7
    	fNf = new Project();
    	fNf.setName("F&F ecommerce");
    	horizon = new Project();
    	horizon.setName("HORIZON ecommerce platform");
    	//projectRepository.deleteAll();
    	projectRepository.insert(fNf);
    	projectRepository.insert(horizon);
        RestAssured.port = port;

    }
    
    @Test
    public void canFetchfNfProject() {
        String projectId = fNf.getId();

        when().
                get("/projects/{id}", projectId).
        then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.is("F&F ecommerce")).
                body("id", Matchers.is(projectId));
    }
    
    @Test
    public void canFetchAll() {
        when().
                get("/projects").
        then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.hasItems("F&F ecommerce", "HORIZON ecommerce platform"));
    }
    
    @Test
    public void canDeleteProjectfNf() {
    	String projectId1 = fNf.getId();

        when()
                .delete("/projects/{id}", projectId1).
        then().
                statusCode(HttpStatus.SC_OK);
    }
    
    

    
    @Test
    public void canDeleteProjectHorizon() {
    	String projectId1 = horizon.getId();

        when()
                .delete("/projects/{id}", projectId1).
        then().
                statusCode(HttpStatus.SC_OK);
    }
    
    /*@Test
    public void canDeleteProjectNotThere() {
    	String projectId1 = horizon.getId();
    	String response = delete("/projects/{id}", projectId1).andReturn().body().asString();
        when()
                .delete("/projects/{id}", projectId1).
        then().
                statusCode(HttpStatus.SC_NO_CONTENT);
    }*/
    
   /* @After
    public void tearDown() {
    	if(horizon.getId()!=null){
    	System.out.println("horizon id"+horizon.getId());
    	String uri = "/projects/"+horizon.getId();
    	String response = delete(uri).andReturn().body().asString();
    	System.out.println(response);
    	}
    	if(fNf.getId()!=null)
    	{	
    		String uri = "/projects/"+fNf.getId();
    		String response1 = delete(uri).andReturn().body().asString();
    		System.out.println(response1);
    	}
    }*/
}

