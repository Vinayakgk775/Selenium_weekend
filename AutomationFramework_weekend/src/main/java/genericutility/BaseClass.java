package genericutility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import objectrepository.LoginPage;
import objectrepository.WelcomePage;

public class BaseClass {
	public static ExtentReports eReport;
	public static WebDriver driver;
	public static ExtentTest test;

	public JavaUtility jUtil = new JavaUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public FileUtility fUtil = new FileUtility();
	public ExcelUtility eUtil = new ExcelUtility();

	@BeforeSuite
	public void reportConfig() {
		ExtentSparkReporter spark = new ExtentSparkReporter(
				"./HTML_reports/extentReport_" + jUtil.getSystemTime() + "html");
		eReport = new ExtentReports();
		eReport.attachReporter(spark);
	}

	@BeforeClass
	public void openBrowser() throws IOException {
		driver = new ChromeDriver();
		wUtil.maximize(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get(fUtil.getDataFromProperty("url"));
	}

	@BeforeMethod
	public void login() throws IOException {
		WelcomePage wp = new WelcomePage(driver);
		wp.getloginLink().click();

		LoginPage lp = new LoginPage(driver);
		lp.getEmailTextField().sendKeys(fUtil.getDataFromProperty("email"));
		lp.getPasswordTextField().sendKeys(fUtil.getDataFromProperty("password"));
		lp.getLoginButton().click();
	}

	@AfterSuite
	public void reportBackup() {
		eReport.flush();
	}

}
