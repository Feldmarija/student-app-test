package page_objects;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public class AddStudentPage {
    private final WebDriver driver = DriverManager.getInstance();
    private final WebDriverWait webDriverWait;
    private Faker faker = new Faker();

    public AddStudentPage() {
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10 ));
        PageFactory.initElements(driver,this);
    }
    @FindBy(how = How.ID, using = "name")
    WebElement nameField;

    public String waitANdSetValueForNameField() {
        webDriverWait.until(ExpectedConditions.visibilityOf(nameField));
        String name = faker.harryPotter().character();
        nameField.sendKeys(name);
        return name;
    }

    @FindBy(how = How.ID, using = "email")
    WebElement emailField;

    public void waitAndSetValueForEmailField() {
        webDriverWait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(faker.internet().emailAddress());
    }

    @FindBy(how = How.ID, using = "gender")
    WebElement genderDropdown;

    public void waitAndSetGender(String gender) {
        genderDropdown.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='" + gender.toUpperCase() + "']")));
        WebElement genderElement = driver.findElement(By.xpath("//div[@title='" + gender.toUpperCase() + "']"));
        genderElement.click();
    }

    @FindBy(how = How.XPATH, using = "//div[@class='ant-form-item-control-input-content']//button")
    WebElement submitButton;

    public void waitAndClickOnSubmitButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }
}
