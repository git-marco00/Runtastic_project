import com.thoughtworks.xstream.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import javax.xml.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.*;
import javax.xml.validation.*;
import org.xml.sax.*;


/**
 *
 * @author marco
 */
public class ServerLogRuntastic{
    
    public static void main(String[] args) {
        try( ServerSocket servs = new ServerSocket(4242, 7) ){
            while(true){
                XStream flussoXML= new XStream();
                try (
                     Socket s= servs.accept();
                     DataInputStream din=new DataInputStream(s.getInputStream());
                    ){
                    String formatoXML= din.readUTF();
                    Files.write(Paths.get("./myfiles/evento.xml"), formatoXML.getBytes());
                    // validazione del formato XML
                    if(validaOggetto()){
                        stampaSuFile(formatoXML);
                    }
                    EventoNavigazioneGUI evento= (EventoNavigazioneGUI) flussoXML.fromXML(formatoXML);
                    System.out.println(evento);
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    private static boolean validaOggetto(){
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema s = sf.newSchema(new StreamSource(new File("./myfiles/validatoreEvento.xsd")));
            Source xmlFile=new StreamSource(new File("./myfiles/evento.xml"));
            s.newValidator().validate(xmlFile);
        } catch (Exception e) {
            if (e instanceof SAXException){
                System.out.println("Errore di validazione: " + e.getMessage());
                return false;
            }
            else
                System.out.println(e.getMessage());
        }
        return true;
    }
    
    private static void stampaSuFile(String XML){
        try{
            Files.write(Paths.get("./myfiles/log.xml"), XML.getBytes(), StandardOpenOption.APPEND);
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
