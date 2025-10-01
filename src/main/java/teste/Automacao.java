package teste;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Automacao {

	@Test
	public void emitirAtestadoMatricula() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\java\\driver\\chromedriver.exe");
		WebDriver navegador = new ChromeDriver();
		navegador.manage().window().maximize();

		navegador.get("https://sigaa.ufrn.br/");

		navegador.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div/ul/li[3]/a")).click();
		
		
		//credenciais do sigaa
		navegador.findElement(By.xpath("/html/body/div/main/div/form/div[3]/input")).sendKeys("");
		navegador.findElement(By.xpath("/html/body/div/main/div/form/div[4]/input")).sendKeys("",
				Keys.ENTER);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		navegador.findElement(By.xpath("/html/body/dialog/button")).click();

		WebElement menu = navegador.findElement(
				By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/div/form/div/table/tbody/tr/td[1]/span[2]"));

		WebElement opcao = navegador.findElement(
				By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/div/form/div/div[1]/table/tbody/tr[3]/td[2]"));

		Actions actions = new Actions(navegador);
		actions.moveToElement(menu).perform();
		actions.moveToElement(opcao).click().perform();

		JavascriptExecutor js = (JavascriptExecutor) navegador;
		js.executeScript("window.scroll(0,1000)");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		navegador.findElement(By.xpath("/html/body/div/div[4]/p/table/tbody/tr/td[1]/a")).click();

		navegador.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div/span[4]/a")).click();

	}

}
