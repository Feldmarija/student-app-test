package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.awt.print.PageFormat;
import java.time.Duration;

public class AllStudentsPage {
    private final WebDriver driver = DriverManager.getInstance();
   private final WebDriverWait webDriverWait;

    public AllStudentsPage() {
        PageFactory.initElements(driver, this);
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(how = How.XPATH, using = "//div[@class='ant-table-title']//button")
    public WebElement addStudentButton;

    public void waitAndClickOnAddStudentButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addStudentButton));
        addStudentButton.click();
    }

}
