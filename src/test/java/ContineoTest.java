import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContineoTest {
    public Parser TestDoc;
    public WebParser TestWeb;

    @BeforeClass
    public void setup() {
        TestWeb = new WebParser("https://demoqa.com/automation-practice-table/");
        TestDoc = new Parser("buildings.xml");
    }

    @Test
    public void RunTest() {
        Assert.assertEquals(TestWeb.getWebTableElement("Burj Khalifa", "Country"), TestDoc.getData("Burj Khalifa","Country"));
        Assert.assertEquals(TestWeb.getWebTableElement("Financial Center", "Rank"), TestDoc.getData("Financial Center","Rank"));
        Assert.assertEquals(TestWeb.getWebTableElement("Taipei 101", "Built"), TestDoc.getData("Taipei 101","Built"));
        Assert.assertEquals(TestWeb.getWebTableElement("Clock Tower Hotel", "Height"), TestDoc.getData("Clock Tower Hotel","Height"));
    }

    @Test public void RunFailTest() {
        Assert.assertEquals(TestWeb.getWebTableElement("Burj Khalifa", "Country"), TestDoc.getData("Taipei 101","Country"));
    }

    @Test public void RunIncorrectKeyTest() {
        Assert.assertEquals(TestWeb.getWebTableElement("Burj Khalifa", "Countries"), TestDoc.getData("Burj Khalifa","Country"));
    }
    
    @AfterTest
    public void tearDown() {
        TestWeb.closeBrowser();
    }

}
