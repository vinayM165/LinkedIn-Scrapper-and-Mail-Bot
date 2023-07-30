package ChromeBot;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GmailSender {
	WebDriver driver;
	List<String> mailList;
	public GmailSender(WebDriver driver,List<String> mailList ) {
		this.driver = driver;
		this.mailList = mailList;
	}
	
	public void sendMail() {
   
        WebDriver driver = new ChromeDriver();

        // Navigate to the Gmail login page
        driver.get("https://mail.google.com/");

        // Log in to Gmail
        WebElement emailInput = driver.findElement(By.id("identifierId"));
        WebElement nextButton = driver.findElement(By.id("identifierNext"));
        emailInput.sendKeys("your_email@gmail.com");
        nextButton.click();

        // Wait for the password input to be displayed
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement signInButton = driver.findElement(By.id("passwordNext"));
        passwordInput.sendKeys("your_password");
        signInButton.click();

        // Wait for the Gmail inbox to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create a list of recipients
        String[] recipients = {"recipient1@example.com", "recipient2@example.com", "recipient3@example.com"};

        // Compose a new email
        WebElement composeButton = driver.findElement(By.xpath("//div[contains(text(), 'Compose')]"));
        composeButton.click();

        // Wait for the compose window to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Enter the recipients, subject, and body of the email
        WebElement toInput = driver.findElement(By.name("to"));
        WebElement subjectInput = driver.findElement(By.name("subjectbox"));
        WebElement bodyInput = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));

        for (String recipient : recipients) {
            toInput.sendKeys(recipient + ",");
        }

        subjectInput.sendKeys("Your Subject");
        bodyInput.sendKeys("Your Message");

        // Send the email
        WebElement sendButton = driver.findElement(By.xpath("//div[contains(text(), 'Send')]"));
        sendButton.click();

        // Close the ChromeDriver instance
        driver.quit();
	}
}

