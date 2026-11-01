package samokatTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.concurrent.TimeUnit;
@RunWith(org.junit.runners.Parameterized.class)
public class deepQuestions {


    private org.openqa.selenium.WebDriver driver;
    private final int index;
    private final String expectedAnswerText;
    private final String browser;
    public deepQuestions(String browser,int index,String expectedAnswerText){
        this.browser=browser;
        this.index=index;
        this.expectedAnswerText=expectedAnswerText;
    }
    @Parameterized.Parameters
    public static Object[][] getDeepQuestionData(){
        return new Object[][]{
                {"chrome",0,"Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"chrome",1,"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"chrome",2,"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"chrome",3,"Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"firefox",4,"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"firefox",5,"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"firefox",6,"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"firefox",7,"Да, обязательно. Всем самокатов! И Москве, и Московской области."}
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @After
    public void quitBrowser()
    {
        driver.quit();
    }

    @Test
    public void testDeepQuestions(){
        pageobject.MainPage mainPage = new pageobject.MainPage(driver);
        mainPage.openMain();
        String answerText = mainPage.openQuestionAndGetAnswer(index);
        org.junit.Assert.assertEquals("Текст ответа не совпадает для вопроса "+index,expectedAnswerText,answerText);
    }
}
