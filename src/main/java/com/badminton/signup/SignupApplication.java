package com.badminton.signup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.Optional;

//@SpringBootApplication
public class SignupApplication {

	public static final String XPATH_EMAIL = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div[1]/div[2]/div[1]/div/div[1]/input";
	public static final String XPATH_FULL_TUES_TEXT = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/label/div/div[2]/div/span";
	public static final String XPATH_NAME_CLICK_DROPDOWN = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div";
	public static final String XPATH_NAME_SELECT_NAME = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[2]";
	public static final String XPATH_REGISTER_TICK = "//*[@id=\"i25\"]";
	public static final String XPATH_SUBMIT = "//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div/div[1]/div";

	public static void main(String[] args) throws InterruptedException{
//		SpringApplication.run(SignupApplication.class, args);

// declaration and instantiation of objects/variables
		//System.setProperty("webdriver.chrome.driver","/Users/haijiewu/Desktop/Intellij/signup/src/main/resources/driver/chromedriver_mac64/chromedriver");
		System.setProperty("webdriver.gecko.driver","/home/kw/IdeaProjects/formSignup/src/main/resources/driver/chromedriver_mac64/geckodriver");

		//ChromeOptions ops = new ChromeOptions();
		FirefoxOptions ops = new FirefoxOptions();
		ops.addArguments("--remote-allow-origins=*");

//		WebDriver driver= (WebDriver) new ChromeDriver(ops);
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://docs.google.com/forms/d/e/1FAIpQLSfA9zHG9OUYUCr57fC7CuAKyvf1_DmqdC_kZrBB4gGRNN48fQ/viewform";

		// launch Fire fox and direct it to the Base URL
		driver.get(baseUrl);

		// get the actual value of the title
		String isFullText = driver.findElement(By.xpath(XPATH_FULL_TUES_TEXT)).getText();

		// not full, then continue
		if(isFullText.equals("*FULL on Tuesday*")){

			// email
			driver.findElement(By.xpath(XPATH_EMAIL)).sendKeys("kwu0521@gmail.com");
			Thread.sleep(2000);

			// name dropdown
			driver.findElement(By.xpath(XPATH_NAME_CLICK_DROPDOWN)).findElements(By.xpath("div")).get(0).click();
			Thread.sleep(2000);

			List<WebElement> names = driver.findElement(By.xpath(XPATH_NAME_SELECT_NAME)).findElements(By.xpath("div"));
			Optional<WebElement> name = names.stream().filter(x->x.getText().equals("Kevin Wu")).findFirst();
			name.ifPresent(WebElement::click);

			Thread.sleep(2000);
			// TICK Tuesday
			driver.findElement(By.xpath(XPATH_REGISTER_TICK)).click();
			Thread.sleep(2000);

			// submit
//			driver.findElement(By.xpath(XPATH_SUBMIT)).click();
		}

		//
		//close Fire fox
		driver.close();

	}


}
