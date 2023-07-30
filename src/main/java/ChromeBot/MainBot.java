package ChromeBot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MainBot {

	public static void main(String args[]) throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\VMANDGE\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // Create a new instance of the ChromeDriver
		 ChromeOptions options = new ChromeOptions();
	      options.addArguments("user-data-dir=C:\\Users\\VMANDGE\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
	      options.addArguments("profile-directory=Profile 1");
	      
	        WebDriver driver = new ChromeDriver(options);
	       // driver.get("https://www.linkedin.com");
	
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        
       
//        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
//        emailInput.sendKeys("mandgevinay16@gmail.com");
////        WebElement nextButton = driver.findElement(By.id("identifierNext"));
////        nextButton.click();
//        WebElement passwordInput = driver.findElement(By.id("password"));
//        passwordInput.sendKeys("vkshimpi16599");
//        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
//        signInButton.click();
//        
//        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search-global-typeahead__input")));
//        searchBox.sendKeys("Hiring Java developer");
//        searchBox.sendKeys(Keys.ENTER);
//        Thread.sleep(5000);
//        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), '@')]"));
//        System.out.println(elements.size());
//        for (WebElement element : elements) {
//        	System.out.println(element.isDisplayed());
//        	
//        }
//        driver.close();
        
        Set<String> set = new HashSet<String>();
        String str[] = {"Hiring Java developer","Looking Java developer","Looking for Java developer","Need Java developer","urgent Java developer","urgent Java developer","Java developer Immediate Joiner"};
        List<String> searchWords = new ArrayList<>(Arrays.asList(str));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;


        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        
        for(String searchKey : searchWords) {
        
        driver.get("https://www.linkedin.com/search/results/content/");
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search-global-typeahead__input")));
        searchBox.sendKeys(searchKey);
        searchBox.sendKeys(Keys.ENTER);

        // Wait for the search results to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	
	       
	        long scrollTime = 10000; // 30 seconds
	
	     
	        long startTime = System.currentTimeMillis();
	
	      
	        while (System.currentTimeMillis() - startTime < scrollTime) {
	           
	            js.executeScript("window.scrollBy(0, 1000)");
	
	            
	            try {
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

        
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
                set.add(email);
                System.out.println(email);
            	}
        	}
        }
        try (FileWriter writer = new FileWriter("output.txt");
                BufferedWriter bw = new BufferedWriter(writer)) {
               for (String element : set) {
                   bw.write(element);
                   bw.newLine();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
        driver.close();
    }
	
}


