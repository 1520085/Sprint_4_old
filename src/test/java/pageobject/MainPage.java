package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    //Инициализация драйвера
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Открытие сайта
    public void openMain() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    //Локаторы "Вопросы о важном"
    //Локаторы вопросов и ответов с зависимостью от индекса (Баг - при переходе на страницу заказа и возврате индекс вопроса меняется в браузере)
    public By[] getAnswerAndQuestionLocator(int index) {
        By questionIndex = By.id("accordion__heading-" + index);
        By answerIndex = By.id("accordion__panel-" + index);
        return new By[]{questionIndex, answerIndex};
    }

    //Точка входа 0 (Кнопка "Заказать", верх страницы)
    private final By headerOrderButton = By.xpath(".//div[contains(@class,'Header_Nav')]/button[contains(@class,'Button_Button')]");
    //Точка входа 1 (Кнопка "Заказать, низ страницы)
    private final By finishOrderButton = By.xpath(".//div[contains(@class,'Home_FinishButton')]/button[contains(@class,'Button_Button')]");

    //Методы

    //Нажатие верхней кнопки заказать
    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    //Нажатие нижней кнопки заказать
    public void clickFinishOrderButton() {
        org.openqa.selenium.WebElement element = driver.findElement(finishOrderButton);
        //Скролл
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    //Открытие вопроса 0-7 и получение ответа
    public String openQuestionAndGetAnswer(int index) {
        org.openqa.selenium.By[] locators = getAnswerAndQuestionLocator(index);
        org.openqa.selenium.By questionLocator = locators[0]; //получаем локатор для вопроса
        org.openqa.selenium.By answerLocator = locators[1]; //получаем локатор для ответа
        org.openqa.selenium.WebElement questionElement = driver.findElement(questionLocator); //Поиск элемента вопроса
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionElement);//Скролл до элемента
        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("arguments[0].click();",questionElement); //Открытие вопроса
        new org.openqa.selenium.support.ui.WebDriverWait(driver,java.time.Duration.ofSeconds(3)).until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(answerLocator)); //Ожидание ответа
        String answerText = driver.findElement(answerLocator).getText();
        return answerText;
    }

}