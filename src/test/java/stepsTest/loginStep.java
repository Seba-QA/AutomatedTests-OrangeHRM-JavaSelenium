package stepsTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.login;

public class loginStep{
    private WebDriver driver = Hooks.getDriver();
    private login lg = new login(driver);

    @Given("Ingreso a la url")
    public void ingresoALaUrl(){
        //ingresa a la url de la pagina
        lg.navegarUrl();
    }

    @Given("Ingreso {string} y {string}")
    public void ingreso(String user, String pass){
        //ingresa las credenciales entregadas en el feature
        lg.loginVal(user, pass);
    }

    @When("Se presiona boton login")
    public void sePresionaBotonLogin() {
        lg.log();
    }

    @Then("Se ve el dashboard")
    public void seVeElDashboard() {
        lg.valDashboard();
    }

    //Validacion casos donde no se debe poder iniciar sesion
    @Then("Se validan mensajes de error {string}")
    public void seValidanMensajesDeError(String caso) {
        lg.validaCaso(caso);
    }

}
