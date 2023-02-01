package p31_01_2023;
//Napisati program koji
//Kreirati folder downloads folder u projektu
//Ucitava stranu https://cms.demo.katalon.com/product/flying-ninja/
//Cita href atribut sa glavne slike sa stranice
//Koristi link iz href atributa za skidanje slike
//Sliku sacuvajte u folderu downloads pod nazivom flying-ninja.jpg
//Koristan link za skidanje fajlova u javi
//Azurirajte gitignore da ignorise downloads folder

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class Zadatak3 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();
        driver.get("https://cms.demo.katalon.com/product/flying-ninja/");

        String url= driver.findElement(By.className("//*[@class='flex-viewport']//a")).getAttribute("href");
        System.out.println(url);

        String path = new File("downloads/flying-ninja.jpg").getAbsolutePath();


        try {
            downloadUsingNIO(url, path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}


