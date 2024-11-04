package stepsTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.io.IOException;

public class Hooks {
    static WebDriver driver;
    private static boolean isReportFolderCleaned = false;
    private static String browser = "chrome";

    @Before
    public void setUp(Scenario scenario){
        if(!isReportFolderCleaned){
            checkEmptyFolder();
            isReportFolderCleaned = true;
        }
        configureWebDriver();
    }

    public void configureWebDriver(){
        //browser = "chrome";
        browser = "edge";
        if(browser.equals("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-software-rasterizer");
            options.addArguments("--disable-site-isolation-trials", "--no-sandbox", "--incognito");
            options.addArguments("--start-maximized");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }else if (browser.equals("edge")){
            EdgeOptions options = new EdgeOptions();
            //options.addArguments("--disable-gpu");
            options.addArguments("--disable-software-rasterizer");
            options.addArguments("--disable-site-isolation-trials", "--incognito"); //se comenta "--no-sandbox" para probar error de jenkins
            options.addArguments("--start-maximized");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(options);
        }
    }
   
    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            takeScreenshot("Error");
        }
        quitWebDriver();
    }

    public void takeScreenshot(String screenshotName){
        // Tomar el screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Guardar el screenshot en la carpeta
        try {
            FileUtils.copyFile(source, new File("./Screenshots/screenshot_" + screenshotName + ".png"));
            System.out.println("Screenshot guardado exitosamente");
        } catch (IOException e) {
            System.out.println("Error al guardar el screenshot: " + e.getMessage());
        }
    }

    public void quitWebDriver(){
        if(driver != null){
            driver.quit();
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }

    private void checkEmptyFolder(){
        String pathFolder = "./Screenshots";
        File file = new File(pathFolder);
        
        if (file.exists() && file.isDirectory()){
            deleteFolder(file);
        }
    }

    private void deleteFolder(File folder){
        File[] files = folder.listFiles();
        if (files != null){
            for (File file : files){
                if (file.isDirectory()){
                    //si es una carpeta, lo elimina recursivamente
                    deleteFolder(file);
                }else{
                    //si es un archivo solo lo elimina
                    file.delete();
                }
            }
        }
    }
}

