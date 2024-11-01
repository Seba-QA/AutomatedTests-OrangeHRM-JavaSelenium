package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.*;
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
    @FindBy(xpath = "//div[contains(@class, 'error') and @role='alert']") WebElement alertErrorCredentiales;
    @FindBy(xpath = "//div[@class='oxd-alert-content oxd-alert-content--error']//p[contains(@class, 'oxd-alert-content-text') and text()='Invalid credentials']") WebElement msgErrorCredentiales;
    @FindBy(xpath = "//input[@name='username']/following::span[contains(@class, 'oxd-input-field-error-message')][1]") WebElement msgUser;
    @FindBy(xpath = "//input[@name='password']/following::span[contains(@class, 'oxd-input-field-error-message')][1]") WebElement msgPass;


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

    /*Validacion de casos donde no puede iniciar sesion*/
    public void validaCaso(String caso){
        boolean validacionAlerta;
        String mensaje = "Invalid credentials";
        boolean validacionMensaje;
        switch (caso) {
            case "USR_INCORRECTO":
            case "PASS_INCORRECTO":
            case "AMBOS_INCORRECTOS":
                // Acción a realizar para USR_INCORRECTO, PASS_INCORRECTO y AMBOS_INCORRECTOS
                validacionAlerta = waitForElementEnable(alertErrorCredentiales, 2);
                if(!validacionAlerta){
                    logError("La alerta con el mensaje de 'Invalid credentials' no aparecio correctamente");
                    logWarn("Se debe revisar alerta");
                }else{
                    validacionMensaje = mensaje.equals(msgErrorCredentiales.getText());
                    if(!validacionMensaje){
                        logError("El mensaje de 'Invalid credentials' no aparece correctamente");
                        logWarn("La alerta de aparece de forma correcta, pero sin el mensaje de 'Invalid credentials'");
                    }else{
                        log("La alerta y mensaje de 'Invalid credentials' se despliega correctamente");
                    }
                }
                break;

            case "USR_VACIO":
                // Acción a realizar para USR_VACIO
                validacionAlerta = waitForElementEnable(msgUser, 2);
                if(!validacionAlerta){
                    logError("La alerta con el mensaje de 'Required' no aparecio correctamente al dejar usuario vacio");
                    logWarn("Se debe revisar alerta");
                }else{
                    log("En construccion");
                }
                break;

            case "PASS_VACIO":
                // Acción a realizar para PASS_VACIO
                System.out.println("Campo contraseña vacío");
                break;

            case "AMBOS_VACIO":
                // Acción a realizar para AMBOS_VACIO
                System.out.println("Ambos campos están vacíos");
                break;

            default:
                // Acción por defecto si "caso" no coincide con ningún valor
                System.out.println("Caso no reconocido");
                break;
        }
    }
}