
/**
 *
 * @author marco
 */
public class EventoNavigazioneGUI {
    String applicazione;
    String ipClient;
    String etichettaEvento;
    String giornoOraCorrente;
    
    EventoNavigazioneGUI(String applicazione, String ipClient, String etichettaEvento, String giornoOraCorrente){
        this.applicazione=applicazione;
        this.ipClient=ipClient;
        this.etichettaEvento=etichettaEvento;
        this.giornoOraCorrente=giornoOraCorrente;
    }
    public String toString(){
        return applicazione + " " + ipClient + " " + etichettaEvento+ " "+ giornoOraCorrente;
    }
    
}
