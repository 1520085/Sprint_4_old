package samokatTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)

public class orderSamokatTests {
    private WebDriver driver;
    //Переменные
    private final String browser;
    private final String buttonOrderLocation; //локация кнопки заказа
    private final String firstName;
    private final String lastName;
    private final String adress;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final int rentalPeriod;
    private final String commentCourier;
    private final String color;

    public orderSamokatTests(String browser, String buttonOrderLocation, String firstName, String lastName, String adress, String metroStation, String phone, String date, int rentalPeriod, String commentCourier, String color)
    {   this.browser=browser;
        this.buttonOrderLocation=buttonOrderLocation;
        this.firstName=firstName;
        this.lastName=lastName;
        this.adress=adress;
        this.metroStation=metroStation;
        this.phone=phone;
        this.date=date;
        this.rentalPeriod=rentalPeriod;
        this.commentCourier=commentCourier;
        this.color=color;
    }
    @Parameterized.Parameters
    public static Object[][] getOrderData(){
        return new Object[][]
                {
                        {"chrome", "top","Мария","Чайкина","Улица Чехова 3", "Лубянка", "+79096662211", "22.10.2026",1,"Только привезите рабочий!","black"},
                        {"firefox", "down","Абдулла","Измайлович","Ленина 17", "Технопарк", "81115606644","23.11.2026",4,"Брат, только не опаздывай!","grey"}
                };
    }
    @Before
    public void OpenBrowser()
    {
        if ("firefox".equals(browser))
        {
            org.openqa.selenium.firefox.FirefoxOptions options = new org.openqa.selenium.firefox.FirefoxOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver=new org.openqa.selenium.firefox.FirefoxDriver(options);
        }else{
            org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver=new org.openqa.selenium.chrome.ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
    }

    @After
    public void quitBrowser()
    {
        driver.quit();
    }

    @Test
    public void testOrderSamokat() throws InterruptedException
    {
        pageobject.MainPage mainPage = new pageobject.MainPage(driver);
        pageobject.OrderPage orderPage = new pageobject.OrderPage(driver);

        mainPage.openMain(); //Открываем сайт
        //Выбор кнопки заказа
        if ("top".equals(buttonOrderLocation))
        {
            mainPage.clickHeaderOrderButton();
        } else if ("down".equals(buttonOrderLocation))
        {
            mainPage.clickFinishOrderButton();
        }

        //Заполнение персональных данных
        orderPage.fillUserInfo(firstName,lastName,adress,metroStation,phone);
        Thread.sleep(3000);
        //Отправка формы
        orderPage.clickNextOrderButton();
        //Заполнение остальных данных
        orderPage.fillOtherInfo(date,rentalPeriod,commentCourier,color);
        Thread.sleep(1000);
        //подтверждение заказа
        orderPage.clickOrderButton();
        Thread.sleep(1000);
    }
}
