import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentAppTest {

    WebDriver driver;
    private final String APP_URL = "http://acodemystudentapp-env.eba-d2vctp4d.eu-north-1.elasticbeanstalk.com/";

    @BeforeMethod
    public void initialise() {
        driver = new ChromeDriver();
        driver.get(APP_URL);
    }
    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
    @Test
    public void openStudentApp() {

        WebElement addStudentButton = driver.findElement(By.xpath("//div[@class='ant-table-title']//button"));
        addStudentButton.click();

        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("Jane Doe");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("siroj18841@marikuza.com");

        WebElement submitButton = driver.findElement(By.xpath("//div[@class='ant-form-item-control-input-content']//button"));
        submitButton.click();
    }
}
