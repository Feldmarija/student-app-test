import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.AddStudentPage;
import page_objects.AllStudentsPage;
import page_objects.Notifications;

import java.time.Duration;

public class StudentAppTest {

    WebDriver driver;
    WebDriverWait driverWait;
    Faker dataFaker = new Faker();
    AllStudentsPage allStudentsPage;
    AddStudentPage addStudentPage;
    Notifications notifications;
    private final String APP_URL = "http://app.acodemy.lv/";

    @BeforeMethod
    public void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver();
        driver.get(APP_URL);
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        allStudentsPage = new AllStudentsPage(driver);
        addStudentPage = new AddStudentPage(driver);
        notifications = new Notifications(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
    @Test
    public void openStudentApp() {

        allStudentsPage.waitAndClickOnAddStudentButton();

        String name = addStudentPage.waitANdSetValueForNameField();

        addStudentPage.waitAndSetValueForEmailField();

        addStudentPage.waitAndSetGender("OTHER");

        addStudentPage.waitAndClickOnSubmitButton();

        Assert.assertEquals(notifications.getMessageFromNotification(),"Student successfully added");
        Assert.assertEquals(notifications.getDescriptionFromNotification(),name + " was added to the system");

        notifications.getPopupCloseButton().click();
        Assert.assertTrue(driverWait.until(ExpectedConditions.invisibilityOf(notifications.getPopupCloseButton())));





    }
}
