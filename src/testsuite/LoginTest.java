package testsuite;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    static String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    //launching url
    public void setUp(){
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        //find username and password element and enter valid username and valid password
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //compare actual and expected text
        String exptectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//div[@class='example']//h2")).getText();
        Assert.assertEquals("Error", exptectedText,actualText);
    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        //find username and password element and enter invalid username and valid password
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //compare actual and expected text
        String exptectedText = "Your username is invalid!\n×";
        String actualText = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        Assert.assertEquals("Error", exptectedText,actualText);
    }

    @Test
    public void verifyThePasswordErrorMessage(){
        //find username and password elements and enter valid username and invalid password
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        String expectedText = "Your password is invalid!\n×";
        String actualText = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        Assert.assertEquals("Error", expectedText,actualText);
    }
}
