package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public class Notifications {
    private final WebDriver driver = DriverManager.getInstance();
    private final WebDriverWait webDriverWait;

    public Notifications() {
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }
    @FindBy(how = How.CLASS_NAME, using = "ant-notification-notice-message")
    WebElement notificationMessageElement;
    @FindBy(how = How.CLASS_NAME, using = "ant-notification-notice-description")
    WebElement notificationDescriptionElement;

    public String getMessageFromNotification() {
        webDriverWait.until(ExpectedConditions.visibilityOf(notificationMessageElement));
        return notificationMessageElement.getText();
    }
    public String getDescriptionFromNotification() {
        webDriverWait.until(ExpectedConditions.visibilityOf(notificationDescriptionElement));
        return notificationDescriptionElement.getText();
    }

    @FindBy(how = How.CLASS_NAME, using = "ant-notification-notice-close")
    WebElement popupCloseButton;

    public WebElement getPopupCloseButton() {
        return popupCloseButton;


    }
}
