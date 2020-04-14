package lmatevosyan.test;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.className;

public class FirstTest extends WebDriverSetUp {
    protected static int sum = 0;

    @Test
    public void testBusketCost() {

        gotoOzonPage();
        checkUrl(driver.getCurrentUrl(), "https://www.ozon.ru/");
        //chooseTwoBooks();

        //Поиск товара
        driver.findElement(By.name("search")).sendKeys("Художественная литература");
        driver.findElement(By.xpath("//div[@class='ui-a6 ui-h4']//button[@class='ui-a9 ui-h4']")).click();
        //Ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Random rand = new Random();
        while (sum < 1500) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //Поиск всех кнопок "В корзину" из списка найденных товаров
            String allProductsButtonXpath = "//div/div/div[1]/div[3]/div/div/button";
            //Создание списка веб-элементов
            List<WebElement> allProducts = driver.findElements(By.xpath(allProductsButtonXpath));
            //вывод произвольного номера товара
            System.out.println(rand.nextInt(allProducts.size()));
            //нажатие кнопки "В корзину" для выбранного товара
            try {
                new Actions(driver).moveToElement(allProducts.get(rand.nextInt(allProducts.size()))).perform();
                allProducts.get(rand.nextInt(allProducts.size())).click();
            } catch (ElementClickInterceptedException e) {
                driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/div/div[3]/div/button")).click();
                new Actions(driver).moveToElement(allProducts.get(rand.nextInt(allProducts.size()))).perform();
                allProducts.get(rand.nextInt(allProducts.size())).click();
            }

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            //Переход в корзину
            driver.findElement(By.xpath("//a[@href='/cart']")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //Проветка общей стоимости корзины
            char[] priceArr = driver.findElement(By.xpath("//span[contains(text(), 'Общая стоимость')]/following::span[1]")).getText().toCharArray();
            char[] newPriceArr = new char[priceArr.length - 2];
            for (int i = 0; i < priceArr.length - 2; i++) {

                newPriceArr[i] = priceArr[i];
            }
            if (newPriceArr.length > 3) {
                String newString = String.valueOf(newPriceArr);
                String[] newStringArr = newString.split(" ");
                String sumString = newStringArr[0] + newStringArr[1];
                sum = Integer.parseInt(sumString);
            } else {
                sum = Integer.parseInt(String.valueOf(newPriceArr));
            }

            System.out.println(sum);
            if (sum < 1500) {
                driver.navigate().back();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void gotoOzonPage() {

        //Переход на Озон
        driver.get("https://www.ozon.ru/");

    }

    @Step
    public void checkUrl(String str1, String str2) {
        Assert.assertEquals(str1, str2);
    }


    private void chooseTwoBooks() {
        //Поиск товара
        driver.findElement(By.name("search")).sendKeys("На западном фронте без перемен");
        driver.findElement(By.xpath("//div[@class='b9i5']//button[@class='ui-a9 ui-h4']")).click();
        //Ожидание загрузки страницы
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


}
