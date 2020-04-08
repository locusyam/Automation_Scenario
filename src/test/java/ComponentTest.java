import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ComponentTest {
    public Parser TestDoc;
    public WebParser TestWeb;
    @BeforeClass
    public void setup() {
        TestWeb = new WebParser("https://demoqa.com/automation-practice-table/");
        TestDoc = new Parser("buildings.xml");
    }

    @Test
    public void ReadXmlDocTest() {
        Assert.assertEquals(TestDoc.getData("Burj Khalifa", "Country"), "UAE");
        Assert.assertEquals(TestDoc.getData("Taipei 101", "City"), "Taipei");
    }

    @Test
    public void ReadWebTableTest() {
        Assert.assertEquals(TestWeb.getWebTableElement("Burj Khalifa", "Country"), "UAE");
        Assert.assertEquals(TestWeb.getWebTableElement("Clock Tower Hotel", "Rank"), "2");
        Assert.assertEquals(TestWeb.getWebTableElement("Taipei 101", "Height"), "509m");
        Assert.assertEquals(TestWeb.getWebTableElement("Financial Center", "Built"), "2008");
    }

    @AfterTest
    public void tearDown() {
        TestWeb.closeBrowser();
    }
}
