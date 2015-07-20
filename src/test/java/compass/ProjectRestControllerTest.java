package compass;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.compass.microservices.Application;
import com.compass.microservices.model.Project;
import com.compass.microservices.repository.ProjectRepository;
import com.compass.microservices.services.ProjectService;
import com.jayway.restassured.RestAssured;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.RuntimeConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.extract.UserTempNaming;
import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@WebAppConfiguration   
@IntegrationTest("server.port:0")  
public class ProjectRestControllerTest {
	
	private static final String LOCALHOST = "127.0.0.1";
    private static final String DB_NAME = "itest";
    private static final int MONGO_TEST_PORT = 27017;
    
    private static MongodProcess mongoProcess;
    
    private static Mongo mongo;
    private MongoTemplate template;


    
	
	@Autowired
	ProjectRepository projectRepository;
	
    Project fNf;
    Project horizon;


    @Value("${local.server.port}")   // 6
    int port;
    
    @BeforeClass
    public static void initializeDB() throws IOException {
 
        RuntimeConfig config = new RuntimeConfig();
        config.setExecutableNaming(new UserTempNaming());
 
        MongodStarter starter = MongodStarter.getInstance(config);
 
        MongodExecutable mongoExecutable = starter.prepare(new MongodConfig(Version.V2_2_0, MONGO_TEST_PORT, false));
        mongoProcess = mongoExecutable.start();
 
        mongo = new Mongo(LOCALHOST, MONGO_TEST_PORT);
        DB db = mongo.getDB(DB_NAME);
        DBCollection col = db.createCollection("projects", new BasicDBObject());

        
        
        

    }
    
    @After
    public void tearDown() throws Exception {
       template.dropCollection(Project.class);
    }
    


    
	
    @Before
    public void setUp() {
    	
        // 7
    	template = new MongoTemplate(mongo, DB_NAME);
    	template.createCollection(Project.class);
    	RestAssured.port = port;
    	fNf = new Project();
    	fNf.setName("F&F ecommerce");
    	horizon = new Project();
    	horizon.setName("HORIZON ecommerce platform");
    	//projectRepository.deleteAll();
    	projectRepository.insert(fNf);
    	projectRepository.insert(horizon);
    	//projectRepository.deleteAll();

    }
    @Test
    public void testInsert(){
    	
        int projectsInCollection = template.findAll(Project.class).size();
        
        assertEquals("Only 2 projects should exist in collection, but there are " 
                + projectsInCollection, 2, projectsInCollection);

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

