package LinkedIn_Scrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
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
import io.github.bonigarcia.wdm.WebDriverManager;

public class MainBot {

	public static void main(String args[]) throws Exception {
		// Setup ChromeDriver automatically
		WebDriverManager.chromedriver().setup();

        // Create a new instance of the ChromeDriver
		ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        
        HashSet<String> Emailset = new HashSet<String>();
        Set<String> URLset = new HashSet<String>();
        
        //Insert Keywords according to Job Profile
     String[] str = {
         "Hiring QA Engineer","Looking for QA Engineer", "Hiring Quality Assurance Analyst","Looking for Quality Assurance Analyst", "Hiring Software Tester","Looking for  Software Tester",
         "Hiring Manual QA", "Looking for Manual QA", "Hiring Automation QA","Looking for Automation QA", "Hiring Test Engineer","Looking for Test Engineer",
         "Hiring SDET","Looking for SDET", "Hiring QA Lead", "Looking for QA Lead", "Immediate Joiners QA", "Immediate Joiners Quality Assurance"
     };

     List<String> searchWords = new ArrayList<>(Arrays.asList(str));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
       
        driver.get("https://www.linkedin.com/login");
      if (isLoginPage(driver.getCurrentUrl())) {
          File cookieFile = new File("cookies.data");
         if (cookieFile.exists()) {
        // Load cookies from file
            try {
            FileReader fileReader = new FileReader(cookieFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] cookieParts = line.split(";");
                org.openqa.selenium.Cookie cookie = new org.openqa.selenium.Cookie(
                    cookieParts[0], cookieParts[1], cookieParts[2], cookieParts[3], null);
                driver.manage().addCookie(cookie);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
    } else {
        // Perform login and save cookies
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        emailInput.sendKeys("aakankshapatil36@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Aakanksha@11599");
        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();

        // Save cookies to file
        try {
            FileWriter fileWriter = new FileWriter(cookieFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (org.openqa.selenium.Cookie cookie : driver.manage().getCookies()) {
                bufferedWriter.write(cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";" + cookie.getPath());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
        scrollDownPage(js,15);


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

        try (FileWriter writer = new FileWriter("EmailOutput.txt");
                BufferedWriter bw = new BufferedWriter(writer)) {
                for (String element : Emailset) {
                    bw.write(element);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        try (FileWriter writer = new FileWriter("URLOutput_.txt");
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
     main.coreMailSender(Emailset);
    }
	
	 private static void scrollDownPage(JavascriptExecutor js, int numScrolls) {
        for (int i = 0; i < numScrolls; i++) {
            js.executeScript("window.scrollBy(0, 500)");
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
