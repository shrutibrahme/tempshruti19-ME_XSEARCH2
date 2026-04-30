
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.google.com");
        driver.get("https://www.wikipedia.org/");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.toLowerCase().contains("wikipedia")) {
            System.out.println("PASS: URL contains 'wikipedia'");
        } else {
            System.out.println("FAIL: URL does not contain 'wikipedia'");
        }
        System.out.println("end Test case: testCase01");

    }

    public void testCase02() throws InterruptedException {
            System.out.println("Start Test case: testCase02");
    
            driver.get("https://www.wikipedia.org/");

            String header = driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/main/div[1]/h1")).getText();
           // String header = driver.getTitle();
            System.out.println(header);

            if (header.equals("Wikipedia")) {
                System.out.println("PASS: Header text is 'Wikipedia'");
            } else {
                System.out.println("FAIL: Header text is not 'Wikipedia'");
            }

            String footerTermsofUse = driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/footer/p/small[2]/a")).getText();
            if (footerTermsofUse.contains("Terms of Use")) {
                System.out.println("PASS: Footer text contains 'Terms of Use'");
            } else {
                System.out.println("FAIL: Footer text doesn't contain 'Terms of Use'");
            }

            String footerPrivacyPolicy = driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/footer/p/small[3]/a")).getText();
            if (footerPrivacyPolicy.contains("Privacy Policy")) {
                System.out.println("PASS: Footer text contains 'Privacy Policy'");
            } else {
                System.out.println("FAIL: Footer text doesn't contain 'Privacy Policy'");
            }

        System.out.println("end Test case: testCase02");
    }
   
    public void testCase03() {
        System.out.println("Start Test case: testCase03");
       
        driver.get("https://www.wikipedia.org/");
       // driver.findElement(By.id("searchInput")).click();
        driver.findElement(By.id("searchInput")).sendKeys("apple");
        driver.findElement(By.xpath("//*[@id='typeahead-suggestions']/div/a[2]/div[1]/h3/em")).click();

        String currentUrl2 = driver.getCurrentUrl();
        //System.out.println(currentUrl2.toLowerCase());
        if (currentUrl2.toLowerCase().contains("apple_inc.")) {
            System.out.println("PASS: navigated to Apple.Inc");
        } else {
            System.out.println("FAIL: Didn't navigate to apple inc");
        }

        List<WebElement> founderList = driver.findElements(By.xpath("//*[@id='mw-content-text']/div[2]/table[1]/tbody/tr[9]/td/div/ul/li/a"));
        for (WebElement element : founderList) {
            String founderName = element.getText();
            if (founderName.equals("Steve Jobs")){
                System.out.println("PASS: Steve jobs present in the founders list");
                break;
            }else{
                System.out.println("Fail: Steve jobs absent in the founders list");
            }
        }



        System.out.println("end Test case: testCase03");
    }

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
       
        driver.get("https://www.wikipedia.org/");
       // driver.findElement(By.id("searchInput")).click();
        driver.findElement(By.id("searchInput")).sendKeys("microsoft");
        driver.findElement(By.xpath("//*[@id='typeahead-suggestions']/div/a[1]/div[1]/h3/em")).click();

        String currentUrl3 = driver.getCurrentUrl();
        //System.out.println(currentUrl2.toLowerCase());
        if (currentUrl3.toLowerCase().contains("microsoft")) {
            System.out.println("PASS: navigated to Microsoft page");
        } else {
            System.out.println("FAIL: Didn't navigate to microsoft page");
        }
        List<WebElement> microsoftFoundersList = driver.findElements(By.xpath("//*[@id='mw-content-text']/div[2]/table[1]/tbody/tr[9]/td/div/ul/li/a"));
        boolean foundGates = false;
        for (WebElement element : microsoftFoundersList) {
            String microFounderName = element.getText();
            if(microFounderName.equals("Bill Gates")){
                System.out.println("PASS: Founders list contains the name of Bill Gates");
                System.out.println(microFounderName);
                element.click();
                foundGates = true;
                break;
            }
        }
        if(!foundGates){
            System.out.println("FAIL: Founders list doesn't contain the name of Bill Gates");
        }

       Thread.sleep(2000);

        String currentUrl5 = driver.getCurrentUrl();
       // System.out.println(currentUrl2.toLowerCase());
        if (currentUrl5.toLowerCase().contains("bill_gates")) {
            System.out.println("PASS: navigated to Gates page");
        } else {
            System.out.println("FAIL: Didn't navigate to Gates page");
        }

        System.out.println("end Test case: testCase04");
    }

    public void testCase05() {
        System.out.println("Start Test case: testCase05");
        //driver.get("https://www.google.com");
        driver.get("https://en.wikipedia.org/");
        driver.findElement(By.id("vector-main-menu-dropdown-checkbox")).click();
        driver.findElement(By.id("n-aboutsite")).click();

        String currentUrl4 = driver.getCurrentUrl();
        //System.out.println(currentUrl2.toLowerCase());
        if (currentUrl4.toLowerCase().contains("about")) {
            System.out.println("PASS: navigated to About page");
        } else {
            System.out.println("FAIL: Didn't navigate to About page");
        }


        
        System.out.println("end Test case: testCase05");
    }
   

}

