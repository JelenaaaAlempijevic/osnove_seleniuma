package d26_01_2023;
//1. Zadatak
//Niz todo-a (niz stringova) koje treba da uneti. Niz je:
//Visit Paris
//Visit Prague
//Visit London
//Visit New York
//Visit Belgrade
//Maksimizirati prozor
//Ucitati stranicu https://example.cypress.io/todo
//Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
//Nakon svakog unosa todo-a, unosi se enter
//Validira da li je novi todo dodat na stranici
//Na kraju programa proci petljom i izbrisati svaki todo sa stranice (klikom na x dugme svakog todo-a)
//Validirati da je na kraju programa broj todo-a na stranici 0.
//Cekanje od 5s
//Zatvorite pretrazivac

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;

import java.util.ArrayList;
import java.util.List;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        ArrayList<String> nizToDo = new ArrayList<String>();

        nizToDo.add("Visit Paris");
        nizToDo.add("Visit Prague");
        nizToDo.add("Visit London");
        nizToDo.add("Visit NewYork");
        nizToDo.add("Visit Belgrade");

        driver.manage().window().maximize();
        driver.get("https://example.cypress.io/todo");

        WebElement input = driver.findElement(By.className("new-todo"));
        for (int i = 0; i < nizToDo.size(); i++) {
            input.sendKeys(nizToDo.get(i));
            input.sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            if (driver.findElement(By.xpath("//ul[@class='todo-list']/li[last()]")).
                    getText().equals(nizToDo.get(i))) {
                System.out.println("Dodat je nov to do.");
            }
        }

        List<WebElement> todosTotal = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
        for (int i = 0; i < todosTotal.size(); i++) {
            driver.findElement(By.className("destroy")).click();
            if(todosTotal.size()==0){
                System.out.println("Broj to do-a je 0");
            }
        }

        Thread.sleep(5000);
        driver.quit();
    }
}

