package lmatevosyan.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class WebDriverSetUp {
    protected ChromeDriver driver;

    @BeforeClass
    public void setUp() { // This method will  be run before test and set the required parameters

        System.setProperty("webdriver.chrome.driver","./src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Fullscreen view
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void quit() {

        //     driver.quit(); // close browser
    }
}
