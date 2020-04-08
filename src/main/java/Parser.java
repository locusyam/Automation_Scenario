import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class Parser {
    private Document XmlDoc;
    private ConcurrentHashMap<String, Dictionary<String,String>> BuildingList = null;
    public Parser(String path) {
        DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = null;
        try {
            xmlBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputStream Raw = getClass().getClassLoader().getResourceAsStream(path);
        try {
            XmlDoc = xmlBuilder.parse(Raw);
        } catch (SAXException | IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ParseBuildingList() {
        BuildingList = new ConcurrentHashMap<>();
        Dictionary<String, String> temp = null;
        for (int n=0; n<XmlDoc.getElementsByTagName("Building").getLength(); n++) {
            temp = new Hashtable<>();
            for (int m=2; m<XmlDoc.getElementsByTagName("Building").item(n).getChildNodes().getLength()-2; m+=2 ) {
                temp.put(
                        XmlDoc.getElementsByTagName("Building").item(n).getChildNodes().item(m).getNextSibling().getNodeName(),
                        XmlDoc.getElementsByTagName("Building").item(n).getChildNodes().item(m).getNextSibling().getFirstChild().getTextContent()
                        );
            }
            BuildingList.put(
                    XmlDoc.getElementsByTagName("Building").item(n).getChildNodes().item(0).getNextSibling().getTextContent(),
                    temp
            );
        }
    }

    public String getData(String Structure, String Attribute) {
        if (BuildingList == null) {
            ParseBuildingList();
        }
        return BuildingList.get(Structure).get(Attribute);
    }


}
