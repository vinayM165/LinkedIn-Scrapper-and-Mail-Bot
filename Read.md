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

## Usage

Follow the steps below to run the modules:

1. **Start LinkedIn Email/URL Scrapping:**
   ```bash
   mvn exec:java -D exec.mainClass="LinkedIn_Scrapper.MainBot"
