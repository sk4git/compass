package compass;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
	Project compass;


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
        //DBCollection col = db.createCollection("projects", new BasicDBObject());

      }
    
    @AfterClass
    public static void shutdownDB() throws InterruptedException {
        mongo.close();
        mongoProcess.stop();
    }
    
    @After
    public void tearDown() throws Exception {
       template.dropCollection(Project.class);
    }
   	
    @Before
    public void setUp() {
		template.createCollection(Project.class);
		RestAssured.port = port;
		fNf = new Project();
		fNf.setName("F&F ecommerce");
		fNf.setTags(Arrays.asList("java", "spring", "hibernate", "mongodb"));
		horizon = new Project();
		horizon.setName("HORIZON ecommerce platform");
		horizon.setTags(Arrays.asList("java", "spring", "hibernate", "oracle"));
		compass = new Project();
		compass.setId("cmpassId001");
		compass.setName("Compass Search Project");
		compass.setTags(Arrays.asList("java", "spring", "mongodb",
				"spring boot"));
		// projectRepository.deleteAll();
		projectRepository.insert(fNf);
		projectRepository.insert(horizon);
		projectRepository.insert(compass);
		// projectRepository.deleteAll();

    }
    @Test
    public void testFindAllProjectsAndCheckSize(){
    	
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
	public void canFetchCompassProject() {
		String projectId = "cmpassId001";

		when().get("/projects/{id}", projectId).then()
				.statusCode(HttpStatus.SC_OK)
				.body("name", Matchers.is("Compass Search Project"))
				.body("id", Matchers.is(projectId));
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
	public void canFetchProjectByName() {
		String name = compass.getName();
		when().get("/projects/search?name=Compass Search Project")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("name", Matchers.equalTo(name))
				.body("tags",
						Matchers.hasItems("java", "spring", "mongodb",
								"spring boot"));

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
	public void canFetchByTagJavaSpringMongoDb() {
		List<String> tags = Arrays.asList("java", "spring", "mongodb");
		when().get("/projects/tagged/{tag}", tags)
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("name",
						Matchers.hasItems("F&F ecommerce",
								"Compass Search Project"));
	}

	@Test
	public void canFetchByTagJavaSpringHibernate() {
		List<String> tags = Arrays.asList("java", "spring", "hibernate");
		when().get("/projects/tagged/{tag}", tags)
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("name",
						Matchers.hasItems("F&F ecommerce",
								"HORIZON ecommerce platform"));
	}

	@Test
	public void fetchOnlyHorizonProjectBySearchingOracleTag() {
		List<String> tags = Arrays.asList("oracle");
		when().get("/projects/tagged/{tag}", tags)
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("name",
						Matchers.not(Matchers.hasItems("F&F ecommerce",
								"Compass Search Project")));
	}

	@Test
	public void NoProjectFoundOnSearchingByMissingTechnologyTag() {
		List<String> tags = Arrays.asList("missing technology");
		when().get("/projects/tagged/{tag}", tags).then()
				.statusCode(HttpStatus.SC_OK).body("name", Matchers.empty());
	}


    
    @Test
    public void canDeleteProjectHorizon() {
    	String projectId1 = horizon.getId();

        when()
                .delete("/projects/{id}", projectId1).
        then().
                statusCode(HttpStatus.SC_OK);
    }
   
    
}

