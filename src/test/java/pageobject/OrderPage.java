package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    //Инициализация драйвера
    private final WebDriver driver;
    public OrderPage(WebDriver driver) {
        this.driver=driver;
    }

    // Заказ самоката
    //Персональные данные
    //Поле ввода Имя
    private final By firstNameInput = By.xpath(".//input[contains(@placeholder,'Имя')]");
    //Поле ввода Фамилия
    private final By lastNameInput = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    //Поле ввода Адрес
    private final By adressInput = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    //Поле ввода Станция Метро
    private final By metroInput = By.xpath(".//input[contains(@placeholder,'метро')]");
    //Поле ввода Телефон
    private final By phoneInput = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    //Кнопка Далее
    private final By nextOrderButton = By.xpath(".//div[contains(@class,'Order_NextButton')]/button[contains(@class,'Button_Middle')]");

    //Страница 2 - Дата, срок и т.п.
    //Поле ввода Календарь
    private final By dateInput = By.xpath(".//div[contains(@class,'react-datepicker__input-container')]/input[contains(@class, 'Input_Responsible')]");
    //Поле ввода Срок аренды
    private final By rentalPeriodInput = By.xpath(".//div[contains(@class,'Dropdown-control')]");
    //Чекбокс Чёрный жемчуг
    private final By checkBoxBlack = By.xpath(".//label/input[contains (@id,'black')]");
    //Чекбокс Серая безысходность
    private final By checkBoxGrey = By.xpath(".//label/input[contains (@id,'grey')]");
    //Поле ввода Комментарий для курьера
    private final By commentCourierInput = By.xpath(".//div[contains(@class,'Input_InputContainer')]/input[contains(@class, 'Input_Responsible')]");
    //Кнопка Заказать
    private final By OrderButton = By.xpath(".//div[contains(@class,'Order_Buttons')]/button[contains(@class, 'Button_Middle')][2]");
    //Кнопка Назад
    private final By PervousOrderButton = By.xpath(".//div[contains(@class,'Order_Buttons')]/button[contains(@class, 'Button_Middle')][1]");
    //Попап подтверждения заказа
    //Кнопка Да
    private final By yesPopupButton = By.xpath(".//div[contains(@class,'Order_Button')]/button[contains(@class, 'Button_Middle') and text()='Да']");
    //Кнопка Нет
    //private final By noPopupButton = By.xpath(".//div[contains(@class,'Order_Button')]/button[contains(@class, 'Button_Middle') and text()='Нет']");
    /**
     *
     * @param firstName  Имя
     * @param lastName Фамилия
     * @param adress Адрес
     * @param metroStation Метро
     * @param phone Телефон
     */
    public void fillUserInfo (String firstName,String lastName, String adress, String metroStation, String phone)
    { new org.openqa.selenium.support.ui.WebDriverWait(driver,java.time.Duration.ofSeconds(5)).until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(adressInput).sendKeys(adress);
        driver.findElement(metroInput).sendKeys(metroStation);
        //Тыкаем на станцию в выпадающем списке
        org.openqa.selenium.By metroOption = org.openqa.selenium.By.xpath(".//div[text()='"+metroStation+"']");
        driver.findElement(metroOption).click();

        driver.findElement(phoneInput).sendKeys(phone);
    }
    public void clickNextOrderButton(){
        driver.findElement(nextOrderButton).click(); // нажатие кнопки Далее на странице заполнения персональных данных
    }

    //Страница 2

    /**
     *
     * @param date Дата заказа
     * @param rentalPeriod Период заказа (1-7)
     * @param commentCourier Комментарий курьеру
     * @param colorCheckBox Цвет самоката (black/grey)
     */
    public void fillOtherInfo(String date, int rentalPeriod, String commentCourier, String colorCheckBox)
    {
        //Ввод даты
        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(dateInput).sendKeys(Keys.ENTER);
        //Срок аренды
        driver.findElement(rentalPeriodInput).click();
        String[] period = {"сутки","двое суток","трое суток","четверо суток","пятеро суток","шестеро суток","семеро суток"};
        rentalPeriod=rentalPeriod-1;
        By periodOption = By.xpath(".//div[contains(@class,'Dropdown-option') and text()='"+period[rentalPeriod]+"']");
        driver.findElement(periodOption).click();
        //Выбор цвета
        if("black".equals(colorCheckBox))
        {
            driver.findElement(checkBoxBlack).click();
        } else if ("grey".equals(colorCheckBox)) {
            driver.findElement(checkBoxGrey).click();
        }
        //Комментарий курьеру
        driver.findElement(commentCourierInput).sendKeys(commentCourier);
    }
    //Попап
    public void clickYesPopup(){
        driver.findElement(yesPopupButton).click();
    }
    public void clickOrderButton(){
        driver.findElement(OrderButton).click();
    }
}


