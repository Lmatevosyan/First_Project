package lmatevosyan.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\lmatevosyan\\AppData\\Local\\Packages\\Microsoft.MicrosoftEdge_8wekyb3d8bbwe\\TempState\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void gotoOzonPage() {

        driver.get("https://www.ozon.ru/");

       driver.findElement(By.className("c0f0")).sendKeys("На западном фронте без перемен");

       driver.findElement(By.className("c0f5")).click();
       driver.switchTo().newWindow(WindowType.TAB);

       //driver.findElement(By.xpath("//div[@class='a3f4']/a[@href='/context/detail/id/31229255/']")).click();


    }

    @AfterClass
    public void tearDown() {

        driver.quit();

    }

}
