# LinkedIn Email/URL Scraper

This Java application allows you to scrape email addresses and URLs from LinkedIn based on specified keywords related to job profiles. The extracted information can be used for various purposes, such as networking or job applications.

## Prerequisites

Before running the application, make sure you have the following prerequisites installed:

1. **Java Runtime:** Ensure that you have Java Runtime Environment (JRE) installed on your system.

2. **Chrome Browser:** The application uses Selenium with ChromeDriver, so make sure you have the Chrome browser installed.

## Setup

1. **Download ChromeDriver:**
   - Download the appropriate version of ChromeDriver.exe from the official website: [ChromeDriver Downloads](https://sites.google.com/chromium.org/driver/)
   - Place the downloaded `ChromeDriver.exe` in a location accessible to your system.

2. **Configure LinkedIn_Scrapper.MainBot.java:**
   - Open `LinkedIn_Scrapper.MainBot.java` and insert the following fields:
     1. **Webdriver Path:** Path to the location where `ChromeDriver.exe` is stored.
     2. **LinkedIn Email/Username:** Your LinkedIn email ID or username.
     3. **LinkedIn Password:** Your LinkedIn account password.
     4. **Keywords:** Add relevant keywords related to the job profiles you want to search for.
     5. *Optional:* Adjust page scroll count and length based on your requirements.

These steps will create two files, one with email-ids scrapped for sending the mail and URLs which can be used to apply for job roles.

3. Once the Email Sender gets triggered, you can either paste email-ids directly or share the path to the file containing email-ids.

4. Enter Email body/sender/receiver/subject fields in `Gmail_API.EmailMain`.

5. Insert the following fields in `Gmail_API.EmailSender.java`:
   1. Insert your email ID from which you are sending the email.
   2. Also insert Gmail API app password that you can enable from your Google account.
   3. Attach Cover-letter or resume if required.

## Command to run the Modules:

1. Start LinkedIn Email/Url Scrapping
   ```cmd
   mvn exec:java -D exec.mainClass="LinkedIn_Scrapper.MainBot"
   ```
## if email sender bot doesn't get triggered use below command
   ```cmd
   mvn exec:java -D exec.mainClass="Gmail_API.EmailMain"
   ```
