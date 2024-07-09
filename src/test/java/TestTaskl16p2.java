import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestTaskl16p2 extends BaseTestTaskl16 {

    private void switchToPayment(){

        WebElement phone = driver.findElement(By.xpath(PHONE_NUMBER_FIELD));
        phone.click();
        phone.sendKeys(PHONE_NUMBER);
        WebElement sum = driver.findElement(By.xpath(SUM_NUMBER_FIELD));
        sum.click();
        sum.sendKeys("3");
        sum.submit();
        WebElement iframe = driver.findElement(By.xpath(IFRAME_PAYMENT));
        driver.switchTo().frame(iframe);
    }

    @Test
    @DisplayName("Проверить корректность отображения суммы в окне (в том числе на кнопке)")
    public void checkSumCorrect(){

        switchToPayment();
        WebElement depositAmount = driver.findElement(By.xpath("//span[text() = '2.00 BYN']"));
        WebElement buttonDepositAmount = driver.findElement(By.xpath(BUTTON_DEPOSIT_XPATH));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(buttonDepositAmount));

        assertAll(
                () -> assertEquals("2.00 BYN", depositAmount.getText()),
                () -> assertEquals("Оплатить 2.00 BYN", buttonDepositAmount.getText()));
    }

    @Test
    @DisplayName("Проверить корректность отображения номера телефона")
    public void checkPhoneNumberCorrect() {

        switchToPayment();
        WebElement paymentInfo = driver.findElement(By.xpath("//div[@class='pay-description__text']/span"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(paymentInfo));
        String expected = "Оплата: Услуги связи Номер:375297777777";
        assertEquals(expected, paymentInfo.getText());
    }

    @Test
    @DisplayName("Проверить надписи в незаполненных полях для ввода реквизитов карты")
    public void checkTextIBlankCard() {

        switchToPayment();
        WebElement cardNumberField = driver.findElement(By.xpath("//label[text()='Номер карты']"));
        WebElement durationPeriodField = driver.findElement(By.xpath("//label[text()='Срок действия']"));
        WebElement holderNameField = driver.findElement(By.xpath("//label[text()='Имя держателя (как на карте)']"));
        WebElement CVCField = driver.findElement(By.xpath("//label[text()='CVC']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(cardNumberField));

        String expectedCardNumberField = "Номер карты";
        String expectedDurationPeriodField = "Срок действия";
        String expectedHolderNameField = "Имя держателя (как на карте)";
        String expectedCVCField = "CVC";

        assertAll(
                () -> assertEquals(expectedCardNumberField, cardNumberField.getText()),
                () -> assertEquals(expectedDurationPeriodField, durationPeriodField.getText()),
                () -> assertEquals(expectedHolderNameField, holderNameField.getText()),
                () -> assertEquals(expectedCVCField, CVCField.getText()));
    }

    @Test
    @DisplayName("Проверить наличие иконок платёжных систем")
    public void checkPaymentSystemIcon() {

        switchToPayment();
        WebElement header = driver.findElement(By.xpath("//div[@class='header__container']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(header));

        List<WebElement> logoElements = driver.findElements(By.xpath("//div[@class='icons-container ng-tns-c46-1']"));
        for (WebElement logo : logoElements) {
            assertTrue(logo.isDisplayed());
        }
    }
    }
