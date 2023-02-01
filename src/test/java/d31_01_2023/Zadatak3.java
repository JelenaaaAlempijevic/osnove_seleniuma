package d31_01_2023;
//3. Zadatak
//Napisati program koji:
//Ucitava stranicu https://itbootcamp.rs/
//Skrola do slajdera na dnu stranice (u kome su slike Java, Php, Selenium, …)
//Cita sve linkove slika iz slajdera
//Proverava url svake slike, i za sve slike koje imaju status veci i jednak od 200 a manji od 300, skida i smesta u folder itbootcamp_slider u okviru projekta
//Azurirajte gitignore da ignorise itbootcamp_slider folder
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException, IOException {


        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://itbootcamp.rs/");

        new Actions(driver).scrollToElement(driver.findElement(By.className("slider_bkgd"))).perform();

        List<WebElement> slike = driver.findElements(By.xpath("//div[@class='carousel-item']/img"));
        for (int i = 0; i < slike.size(); i++) {
            String link = slike.get(i).getAttribute("src");
            if (getHTTPResponseStatusCode(link) >= 200 && getHTTPResponseStatusCode(link) < 300) {
                System.out.println("Status *" + slike.get(i).getAttribute("src")
                        + "* linka je: dobar - veci ili jednako 200 i manji od 300");
                try {
                    downloadUsingStream(link, "itbootcamp_slider/" + (i+1) + ".png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Status *" + slike.get(i).getAttribute("src")
                        + "* linka je: los - nije veci ili jednako 200 i manji od 300");
            }
        }
        Thread.sleep(5000);
        driver.quit();
    }

    public static int getHTTPResponseStatusCode(String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        return http.getResponseCode();
    }

    private static void downloadUsingStream(String link, String file) throws IOException {
        URL url = new URL(link);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}