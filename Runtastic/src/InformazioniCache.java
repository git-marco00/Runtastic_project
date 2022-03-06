
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marco
 */
public class InformazioniCache implements Serializable {
    String utente;
    int pesoUtente;
    String dataDa;
    String dataA;
    String dataUltimaRiga;
    Double distanzaUltimaRiga;
    String tempoUltimaRiga;
    int rigaSelezionata;
    
    InformazioniCache(String utente,int pesoUtente, String dataDa, String dataA, String dataUltimaRiga, Double distanzaUltimaRiga, String tempoUltimaRiga, int rigaSelezionata){
        this.utente=utente;
        this.pesoUtente=pesoUtente;
        this.dataDa=dataDa;
        this.dataA=dataA;
        this.dataUltimaRiga=dataUltimaRiga;
        this.distanzaUltimaRiga=distanzaUltimaRiga;
        this.tempoUltimaRiga=tempoUltimaRiga;
        this.rigaSelezionata=rigaSelezionata;
    }
}
