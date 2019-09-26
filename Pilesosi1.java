//Тест 2

package com.pochta.tеsts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;


public class Pilesosi1 {
  private WebDriver driver;
  private String baseUrl;

  @Before
  public void setUp() throws Exception {

    //Создаем объект chrome driver.
    System.setProperty("webdriver.chrome.driver", "C:\\Java\\cromedriver\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("disable-infobars");
    options.addArguments("--start-maximized");
    driver = new ChromeDriver(options);
    baseUrl = "https://market.yandex.ru/";
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
  }

  @Test
  public void testPilesosi1() throws Exception {

    //произойдет переход на страницу с пылесосами
    driver.get(baseUrl + "/");
    driver.findElement(By.id("header-search")).click();
    driver.findElement(By.id("header-search")).clear();
    driver.findElement(By.id("header-search")).sendKeys("пылесосы");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("div._3nuwI0jgrK")).click();

    //Выбрать в фильтре Polaris и Vitek
    WebElement WStr2 = driver.findElement(By.xpath("//div[@id='search-prepack']/div/div/div[3]/div/div/div[2]/div[4]/div/div/fieldset/ul/li[8]/div/a/label/div"));
    WStr2.click();
    //Проверяем, что в фильре checkbox установлен для Polaris
    assertTrue(WStr2.isSelected());
    System.out.println("WStr2: " + WStr2.isSelected());

    WebElement WStr3 = driver.findElement(By.xpath("//div[@id='search-prepack']/div/div/div[3]/div/div/div[2]/div[4]/div/div/fieldset/ul/li[12]/div/a/label/div"));
    WStr3.click();
    //Проверяем, что в фильтре checkbox установлен для Vitek
    assertTrue(WStr3.isSelected());
    System.out.println("WStr3: " + WStr3.isSelected());

    //Устанавливаем в фильтре цену в поле до = 6000
    WebElement WStr4 = driver.findElement(By.id("glpriceto"));
    WStr4.sendKeys("6000");

    //Проверяем, что цена до=6000, установлена в фильтре
    WebElement WStr7 = driver.findElement(By.xpath("//*[@id=\"search-prepack\"]/div/div/div[3]/div/div/div[2]/div[1]/div/div/fieldset/div[1]/ul/li[2]"));
    String Str11 = WStr7.getAttribute("innerHTML");
    //Здесь должен быть код, который проверяет, что в строке Str1 в атрибуте value установлена цена 6000
    // { ... }

    //Находим WebElement, содержащий  список всех доступные для выбора производителей
    WebElement WStr1 = driver.findElement(By.xpath("//*[@id=\"search-prepack\"]/div/div/div[3]/div/div/div[2]/div[4]/div/div/fieldset/div[2]/ul"));

    //С помощью javascript пПрокручиваем список всех доступных для выбора производителей чтобы все производители попали в DOM
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true)", WStr1);

    //Получаем String  всех производителей из WebElement WStr1
    String Str1 = WStr1.getText();

    System.out.println("Производители:");
    System.out.println("");
    System.out.println(Str1);

    //Записываем String со всеми производителями в лог файл
    try {
      FileWriter writer = new FileWriter("1.txt");
      writer.write(Str1);
      writer.flush();
      writer.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }

  }

  @After
  public void tearDown() throws Exception {
    driver.quit();

  }

}
