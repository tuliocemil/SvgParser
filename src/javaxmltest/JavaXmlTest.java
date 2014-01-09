package javaxmltest;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

public class JavaXmlTest {

    public static void main(String[] args) throws Exception {
        String[] tags = {"teste1", "teste2", "teste3"};
        
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(new File("xml/bomba1.svg"));
        
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        
        int i = 0;
        
        for(String tag : tags) {
            NodeList nodeList = (NodeList) xpath.evaluate("//*[@id='"+ tag +"']", document, XPathConstants.NODESET);
        
            System.out.println("Antes: " + nodeList.item(0).getTextContent()); 

            nodeList.item(0).setTextContent("novo valor " + i);

            String value = xpath.evaluate("//*[@id='"+ tag +"']", document, XPathConstants.STRING).toString();

            System.out.println("Depois: " + value + "\n\n");
            i++;
        }
        
        // Imprime o XML
        OutputFormat format = new OutputFormat(document);
        Writer out = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(out, format);
        serializer.serialize(document);
        
        System.out.println( "\n\n\n" + out.toString() );
    }

}