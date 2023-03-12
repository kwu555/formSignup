package com.badminton.signup.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormSignup{

    public static final String XPATH_EMAIL = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div[1]/div[2]/div[1]/div/div[1]/input";
    public static final String XPATH_FULL_TUES_TEXT = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/label/div/div[2]/div/span";
    public static final String XPATH_FULL_THUR_TEXT = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[2]/label/div/div[2]/div/span";

    public static final String XPATH_NAME_CLICK_DROPDOWN = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div";
    public static final String XPATH_NAME_SELECT_NAME = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[2]";
    public static final String XPATH_REGISTER_TUES__TICK = "//*[@id=\"i25\"]";
    public static final String XPATH_REGISTER_THUR__TICK = "//*[@id=\"i28\"]";

    public static final String XPATH_SUBMIT = "//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div/div[1]/div";

    public static final String BASE_URL = "https://docs.google.com/forms/d/e/1FAIpQLSfA9zHG9OUYUCr57fC7CuAKyvf1_DmqdC_kZrBB4gGRNN48fQ/viewform";

    public boolean signup(String chooseName, String day){



        WebDriver driver = chromeSetup();
//        WebDriver driver = firefoxSetup();


        String dayPath;
        String dayClick;
        String compare;
        if(day.equals("TUES")){
            dayPath = XPATH_FULL_TUES_TEXT;
            dayClick = XPATH_REGISTER_TUES__TICK;
            compare = "*FULL on Tuesday*";
        }else{
            dayPath = XPATH_FULL_THUR_TEXT;
            dayClick = XPATH_REGISTER_THUR__TICK;
            compare = "*FULL on Thursday*";

        }


        boolean successfulSignup = false;

        try{

            // launch Fire fox and direct it to the Base URL
            driver.get(BASE_URL);
            Thread.sleep(2000);

            // get the actual value of the title
            String isFullText = driver.findElement(By.xpath(dayPath)).getText();

            // not full, then continue
            if(!isFullText.equals(compare)){

                // email
                driver.findElement(By.xpath(XPATH_EMAIL)).sendKeys("kwu0521@gmail.com");
                Thread.sleep(2000);

                // name dropdown
                driver.findElement(By.xpath(XPATH_NAME_CLICK_DROPDOWN)).findElements(By.xpath("div")).get(0).click();
                Thread.sleep(2000);

                List<WebElement> names = driver.findElement(By.xpath(XPATH_NAME_SELECT_NAME)).findElements(By.xpath("div"));
                Optional<WebElement> name = names.stream().filter(x->x.getText().equals(chooseName)).findFirst();
                name.ifPresent(WebElement::click);

                Thread.sleep(2000);

                // TICK day
                driver.findElement(By.xpath(dayClick)).click();
                Thread.sleep(2000);

                // submit
			    driver.findElement(By.xpath(XPATH_SUBMIT)).click();
                Thread.sleep(2000);
                successfulSignup = true;
            }

        }catch(InterruptedException e){
            e.printStackTrace();
        }

        driver.close();
        return successfulSignup;
    }

    private WebDriver chromeSetup(){
        System.setProperty("webdriver.chrome.driver","/Users/haijiewu/Desktop/Intellij/signup/src/main/resources/driver/chromedriver_mac64/chromedriver");
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(ops);
    }

    private WebDriver firefoxSetup(){
        System.setProperty("webdriver.gecko.driver","/Users/haijiewu/Desktop/Intellij/signup/src/main/resources/driver/chromedriver_mac64/chromedriver");

        return new FirefoxDriver();
    }
}
