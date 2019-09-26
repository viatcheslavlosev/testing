//���� 1

package com.pochta.t�sts;

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

    //������� ������ chrome driver.
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

    //� ���������� ������ ������ ������
    driver.findElement(By.name("q")).sendKeys("������ ������");
    driver.findElement(By.xpath("//form[@id='tsf']/div[2]")).click();

    //��������� �� ������, ����������� ������, � ��� ����� ������ ������
    driver.findElement(By.cssSelector("div.FPdoLc.VlcLAe > center > input[name=\"btnK\"]")).click();
    driver.findElement(By.cssSelector("div.ellip")).click();

    //������� WebElement ���������� ������, �������� html ��������
    WebElement WStr1 = driver.findElement(By.id("rso"));
    String Str1 = WStr7.getAttribute("innerHTML");
    //����� ������ ���� ���, ������� ���������, ��� ������ ������ � ������ Str1 ��������� �� ������ ������, �.�. href="https://market.yandex.ru/
    // { ... }
  }

  @After
  public void quit() throws Exception {
    driver.quit();
  }

}
