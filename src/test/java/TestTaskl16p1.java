import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTaskl16p1 extends BaseTestTaskl16 {

    private List<WebElement> openDropdownList(){

        WebElement dropDownButton = driver.findElement(By.xpath(DROPDOWN_BUTTON));
        dropDownButton.click();

        return driver.findElements(By.xpath(LIST_TO_SELECT));
    }

    @Test
    @DisplayName("Проверить надписи в незаполненных полях каждого варианта оплаты услуг: услуги связи")
    public void checkTextCommunicationServices(){

        WebElement phoneHolder = driver.findElement(By.xpath("//input[@placeholder='Номер телефона']"));
        WebElement sumHolder = driver.findElement(By.xpath("input[@placeholder='Сумма']"));
        WebElement emailHolder = driver.findElement(By.xpath("//input[@placeholder='E-mail для отправки чека']"));

        assertAll(
                () -> assertEquals(EXPECTED_PHONE_NUMBER, phoneHolder.getAttribute("placeholder")),
                () -> assertEquals(EXPECTED_SUM, sumHolder.getAttribute("placeholder")),
                () -> assertEquals(EXPECTED_EMAIL_PAYCHECK, emailHolder.getAttribute("placeholder"))
        );
    }

    @Test
    @DisplayName("Проверить надписи в незаполненных полях каждого варианта оплаты услуг: домашний интернет")
    public void checkTextHomeInternet(){

        List<WebElement> listItems = openDropdownList();
        WebElement homeInternet = listItems.get(1);
        homeInternet.click();

        WebElement userNumberHolder = driver.findElement(By.xpath("//input[@placeholder='Номер абонента']"));
        WebElement sumHolder = driver.findElement(By.xpath("//input[@placeholder='Сумма']"));
        WebElement emailHolder = driver.findElement(By.xpath("//input[@placeholder='E-mail для отправки чека']"));

        assertAll(
                () -> assertEquals(EXPECTED_USER_NUMBER, userNumberHolder.getAttribute("placeholder")),
                () -> assertEquals(EXPECTED_SUM, sumHolder.getAttribute("placeholder")),
                () -> assertEquals(EXPECTED_EMAIL_PAYCHECK, emailHolder.getAttribute("placeholder"))
        );
    }

    @Test
    @DisplayName("Проверить надписи в незаполненных полях : рассрочка")
    public void checkTextInstallmentPlan(){

        List<WebElement> listItems = openDropdownList();
        WebElement installmentPayment = listItems.get(2);
        installmentPayment.click();

        WebElement accountNumberHolder = driver.findElement(By.xpath("//input[@placeholder='Номер счета на 44']"));
        WebElement sumHolder = driver.findElement(By.xpath("//input[@placeholder='Сумма']"));
        WebElement emailHolder = driver.findElement(By.xpath("//input[@placeholder='E-mail для отправки чека']"));

        assertAll(
                () -> assertEquals(EXPECTED_ACCOUNT_NUMBER, accountNumberHolder.getAttribute("placeholder")),
                () -> assertEquals(EXPECTED_SUM, sumHolder.getAttribute("placeholder")),
                () -> assertEquals(EXPECTED_EMAIL_PAYCHECK, emailHolder.getAttribute("placeholder"))
        );
    }

    @Test
    @DisplayName("Проверить надписи в незаполненных полях : задолженность")
    public void checkTextDebt(){

        List<WebElement> listItems = openDropdownList();
        WebElement dept = listItems.get(3);
        dept.click();

        WebElement debtAccountHolder = driver.findElement(By.xpath("//input[@placeholder='Номер счета на 2073']"));
        WebElement sumHolder = driver.findElement(By.xpath("//input[@placeholder='Сумма']"));
        WebElement emailHolder = driver.findElement(By.xpath("//input[@placeholder='E-mail для отправки чека']"));

        assertAll(
                () -> assertEquals(EXPECTED_DEBT_NUMBER, debtAccountHolder.getAttribute("placeholder")),
                () -> assertEquals(EXPECTED_SUM, sumHolder.getAttribute("placeholder")),
                () -> assertEquals(EXPECTED_EMAIL_PAYCHECK, emailHolder.getAttribute("placeholder"))
        );
    }



    }


