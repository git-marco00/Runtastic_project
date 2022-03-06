
import javax.xml.*;
import java.io.*;
import org.xml.sax.*;
import javax.xml.validation.*;
import javax.xml.transform.stream.*;
import com.thoughtworks.xstream.*; 
import java.nio.file.*;
import javax.xml.transform.Source;

/**
 *
 * @author marco
 */
public class CaricatoreConfigurazione { 
    ParametriConfigurazione parametri;
    
    CaricatoreConfigurazione(){
        XStream xstream= new XStream();
        try
        {
            validaConfigurazione();
            String x = new String(Files.readAllBytes(Paths.get("./myfiles/config.xml")));
            parametri = (ParametriConfigurazione) xstream.fromXML(x);        
        }
        catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    private static void validaConfigurazione(){
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema s = sf.newSchema(new StreamSource(new File("./myfiles/validatoreConfig.xsd")));
            Source xmlFile=new StreamSource(new File("./myfiles/config.xml"));
            s.newValidator().validate(xmlFile);
        } catch (Exception e) {
            if (e instanceof SAXException)
                e.printStackTrace();
            else
                System.out.println(e.getMessage());
        }
    }

}
