
import java.io.*;
import javafx.collections.*;
import javafx.scene.control.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marco
 */
public class GestoreCache {
    private final String nomeSalvataggio;
    
    GestoreCache(String salvataggio){
        nomeSalvataggio=salvataggio;
    }
    
    void salvaInLocale(Runtastic GUI){ //01
        InformazioniCache info;
        String utente;
        int pesoUtente;
        String dataDa;
        String dataA;
        Corsa ultimaRiga;
        String dataUltimaRiga;
        Double distanzaUltimaRiga;
        String tempoUltimaRiga;
        int rigaSelezionata=-1;
        
        utente=GUI.nomeUtente;
        pesoUtente=GUI.pesoUtente;
        dataDa=GUI.elementiInterattivi.dataDa.getText();
        dataA=GUI.elementiInterattivi.dataA.getText();
        ultimaRiga=GUI.tabellaCorse.tabella.getItems().get(GUI.tabellaCorse.tabella.getItems().size()-1);
        dataUltimaRiga=ultimaRiga.getData();
        distanzaUltimaRiga=ultimaRiga.getDistanza();
        tempoUltimaRiga=ultimaRiga.getTempo();
        ObservableList selectedCells=GUI.tabellaCorse.tabella.getSelectionModel().getSelectedCells();
        if(!selectedCells.isEmpty()){
            TablePosition cell=GUI.tabellaCorse.tabella.getSelectionModel().getSelectedCells().get(0);
            rigaSelezionata=cell.getRow();
        }
        info=new InformazioniCache(utente, pesoUtente, dataDa, dataA, dataUltimaRiga, distanzaUltimaRiga, tempoUltimaRiga, rigaSelezionata);
        salvaCache(info);
    }
    
    private void salvaCache(InformazioniCache info){  //02
        try(FileOutputStream fout= new FileOutputStream(nomeSalvataggio);
            ObjectOutputStream oout= new ObjectOutputStream(fout);){
            oout.writeObject(info);
        } catch(IOException ex){
            System.out.println("Errore: impossibile caricare la cache");
        }
    }
    
    private InformazioniCache caricaCache(){  //03
        InformazioniCache info = null;
        try (FileInputStream fin= new FileInputStream(nomeSalvataggio);
            ObjectInputStream oin=new ObjectInputStream(fin); ) {
                info=(InformazioniCache)oin.readObject();
        } catch(IOException | ClassNotFoundException ex){
            System.out.println("Errore: impossibile leggere la cache");
        }
        return info;
    }
    
    void caricaInLocale(Runtastic GUI){  //04
        InformazioniCache info=caricaCache();
        if(info!=null){
            GUI.nomeUtente=info.utente;
            GUI.pesoUtente=info.pesoUtente;
            GUI.elementiInterattivi.sceltaUtente.setValue(info.utente);
            GUI.elementiInterattivi.dataA.setText(info.dataA);
            GUI.elementiInterattivi.dataDa.setText(info.dataDa);
            GUI.elementiInterattivi.refresh(GUI);
            Corsa corsaModificata=new Corsa(0, "null", info.dataUltimaRiga, info.distanzaUltimaRiga, info.tempoUltimaRiga, 0, 0);
            GUI.tabellaCorse.lista.set(GUI.tabellaCorse.lista.size()-1, corsaModificata);
            if(info.rigaSelezionata!=-1){
                GUI.tabellaCorse.tabella.getSelectionModel().select(info.rigaSelezionata);
            }
        }
    }
}

//01
/*La funzione preleva dalla classe principale Runtastic tutti i dati che dovranno 
essere poi salvati in cache. Chiama poi la salvaCache per salvarli su file.
*/

//02
/* La funzione prende in ingresso le InformazioniCache salvate precedentemente e si occupa di salvarle su file
*/

//03
/* La funzione si occupa di leggere la cache in binario da file e ritorna tali informazioni come oggetto di classe
InformazioniCache
*/

//04
/* Chiama la caricaCache che restituisce un oggetto della classe InformazioniCache e si occupa di ripristinare tali
valori nell'applicativo
*/
