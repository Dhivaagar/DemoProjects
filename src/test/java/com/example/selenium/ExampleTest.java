package com.example.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import selenium.test.AlertHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExampleTest {

    @Test
    public void exampleTestMethod2() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://letcode.in/alert");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text()=' Alert']"))));

        captureScreen(driver);

        driver.findElement(By.id("accept")).click();
        System.out.println("Accept alert text : " +driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        driver.findElement(By.id("confirm")).click();
        System.out.println("Dismiss alert text : " +driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();

        driver.findElement(By.id("prompt")).click();
        System.out.println("Prompt alert text : " +driver.switchTo().alert().getText());
        driver.switchTo().alert().sendKeys("Dhivaagar");
        driver.switchTo().alert().accept();
        System.out.println("prompt MyName alert text : " +driver.findElement(By.id("myName")).getText());
        driver.quit();
    }

    private static void captureScreen(WebDriver driver){
        String path;
        //String tcName = "screen";
        try {
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String now = "";
            path = "./screenshots/screenshot.jpeg";

            //  path = "./screenshots/" + tcName + "  " +source.getName();
            // Now you can do whatever you need to do with it, for example copy somewhere

            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            //e.printStackTrace();
            path = "Failed to capture screenshot: " + e.getMessage();
        }
    }
}
