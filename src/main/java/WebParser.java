import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WebParser {
    private WebDriver driver;
    private List<WebElement> TestHeader, TestBody;
    private ConcurrentHashMap<String, ConcurrentHashMap<String, String>> WebTable = null;
    public WebParser(String URL) {
        String DriverPath = ".\\src\\main\\resources\\webdriver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", DriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(URL);
    }

    private void OpenWebElement() {
        TestHeader = driver.findElements(By.className("tsc_table_s13")).get(0).findElements(By.tagName("thead"));
        TestBody = driver.findElements(By.className("tsc_table_s13")).get(0).findElements(By.tagName("tbody"));
    }

    private void ParseWebTableElement() {
        WebTable = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> temp;
        for (int n=0; n<TestBody.get(0).getText().split("\n").length; n++ ) {
            temp = new ConcurrentHashMap<>();
            for (int m=1; m<TestHeader.get(0).getText().split(" ").length-1; m++)
                temp.put(TestHeader.get(0).findElements(By.tagName("th")).get(m).getText(),
                        TestBody.get(0).findElements(By.tagName("tr")).get(n).findElements(By.tagName("td")).get(m-1).getText()
                );
            WebTable.put(TestBody.get(0).findElements(By.tagName("tr")).get(n).findElements(By.tagName("th")).get(0).getText(),
                    temp
                    );
        }
    }

    public String getWebTableElement(String Structure, String Attribute) {
        if (WebTable == null) {
            OpenWebElement();
            ParseWebTableElement();
        }
        return WebTable.get(Structure).get(Attribute);
    }

    public void closeBrowser() {
        driver.close();
    }

}
