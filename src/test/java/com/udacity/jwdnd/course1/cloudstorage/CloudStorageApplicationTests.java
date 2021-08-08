package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	public void signUp() {
		driver.get("http://localhost:" + this.port + "/signup");
		Signup signup = new Signup(driver);
		signup.createAccount("kanak", "Kanak", "Tenguria", "k");
	}
	public void Login(){
		driver.get("http://localhost:" + this.port + "/login");
		Login login = new Login(driver);
		login.signIn("kanak", "k");
	}

	@Test
	@Order(1)
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(2)
	public void unauthAccessHome(){
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(3)
	public void verifySignUpSignInLogout(){
		signUp();
		Login();
		Home home = new Home(driver);
		home.onClickLogout();
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(4)
	public void testAddNote(){
		Login();
		Home home = new Home(driver);
		home.onClickNoteTab();
		home.addNote("Note Title", "Note Description");
		driver.get("http://localhost:" + this.port + "/home");
		String insertedTitle = home.getNoteTitle();
		String insertedDescription = home.getNoteDescription();
		Assertions.assertEquals("Note Title", insertedTitle);
		Assertions.assertEquals("Note Description", insertedDescription);
	}

	@Test
	@Order(5)
	public void testEditNote() {
		Login();
		driver.get("http://localhost:" + this.port + "/home");
		Home home = new Home(driver);
		home.onClickNoteTab();
		home.editNote("Changed Title", "Changed Description");
		driver.get("http://localhost:" + this.port + "/home");
		String insertedTitle = home.getNoteTitle();
		String insertedDescription = home.getNoteDescription();
		Assertions.assertEquals("Changed Title", insertedTitle);
		Assertions.assertEquals("Changed Description", insertedDescription);
	}

	@Test
	@Order(6)
	public void testDeleteNote(){
		Login();
		driver.get("http://localhost:" + this.port + "/home");
		Home home = new Home(driver);
		home.onClickNoteTab();
		home.deleteNote();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertThrows(NoSuchElementException.class, home::getNoteTitle);
		Assertions.assertThrows(NoSuchElementException.class, home::getNoteDescription);
	}

	@Test
	@Order(7)
	public void testAddCredentials(){
		Login();
		Home home = new Home(driver);
		home.onClickCredentialsTab();
		home.addCredentials("google","kanak", "kanak");
		driver.get("http://localhost:" + this.port + "/home");
		String insertedURL = home.getCredentialsURL();
		String insertedUsername = home.getCredentialsUsername();
		Assertions.assertEquals("google", insertedURL);
		Assertions.assertEquals("kanak", insertedUsername);
	}

	@Test
	@Order(8)
	public void testEditCredentials(){
		Login();
		Home home = new Home(driver);
		home.onClickCredentialsTab();
		home.editCredentials("googleCHANGED","kira", "kira");
		driver.get("http://localhost:" + this.port + "/home");
		String insertedURL = home.getCredentialsURL();
		String insertedUsername = home.getCredentialsUsername();
		Assertions.assertEquals("googleCHANGED", insertedURL);
		Assertions.assertEquals("kira", insertedUsername);
	}

	@Test
	@Order(9)
	public void testDeleteCredentials(){
		Login();
		driver.get("http://localhost:" + this.port + "/home");
		Home home = new Home(driver);
		home.onClickCredentialsTab();
		home.deleteCredentials();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertThrows(NoSuchElementException.class, home::getCredentialsURL);
		Assertions.assertThrows(NoSuchElementException.class, home::getCredentialsUsername);
	}
}
