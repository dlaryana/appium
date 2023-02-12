package com.simplilearn.MobileProject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class FlipkartApp {
	
AndroidDriver<MobileElement> driver;
	
	@BeforeTest
	public void launchApplication() throws MalformedURLException {
		//Launch Flipkart app
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability("platformName", "ANDROID");
		cap.setCapability("appPackage", "com.flipkart.android");
		cap.setCapability("appActivity", "com.flipkart.android.SplashActivity");
		cap.setCapability("noReset", true);
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
	}
	
	@Test
	public void verifySteps() {
		//click on search field
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[(@text='Search for products')]")));
		driver.findElement(By.xpath("//*[(@text='Search for products')]")).click();
		
		//type ‘mobile’ in the search-box
		MobileElement searchField = driver.findElement(By.xpath("//*[@resource-id,'main_content']/android.widget.FrameLayout"
				+ "/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.EditText"));
		searchField.sendKeys("mobile");

	
		//click on the first search result
		driver.findElement(By.xpath("((//*[@resource-id,'main_content']/android.widget.FrameLayout/android.view.ViewGroup"
				+ "/android.view.ViewGroup/android.view.ViewGroup)[3]/android.widget.ScrollView/android.view.ViewGroup"
				+ "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[1]")).click();

		//handle pop up window if any
		MobileElement popWindow = driver.findElement(By.id("com.flipkart.android:id/not_now_button"));	
		boolean isDisplayed = popWindow.isDisplayed();
		while (isDisplayed) 
		{
			popWindow.click();
		}
	
	    //get text of the first product to validate later
		 MobileElement firstProdTitle = driver.findElement(By.xpath(""));
		 String firstProductTitle = firstProdTitle.getText();
		
		//click on the first product in the list
		driver.findElement(By.xpath("//*[@resource-id,'main_content']/android.widget.FrameLayout/android.view.ViewGroup"
				+ "/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup"
				+ "/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]")).click();
		
		//click on ‘Add to cart’ button
		driver.findElement(By.xpath("//*[@resource-id,'main_content']/android.widget.FrameLayout/android.view.ViewGroup"
				+ "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
				+ "/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup")).click();
		
		//click on ‘Go to cart’ button
		driver.findElement(By.xpath("//*[@resource-id,'main_content']/android.widget.FrameLayout/android.view.ViewGroup"
				+ "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
				+ "/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup")).click();
		
		//click on ‘Place Order’ button
		driver.findElement(By.xpath("//*[@resource-id,'main_content']/android.widget.FrameLayout/android.view.ViewGroup"
				+ "/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
				+ "/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup")).click();
		
		//if screen to insert number appears close it
		MobileElement phoneScreen = driver.findElement(By.id("com.flipkart.android:id/custom_back_icon"));
		boolean isVisible = phoneScreen.isDisplayed();
		while (isVisible) 
		{
			phoneScreen.click();
		}
		
		//The requirement was to verify ‘Order Summary’ as heading of the activity, but the order summary only appears after you 
		// provide your mobile number, since I am not a resident of India the below code is verifying if the first product 
		//we selected is the same we added in the cart
		
		MobileElement productTitle = driver.findElement(By.xpath("//*[@resource-id,'main_content']/android.widget.FrameLayout"
				+ "/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup"
				+ "/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[3]"
				+ "/android.widget.TextView[1]"));
		String product = productTitle.getText();
		
		
		//Validating titles are the same
		 if(product.equals(firstProductTitle)) {
		    	System.out.println("Title is the same");
		 }else {
		    	System.out.println("Title is not the same");
		 }
	    
		 driver.quit();

	}

}
