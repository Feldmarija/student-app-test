import com.github.javafaker.Faker;
import constants.AllConstants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.AddStudentPage;
import page_objects.AllStudentsPage;
import page_objects.Notifications;
import utils.ConfigHelper;
import utils.DriverManager;

import java.time.Duration;

import static utils.ConfigHelper.getConfig;

public class StudentAppTest {

    WebDriver driver = DriverManager.getInstance();
    WebDriverWait driverWait;
    Faker dataFaker = new Faker();
    AllStudentsPage allStudentsPage;
    AddStudentPage addStudentPage;
    Notifications notifications;

    @BeforeMethod
    public void initialize() {
        driver.get(getConfig().getString("student.app.hostname"));
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        allStudentsPage = new AllStudentsPage();
        addStudentPage = new AddStudentPage();
        notifications = new Notifications();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
            String status = result.isSuccess() ? "passed" : "failed";
            ((JavascriptExecutor)driver).executeScript("sauce:job-result=" + status);
            driver.close();
            driver.quit();
        }
    @Test
    public void openStudentApp() {

        allStudentsPage.waitAndClickOnAddStudentButton();

        String name = addStudentPage.waitANdSetValueForNameField();

        addStudentPage.waitAndSetValueForEmailField();

        addStudentPage.waitAndSetGender(AllConstants.GenderConstants.MALE );

        addStudentPage.waitAndClickOnSubmitButton();

        Assert.assertEquals(notifications.getMessageFromNotification(),"Student successfully added");
        Assert.assertEquals(notifications.getDescriptionFromNotification(),name + " was added to the system");

        notifications.getPopupCloseButton().click();
        Assert.assertTrue(driverWait.until(ExpectedConditions.invisibilityOf(notifications.getPopupCloseButton())));

    }
}
