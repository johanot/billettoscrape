package dk.dds.billettoscrape;

import com.thoughtworks.selenium.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * User: jot
 * Date: 6/16/12
 * Time: 6:37 PM
 */
public class BillettoScrape {

    private WebDriver driver;
    private Selenium selenium;


    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Syntaks: BillettoScrape user password event");
            System.exit(1);
        }

        BillettoScrape billettoScrape = new BillettoScrape();
        billettoScrape.initDriver();
        billettoScrape.login(args[0], args[1]);
        billettoScrape.downloadFile(args[2]);
    }


    private void initDriver() {
        driver = new ChromeDriver();
        selenium = new WebDriverBackedSelenium(driver, "");
    }


    private void login(String user, String passwd) {

        driver.navigate().to("http://www.billetto.dk/da/users/sign_in");
        selenium.waitForPageToLoad("5000");

        driver.findElement(new By.ById("user_email")).sendKeys(user);

        driver.findElement(new By.ById("user_password")).sendKeys(passwd);

        driver.findElement(new By.ById("login_user")).click();
        selenium.waitForPageToLoad("2000");
    }


    private void downloadFile(String event) {
        String eventListDownloadLink = "http://www.billetto.dk/da/events/" + event + "/list.csv?listtype=guestlist";
        driver.navigate().to(eventListDownloadLink);
    }

}