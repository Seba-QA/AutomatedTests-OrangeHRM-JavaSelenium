package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.pageUtils;

public class login extends pageUtils{
    public login(WebDriver driver){
        super(driver);
    }

    /*Elementos de la pagina*/
    @FindBy(xpath = "//input[@name='username']") WebElement inputUser;
    @FindBy(xpath = "//input[@name='password']") WebElement inputPass;
    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'orangehrm-login-button')]") WebElement btnLogin;
    @FindBy(xpath = "//h6[text()='Dashboard']") WebElement dashboard;


    /*Funciones del paso a paso*/

    /*--Funcion general para iniciar sesion de forma valida--*/
    public void navegarUrl(){
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(url);
    }

    public void loginVal(String user, String pass){
        waitFor(2);
        inputUser.sendKeys(user);
        waitFor(2);
        inputPass.sendKeys(pass);
        waitFor(5);
    }

    public  void  log(){
        btnLogin.click();
        waitFor(5);
    }
    /*Fin funcion general para iniciar sesion*/

    public void valDashboard(){
        //Validacion de que dashoard carga correctamente
        if(!waitForElementEnable(dashboard, 3)){
            logError("El dashoard no cargo correctamente al iniciar sesion");
            logWarn("Se debe revisar mas a fondo la razon");
        }else {
            log("El dashoard cargo correctamente");
            log("El inicio de sesion fue exitoso");
        }
    }
}