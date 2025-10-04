package teste;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Relatorio {
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeClass
    public static void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Analise.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Test
    public void testSigaa() {
    	System.setProperty("webdriver.chrome.driver", "src\\main\\java\\driver\\chromedriver.exe");
    	WebDriver navegador = new ChromeDriver();
    	navegador.manage().window().maximize();
    	
        test = extent.createTest("Teste SIGAA");
        test.info("INICIANDO TESTE");
		navegador.get("https://sigaa.ufrn.br/");
		
		try {
			navegador.findElement(By.className("login")).click();
			test.pass("Clicou no botão de Login");
		} catch (Exception e) {
			test.fail("Erro ao tentar clicar no botão de Login: " + e.getMessage());
			Assert.fail("Não foi possível clicar no botão");
		}
		
		try {
			navegador.findElement(By.id("username")).sendKeys("");
			navegador.findElement(By.id("password")).sendKeys("",
					Keys.ENTER);
			test.pass("Preencheu os dados de login");
		} catch (Exception e) {
			registrarPrint("Erro ao preencher os dados de login", navegador);
			test.fail("Erro ao preencher os dados de login: " + e.getMessage());
			Assert.fail("Não foi possível preencher os dados do login");
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			navegador.findElement(By.xpath("/html/body/dialog/butto")).click();
			test.pass("Fez login");
		} catch (Exception e) {
			registrarPrint("Erro ao fazer Login", navegador);
			test.fail("Erro ao fazer login: " + e.getMessage());
			Assert.fail("Não foi possível fazer Login");
		}
		
        test.pass("Teste passou com sucesso!");
    }
    
    public void registrarPrint(String mensagem, WebDriver navegador) {
    	File src = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
	    String caminho = "screenshot_" + System.currentTimeMillis() + ".png";
	    try {
			Files.copy(src.toPath(), new File(caminho).toPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	    test.addScreenCaptureFromPath(caminho, mensagem);
    }

    @AfterClass
    public static void tearDown() {
        extent.flush(); // gera o arquivo Analise.html
    }
}
