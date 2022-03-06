import com.thoughtworks.xstream.*;
import java.io.*;
import java.net.*;
import java.time.format.*;
import java.time.*;  
/**
 *
 * @author marco
 */
public class GestoreInvioEventi {
    private final ParametriConfigurazione parametri;
    
    GestoreInvioEventi(ParametriConfigurazione parametri){
        this.parametri=parametri;
    }
    
    void invioAlServer(String nomeUtente, String nomeEvento){ //01
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dataOra=dtf.format(now);
        EventoNavigazioneGUI evento=new EventoNavigazioneGUI("RUNTASTIC", parametri.indirizzoClient, nomeEvento, dataOra);
        XStream xs=new XStream();
        String x=xs.toXML(evento);
        
        try(
            DataOutputStream flussoDati = new DataOutputStream(new Socket(parametri.indirizzoServer, parametri.portaServer).getOutputStream());
            )
        {
            flussoDati.writeUTF(x);
        }
        catch (IOException ex) {
            System.out.println("Qualcosa è andato storto...");
        }
    }
}
//01
/* La funzione si occupa di inviare al server di log, con indirizzo e porta specificati in ParametriConfigurazione.
L'invio avviene tramite un oggetto della classe EventoNavigazioneGUI in XML
*/
