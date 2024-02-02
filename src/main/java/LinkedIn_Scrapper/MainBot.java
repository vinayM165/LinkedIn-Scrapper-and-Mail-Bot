package LinkedIn_Scrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Gmail_API.EmailMain;

public class MainBot {

	public static void main(String args[]) throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp5pr\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");

        // Create a new instance of the ChromeDriver
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\path\\to\\your\\custom\\directory");
        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        
        Set<String> Emailset = new HashSet<String>();
        Set<String> URLset = new HashSet<String>();
        
        String str[] = {"Hiring Java developer","Looking Java developer","Looking for Java developer","Need Java developer","urgent Java developer","urgent Java developer","Java developer Immediate Joiner"};
        
        List<String> searchWords = new ArrayList<>(Arrays.asList(str));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
       
        driver.get("https://www.linkedin.com/login");
        if(isLoginPage(driver.getCurrentUrl())){
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        emailInput.sendKeys("mandgevinay16@gmail.com");
        // WebElement nextButton = driver.findElement(By.id("identifierNext"));
        // nextButton.click();
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("vkshimpi16599");
        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();
        }

        for(String searchKey : searchWords) {
        driver.get("https://www.linkedin.com/search/results/content/");
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search-global-typeahead__input")));
        searchBox.sendKeys(searchKey);
        searchBox.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //scroll down Page to get more results
        //more value of 2nd parameter more data
        scrollDownPage(js,1);


        List<WebElement> posts = driver.findElements(By.className("break-words"));
        // Create a pattern to match email addresses
        // Iterate over each post and check for email addresses in the post description
        for (WebElement post : posts) {
            // WebElement descriptionElement = post.findElement(By.className("feed-shared-update-v2__description"));
            String descriptionText = post.getText();
            //System.out.println(descriptionText);
            Matcher matcher = emailPattern.matcher(descriptionText);
            while (matcher.find()) {
                String email = matcher.group();
                Emailset.add(email);
                }
            }

        List<WebElement> links = driver.findElements(By.xpath("//a[not(contains(@href, 'linkedin.com')) and not(starts-with(@href, '#'))]"));
            for (WebElement link : links) {
                String url = link.getAttribute("href");
                if(url.contains("feed") || url.contains("hashtag"))
                    continue;
                if (url.contains("mailto:")) {
                    url = url.replace("mailto:","");
                    Emailset.add(url);
                } else {
                    URLset.add(url);
                }
            }
        }
        String timeString = LocalDateTime.now().format(formatter);

        try (FileWriter writer = new FileWriter("EmailOutput_"+ timeString +".txt");
                BufferedWriter bw = new BufferedWriter(writer)) {
                for (String element : Emailset) {
                    bw.write(element);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        try (FileWriter writer = new FileWriter("URLOutput_"+ timeString +".txt");
                BufferedWriter bw = new BufferedWriter(writer)) {
                for (String element : URLset) {
                    bw.write(element);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        driver.close();

     //starting to send mails
     EmailMain main = new EmailMain();
     //main.startEmailProcess();
    }
	
	 private static void scrollDownPage(JavascriptExecutor js, int numScrolls) {
        for (int i = 0; i < numScrolls; i++) {
            js.executeScript("window.scrollBy(0, 200)");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static boolean isLoginPage(String currentUrl) {
        // You need to adjust this condition based on the actual login page URL
        return currentUrl.contains("linkedin.com/login");
    }
	
}


