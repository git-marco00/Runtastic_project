import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.*;
import javafx.scene.control.cell.*;
import javafx.util.converter.*;

public class TabellaCorse {
    TableView<Corsa> tabella;
    RichiestaArchivio richiesta;
    ObservableList<Corsa> lista;
    
    public TabellaCorse(ParametriDatabase parametriDB, Runtastic GUI){
        richiesta=new RichiestaArchivio(parametriDB);
        tabella=new TableView<>();
        inizializzaTabella(GUI);
    }
    
    private void inizializzaTabella(Runtastic GUI){
        TableColumn colonnaData= new TableColumn("DATA");
        colonnaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        
        TableColumn colonnaDistanza= new TableColumn("DISTANZA(km)");
        colonnaDistanza.setCellValueFactory(new PropertyValueFactory<>("distanza"));
        
        TableColumn colonnaTempo= new TableColumn("TEMPO(hh:mm:ss)");
        colonnaTempo.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        
        TableColumn colonnaAndatura= new TableColumn("ANDATURA(min/km)");
        colonnaAndatura.setCellValueFactory(new PropertyValueFactory<>("andatura"));
        
        TableColumn colonnaCalorie= new TableColumn("CALORIE");
        colonnaCalorie.setCellValueFactory(new PropertyValueFactory<>("calorie"));
        
        impostaTabellaEditabile(colonnaData, colonnaDistanza, colonnaTempo);
        
        tabella.getColumns().addAll(colonnaData, colonnaDistanza, colonnaTempo, colonnaAndatura, colonnaCalorie);
        tabella.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection!=null){
                int rowIndex=tabella.getSelectionModel().getSelectedIndex();
                String eventoRigaSelezionata="RIGA SELEZIONATA: "+rowIndex;
                GUI.gestoreInvioEventi.invioAlServer(GUI.nomeUtente, eventoRigaSelezionata);
            }
    });
        tabella.setEditable(true);
    }
    
    private void impostaTabellaEditabile(TableColumn colonnaData, TableColumn colonnaDistanza, TableColumn colonnaTempo){
        colonnaData.setCellFactory(TextFieldTableCell.forTableColumn());
        colonnaData.setEditable(true);
        colonnaData.setOnEditCommit(new EventHandler<CellEditEvent<Corsa, String>>(){
            public void handle(CellEditEvent ev){
                Corsa modifica=(Corsa)ev.getRowValue();
                modifica.setData((String)ev.getNewValue());
            }
        });
        colonnaDistanza.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        colonnaDistanza.setEditable(true);
        colonnaDistanza.setOnEditCommit(new EventHandler<CellEditEvent<Corsa, Double>>(){
            public void handle(CellEditEvent ev){
                Corsa modifica=(Corsa)ev.getRowValue();
                modifica.setDistanza((Double)ev.getNewValue());
            }
        });
        colonnaTempo.setCellFactory(TextFieldTableCell.forTableColumn());
        colonnaTempo.setEditable(true);    
        colonnaTempo.setOnEditCommit(new EventHandler<CellEditEvent<Corsa, String>>(){
            public void handle(CellEditEvent ev){
                Corsa modifica=(Corsa)ev.getRowValue();
                modifica.setTempo((String)ev.getNewValue());
            }
        });
    }
    
    public void popolaTabella(String utente, String dataDa, String dataA){
        if(utente.equals("null")==false){
            lista=FXCollections.observableArrayList(richiesta.restituisciCorse(utente, dataDa, dataA));
            tabella.setItems(lista);
            // qua dovrei aggiungere l'ultima riga editabile
            Corsa rigaEditabile=new Corsa(0,"null","(inserisci)", 0, "(inserisci)",0,0);
            tabella.getItems().add(rigaEditabile);
        }
    }
    
    public void aggiungiCorsa(String nomeUtente, int pesoUtente){
        Corsa corsaDaAggiungere=tabella.getItems().get(tabella.getItems().size()-1);
        corsaDaAggiungere.setNomeUtente(nomeUtente);
        int hours=Integer.parseInt(corsaDaAggiungere.getTempo().substring(0,2));
        int minutes=Integer.parseInt(corsaDaAggiungere.getTempo().substring(3,5));
        int min=hours*60+minutes;
        corsaDaAggiungere.setAndatura(Math.floor(min/corsaDaAggiungere.getDistanza()*100)/100);
        corsaDaAggiungere.setCalorie((int)(pesoUtente*(int)corsaDaAggiungere.getDistanza()*0.9));
        richiesta.inserisciCorsa(nomeUtente, corsaDaAggiungere);
    }
    
    public void eliminaCorsa(){
        Corsa CorsaDaEliminare=tabella.getSelectionModel().getSelectedItem();
        richiesta.eliminaCorsa(CorsaDaEliminare);
    }
}

/*
La classe si occupa di creare la nuova tabella instanziando le colonne. Con impostaTabellaEditabile si occupa di 
settare come editabili le prime 3 colonne, ovvero quelle relative a data, distanza e tempo.
PopolaTabella si occupa di effettuare le chiamate alla classe richiestaArchivio che restituisce la lista di corse, e successivamente
le aggiunge alla tabella stessa. 
La aggiungiCorsa e la eliminaCorsa effettuano le dovute chiamate al database per aggiungere o eliminare la corsa corrispondente.
*/
