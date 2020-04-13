package lmatevosyan.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.className;

public class FirstTest {
    public static WebDriver driver;
    private static int sum = 0;

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver","./src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testBusketCost() {

        gotoOzonPage();

        //chooseTwoBooks();

        //Поиск товара
        driver.findElement(By.name("search")).sendKeys("Художественная литература");
        driver.findElement(By.xpath("//div[@class='ui-a6 ui-h4']//button[@class='ui-a9 ui-h4']")).click();


        while(sum < 1500) {

            //Ожидание загрузки старницы
           // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            String allProductsXpath1 = "//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[4]/div[1]/div/div/div//img";
            String allProductsXpath2 = "//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div/div/div//img";
            //*[@id="__nuxt"]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div/div/div//img
            try {
                selectRandomProduct(allProductsXpath1);
            }
            catch (IllegalArgumentException e){
                selectRandomProduct(allProductsXpath2);
            }


            //Get the price
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            char[] priceArr = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[4]/div[2]/div[2]/div/div[3]/div[2]/div/div[1]/div/div/div/div/div[1]/div/span[1]")).getText().toCharArray();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                //WebDriverWait wait = new WebDriverWait(driver, 10);
                //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[4]/div[2]/div[2]/div/div[3]/div[2]/div/div[1]/div/div/div/div/div[5]/div/div/div/div/div/div/button"))));
                //driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[4]/div[2]/div[2]/div/div[3]/div[2]/div/div[1]/div/div/div/div/div[5]/div/div/div/div/div/div/button")).click();
                driver.findElement(By.xpath("//div[text()='Добавить в корзину']")).click();
            }
            catch (NoSuchElementException | ElementClickInterceptedException e){
                System.out.println("Не нашел кнопку Купить");
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[text()='+1 шт.']"))));
                   //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.findElement(By.xpath("//div[text()='+1 шт.']")).click();
            }
            char[] newPriceArr = new char[priceArr.length - 2];
            for (int i = 0; i < priceArr.length - 2; i++) {
                newPriceArr[i] = priceArr[i];
            }
            System.out.println(newPriceArr);
            sum += Integer.parseInt(String.valueOf(newPriceArr));
            driver.navigate().back();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        System.out.println(sum);


    }
    //@Step
    public void gotoOzonPage() {

        //Переход на Озон
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.ozon.ru/");
    }
    public void selectRandomProduct(String xpath) {

        //Нажать на изображение произвольного товара
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */

        List <WebElement> allProducts = driver.findElements(By.xpath(xpath));
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        Actions action = new Actions(driver);
        action.moveToElement(allProducts.get(randomProduct)).click().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }



    private void chooseTwoBooks() {
        //Поиск товара
        driver.findElement(By.name("search")).sendKeys("На западном фронте без перемен");
        driver.findElement(By.xpath("//div[@class='b9i5']//button[@class='ui-a9 ui-h4']")).click();
        //Ожидание загрузки старницы
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Нажатие кнопки КУПИТЬ первого товара
        driver.findElement(By.xpath("//div[@class='a5u4 ui-a6']/button[@class='ui-a9']")).click();
        //Очистка поля поиска
        driver.findElement(className("b9i2")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Поиск второго товара
        driver.findElement(By.name("search")).sendKeys("На обратном пути");
        driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/header/div[1]/div[3]/div/form/div[3]/div/button")).click();
        //Ожидание загрузки страницы

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div/div[1]/div/div[1]/div[3]/div/div/button")).click();

        //Переход в корзину
        driver.findElement(By.xpath("//a[@href='/cart']")).click();
    }


   /* @AfterClass
    public void tearDown() {

        driver.quit();
    }

    */


}
