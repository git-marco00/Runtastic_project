
import javafx.collections.*;
import javafx.event.ActionEvent;
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
public class InterazioniUtente {
    
    Button bottoneRefresh;
    Button bottoneAggiungi;
    Button bottoneElimina;
    ComboBox sceltaUtente;
    TextField dataDa;
    TextField dataA;
    
    InterazioniUtente(Runtastic GUI){  //01
        bottoneRefresh=new Button("Refresh");
        bottoneAggiungi=new Button("Aggiungi");
        bottoneElimina=new Button("Elimina");
        dataDa=new TextField();
        dataA=new TextField();
        sceltaUtente=new ComboBox(FXCollections.observableArrayList(GUI.tabellaCorse.richiesta.restituisciUtenti()));
        
        bottoneRefresh.setOnAction((ActionEvent ev)->{
            refresh(GUI);
            GUI.gestoreInvioEventi.invioAlServer(GUI.nomeUtente, "REFRESH");
        });
        
        bottoneAggiungi.setOnAction((ActionEvent ev)->{
            aggiungi(GUI);
            GUI.gestoreInvioEventi.invioAlServer(GUI.nomeUtente, "AGGIUNGI");
        });
        
        bottoneElimina.setOnAction((ActionEvent ev)->{
            elimina(GUI);
            GUI.gestoreInvioEventi.invioAlServer(GUI.nomeUtente, "ELIMINA");
        });
    }
    
    public void refresh(Runtastic GUI){
        String utente=(String)sceltaUtente.getValue();
        GUI.nomeUtente=utente;
        GUI.pesoUtente=GUI.tabellaCorse.richiesta.restituisciPeso(utente);
        GUI.tabellaCorse.popolaTabella(utente, dataDa.getText(), dataA.getText());
        // a questo punto devo aggiornare il grafico
        GUI.graficoCorse.aggiornaGrafico(GUI.tabellaCorse.lista);
    }
    
    private void aggiungi(Runtastic GUI){
        GUI.tabellaCorse.aggiungiCorsa(GUI.nomeUtente, GUI.pesoUtente);
        refresh(GUI);
    }
    
    private void elimina(Runtastic GUI){
        // devo capire quale sia la riga selezionata
        GUI.tabellaCorse.eliminaCorsa();
        refresh(GUI);
    }
}

//01
/* Si occupa di instanziare i bottini, textfield e comboBox. La comboBox dovrà presentare i nomi degli utenti presenti
nel database, quindi verrà inizializzata con la lista di stringhe restituite dalla RichiestaArchivio.
Inoltre si occupa di collegare gli eventi corrispondenti ai bottoni
*/
