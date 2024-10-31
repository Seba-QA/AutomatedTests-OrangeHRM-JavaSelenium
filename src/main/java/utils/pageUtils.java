package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Response;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Optional;


import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Logger;

public class pageUtils {
    /*Elementos comunes*/
    Actions actions;
    public Logger LOGGER = Logger.getLogger("Pruebas-portafolio");
    private static final int TIMEOUT = 10;
    private static final int POLLING = 100;
    public WebDriver driver;
    private final WebDriverWait wait;
    private DevTools devTools;

    /*Constructor de la pagina*/
    public pageUtils(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofSeconds(POLLING));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
        // Inicialización de DevTools si estamos usando ChromeDriver
        if (driver instanceof ChromeDriver) {
            this.devTools = ((ChromeDriver) driver).getDevTools();
            devTools.createSession();
        }
    }

    /* Realiza una espera de tiempo en seg
       parametro seconds seran los segundos a esperar*/
    public void waitFor(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void log(String message){
        LOGGER.info(message);
    }

    public void logWarn(String message){
        LOGGER.warning(message);
    }

    public void logError(String message) { LOGGER.severe(message); }

    protected boolean isVisible(WebElement WebElement, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOf(WebElement));
            return true;
        } catch (NoSuchElementException e) {
            logWarn("Elemento no visible");
            return false;
        }catch (TimeoutException e) {
            logWarn("Timeout");
        }catch (Exception e) {
            return false;
        }
        return false;
    }

    public void newTag(String url, int num){
        //Se usa para abrir una nueva pestaña
        ((JavascriptExecutor) driver).executeScript("window.open()");

        /*Cambia a la nueva pestaña
          Observacion: las pestañas se manejan como listas con el indice en 0*/
          ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
          driver.switchTo().window(tabs.get(num));

          //carga una nueva url
          driver.get(url);
    }

    public boolean waitForElementEnable(WebElement element, int timeoutSeconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            //queda a la espera que el elemento esté prestente y visible
            wait.until(ExpectedConditions.elementToBeClickable(element));
            wait.until((WebDriver D) -> !element.getAttribute("class").contains("disabled"));
            return true;
        } catch (Exception e) {
            logWarn("Ocurrio un error" + e.getMessage());
            return false;
        }
    }

    public void doubleClick(WebElement element){
        new Actions(driver).doubleClick(element).perform();
    }

    // Nueva función reutilizable para interceptar la respuesta de la API después de una interacción
    public String getApiResponse(String apiUrl, WebElement elementToInteract) {
        String[] interceptedApiResponse = {null};

        if (devTools != null) {
            // Habilitar la interceptación de red
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            // Añadir listener para interceptar la respuesta de la API
            devTools.addListener(Network.responseReceived(), responseReceived -> {
                Response response = responseReceived.getResponse();
                if (response.getUrl().contains(apiUrl)) {
                    // Almacenamos la respuesta si coincide con la API especificada
                    interceptedApiResponse[0] = String.valueOf(response.getStatus());
                }
            });

            // Realizar la interacción con el elemento (por ejemplo, hacer clic en un botón)
            elementToInteract.click();

            // Pausar para esperar que la solicitud y la respuesta se completen
            waitFor(5);

        } else {
            logWarn("DevTools no está habilitado. Esta funcionalidad solo está disponible con ChromeDriver.");
        }

        // Devolver la respuesta interceptada
        return interceptedApiResponse[0];
    }
}
