package automation.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.PadrePage;

public class MenuBar extends PadrePage {

    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    WebElement logout;

    @FindBy(css = "body.main-body:nth-child(2) div.page_wrapper:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(3) div.bm-burger-button > button:nth-child(2)")
    WebElement openMenu;

    public MenuBar(WebDriver driver) {
        super(driver);
    }


    public void abrirMenu(){
        openMenu.click();
    }
    public void seleccionarLogout(){
        scrollIntoViewElementAndClick(logout);
    }

}
