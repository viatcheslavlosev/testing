//Тест 1

package com.pochta.tеsts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleYandex {
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
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

  }

  @Test
  public void testGoogleYandex() throws Exception {
    driver.get(baseUrl + "/");

    //в поисковике вводим яндекс маркет
    driver.findElement(By.name("q")).sendKeys("яндекс маркет");
    driver.findElement(By.xpath("//form[@id='tsf']/div[2]")).click();

    //переходим по ссылке, открываются ссылки, в том числе на яндекс маркет
    driver.findElement(By.cssSelector("div.FPdoLc.VlcLAe > center > input[name=\"btnK\"]")).click();
    driver.findElement(By.cssSelector("div.ellip")).click();

    //Находим WebElement с id "rso" содержащий ссылки, получаем html элемента
    WebElement WStr1 = driver.findElement(By.id("rso"));
    String Str1 = WStr1.getAttribute("innerHTML");
    //Здесь должен быть код, который проверяет, что первая ссылка в строке Str1 ссылается на яндекс маркет, т.е. href="https://market.yandex.ru/
    // { ... }
  }

  @After
  public void quit() throws Exception {
    driver.quit();
  }

}
