package automation.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.PadrePage;

import java.util.HashMap;

public class SignInPage extends PadrePage {

    @FindBy(id="user-name")
    WebElement userTxt;
    @FindBy(id="password")
    WebElement passwordTxt;
    @FindBy(className="btn_action")
    WebElement btnSignIn;
    @FindBy(xpath = "//h3[1]")
    WebElement mensajeError;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void ingresarCredenciales(HashMap<Object,Object> inputData){
        scrollIntoViewElementAndSendKeys(userTxt   ,inputData.get("username").toString());
        scrollIntoViewElementAndSendKeys(passwordTxt,inputData.get("password").toString());
        scrollIntoViewElementAndClick(btnSignIn);
    }
    public String devolverMensajeError(){
        scrollIntoViewElement(mensajeError);
        return esperarTextoNoEsteVacioYDevolver(mensajeError);
    }



}
