package lmatevosyan.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

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

        driver.findElement(By.name("search")).sendKeys("На западном фронте без перемен");
        driver.findElement(By.xpath("//form[@class='c1b8']//button[@class='ui-a9 ui-h4']")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//div[@class='a7i0']//button[@class='ui-a9']")).click();
        driver.findElement(By.className("c1c1")).click();

        driver.findElement(By.name("search")).sendKeys("На обратном пути");
        driver.findElement(By.xpath("//form[@class='c1b8']//button[@class='ui-a9 ui-h4']")).click();

        driver.navigate().refresh();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//div[@class='a7i0']//button[@class='ui-a9']")).click();
        driver.findElement(By.xpath("//a[@href='/cart']")).click();

    }

   /*@AfterClass
    public void tearDown() {

        driver.quit();

    }*/

}
