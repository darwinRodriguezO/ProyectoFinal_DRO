package automation.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.PadrePage;

public class MyAccountPage extends PadrePage {

    @FindBy(xpath = "//h1[@class='page-heading' and text()='My account']")
    WebElement myAccountHeader;
    @FindBy(linkText = "My addresses")
    WebElement myAddressesBtn;
    @FindBy(xpath = "//a[@title='Information']//span")
    WebElement myPersonalInformationBtn;

    // Xpath seleccionados...
    @FindBy(xpath = "//div[@class='product_label']")
    WebElement headerProducts;
    @FindBy(xpath = "//div[@class='login_logo']")
    WebElement headerLogo;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    public void ingresarMyAddresses(){
        scrollIntoViewElementAndClick(myAddressesBtn);
    }
    public void ingresarMyPersonaInformation(){
        scrollIntoViewElementAndClick(myPersonalInformationBtn);
    }
    public boolean verifificarSiCargoPagina(){
        if(cargoElemento(myAccountHeader)){
            return true;
        }else{
            return false;
        }
    }
    // Procedimientos para verificar logeos:

    public boolean verifificarSiLogueoExitoso(){
        if(cargoElemento(headerProducts)){
            return true;
        }else{
            return false;
        }
    }

    public boolean verifificarSiDeslogueoExitoso(){
        if(cargoElemento(headerLogo)){
            return true;
        }else{
            return false;
        }
    }

}
