package cap15;

import automation.practice.MainPage;
import automation.practice.MenuBar;
import automation.practice.MyAccountPage;
import automation.practice.SignInPage;
import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import util.Excel;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static util.reporter.MensajesReporte.cargarMensajeResultadoTestNOk;
import static util.reporter.MensajesReporte.cargarMensajeResultadoTestOk;

public class ProyectoFinalAPFS {

    HashMap<String, String> preferencias = new HashMap<String, String>();
    String url_AUT="";
    String excelPath = System.getProperty("user.dir")+"\\report\\excelOutput\\";
    @Factory(dataProvider = "dpBrowsers")
    public ProyectoFinalAPFS(String browserName) {
        preferencias.put("browserName", browserName);
    }
    public ProyectoFinalAPFS(){
    }

    @DataProvider
    public Object[][] dpBrowsers() throws Exception {
        return new Object[][]{{"chrome"}};
    }
    @BeforeMethod
    public void setUp(Method method) throws Exception {
        Driver.getInstance().setDriver(preferencias.get("browserName"), preferencias);
        url_AUT = "https://www.saucedemo.com/index.html";
        Driver.getInstance().getDriver().get(url_AUT);
        Driver.getInstance().getDriver().manage().deleteAllCookies();
    }

    // Ejercio 1 :CP001. Login no exitoso
    @Test(dataProvider = "dp_Ejercio1")
    public void ejercicio_loginNoExitoso(HashMap<Object,Object> inputData)  throws IOException {
        ITestResult testResult = Reporter.getCurrentTestResult();
        WebDriver driver = Driver.getInstance().getDriver();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.ingresarCredenciales(inputData);
        String mensajeErrorActual   = signInPage.devolverMensajeError();
        String mensajeErrorEsperado = inputData.get("mensajeError").toString();
        List<String> mensajeReporte = new ArrayList<>();

        // Verificamos y generamos reporte en excel
        if(mensajeErrorActual.compareTo(mensajeErrorEsperado)==0){
            String mensajeError = "Mensaje se muestra Error: "+mensajeErrorActual;
            mensajeReporte.add(mensajeError);
            cargarMensajeResultadoTestOk(testResult,mensajeError);
            Excel.crearExcel(excelPath+testResult.getMethod().getMethodName()+".xlsx",mensajeReporte);

        }else{
            String mensajeNOk = "Mensaje esperado : "+mensajeErrorEsperado+"<br>" +
                    "Mensaje actual  : "+mensajeErrorActual;
            mensajeReporte.add(mensajeNOk);
            cargarMensajeResultadoTestNOk(testResult, mensajeNOk );
            Excel.crearExcel(excelPath+testResult.getMethod().getMethodName()+".xlsx",mensajeReporte);

        }
    }

    // Ejercio 2 :CP002. Login exitoso
    @Test (dataProvider = "dp_Ejercio2")
    public void ejercicio_loginExitoso(HashMap<Object,Object> inputData) throws IOException {
        ITestResult testResult = Reporter.getCurrentTestResult();
        WebDriver driver = Driver.getInstance().getDriver();
        SignInPage signInPage = new SignInPage(driver);
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        signInPage.ingresarCredenciales(inputData);
        boolean cargo = myAccountPage.verifificarSiLogueoExitoso();
        List<String> mensajeReporte = new ArrayList<>();

        // Verificamos y generamos reporte en excel
        if(cargo){
            String mensajeCargaCorrecta = "Cargó correctamente div Header Products.";
            mensajeReporte.add(mensajeCargaCorrecta);
            cargarMensajeResultadoTestOk(testResult,mensajeCargaCorrecta);
            Excel.crearExcel(excelPath+testResult.getMethod().getMethodName()+".xlsx",mensajeReporte);

        }else{
            String mensajeNOcargoCorrecto = "No cargó correctamente div Header Products.";
            cargarMensajeResultadoTestNOk(testResult,mensajeNOcargoCorrecto);
            Excel.crearExcel(excelPath+testResult.getMethod().getMethodName()+".xlsx",mensajeReporte);
        }
    }

    //Ejercicio 3: CP003. Deslogeo exitoso
    @Test (dataProvider = "dp_Ejercio3")
    public void ejercicio_deslogueoExitoso(HashMap<Object,Object> inputData) throws IOException {
        ITestResult testResult = Reporter.getCurrentTestResult();
        WebDriver driver = Driver.getInstance().getDriver();
        MainPage mainPage     = new MainPage(driver);
        SignInPage signInPage = new SignInPage(driver);
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        MenuBar menu = new MenuBar(driver);
        signInPage.ingresarCredenciales(inputData);
        menu.abrirMenu();
        menu.seleccionarLogout();
        boolean cargo = myAccountPage.verifificarSiDeslogueoExitoso();
        List<String> mensajeReporte = new ArrayList<>();

        // Verificamos y generamos reporte en excel
        if(cargo){
            String mensajeCargoOk = "Cargó correctamente div Logo.";
            mensajeReporte.add(mensajeCargoOk);
            cargarMensajeResultadoTestOk(testResult,mensajeCargoOk);
            Excel.crearExcel(excelPath+testResult.getMethod().getMethodName()+".xlsx",mensajeReporte);

        }else{
            String mensajeNOCargo = "No cargó correctamente div Logo.";
            cargarMensajeResultadoTestNOk(testResult,mensajeNOCargo);
            Excel.crearExcel(excelPath+testResult.getMethod().getMethodName()+".xlsx",mensajeReporte);
        }

    }

    // Los DataProvider de los ejercicios 1,2,3 ...

    @DataProvider(parallel = true)
    public Object[][] dp_Ejercio1(Method testMethod) throws Exception {
        Object[][] devolver = new Object[1][1];
        String excelPath = System.getProperty("user.dir")+"\\src\\main\\resources\\excel\\datos_1.xlsx";
        HashMap<Object,Object> mapa = Excel.devolverMapaExcel(excelPath);
        devolver[0][0] = mapa;
        return devolver;
    }

    @DataProvider(parallel = true)
    public Object[][] dp_Ejercio2(Method testMethod) throws Exception {
        Object[][] devolver = new Object[1][1];
        String excelPath = System.getProperty("user.dir")+"\\src\\main\\resources\\excel\\datos_2.xlsx";
        HashMap<Object,Object> mapa = Excel.devolverMapaExcel(excelPath);
        devolver[0][0] = mapa;
        return devolver;
    }

    @DataProvider(parallel = true)
    public Object[][] dp_Ejercio3(Method testMethod) throws Exception {
        Object[][] devolver = new Object[1][1];
        String excelPath = System.getProperty("user.dir")+"\\src\\main\\resources\\excel\\datos_3.xlsx";
        HashMap<Object,Object> mapa = Excel.devolverMapaExcel(excelPath);
        devolver[0][0] = mapa;
        return devolver;
    }
}
