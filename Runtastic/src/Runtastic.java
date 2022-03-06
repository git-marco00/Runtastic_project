import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Runtastic extends Application{
    private VBox vbox;
    public String nomeUtente;
    public int pesoUtente;
    private Label runtasticLabel;
    private Label dataDa, dataA;
    public TabellaCorse tabellaCorse;
    public InterazioniUtente elementiInterattivi;
    public GraficoLinea graficoCorse;
    public ParametriConfigurazione parametri;
    public CaricatoreConfigurazione caricatoreParametri;
    public GestoreCache gestoreCache;
    public GestoreInvioEventi gestoreInvioEventi;
    
    public void start(Stage stage){
        inizializza();
        
        vbox = new VBox(10);
        HBox dateBox=new HBox(elementiInterattivi.sceltaUtente, dataDa, elementiInterattivi.dataDa, dataA, elementiInterattivi.dataA, elementiInterattivi.bottoneRefresh);
        HBox buttonBox=new HBox(elementiInterattivi.bottoneAggiungi, elementiInterattivi.bottoneElimina);
        HBox tableBox=new HBox(tabellaCorse.tabella);
        modificaGrafica(dateBox, buttonBox, tableBox);
        vbox.getChildren().addAll(runtasticLabel);
        vbox.getChildren().addAll(dateBox);
        vbox.getChildren().addAll(tableBox);
        vbox.getChildren().addAll(buttonBox);
        vbox.getChildren().addAll(graficoCorse.grafico);
        gestoreInvioEventi.invioAlServer(nomeUtente, "AVVIO");
        Scene scene = new Scene (vbox, 500,600);
        stage.setOnCloseRequest((WindowEvent eventoFinestra)-> {
            gestoreCache.salvaInLocale(this);
            gestoreInvioEventi.invioAlServer(nomeUtente, "TERMINE");
        });
        stage.setTitle("RUNTASTIC");
        stage.setScene(scene);
        stage.show();
    }
    
    private void inizializza(){  //01
        runtasticLabel=new Label("RUNTASTIC");
        dataDa= new Label("Da: ");
        dataA=new Label("A: ");
        caricatoreParametri=new CaricatoreConfigurazione();
        parametri=caricatoreParametri.parametri;
        gestoreInvioEventi=new GestoreInvioEventi(parametri);
        tabellaCorse=new TabellaCorse(parametri.parametriDB, this);
        elementiInterattivi=new InterazioniUtente(this);
        graficoCorse=new GraficoLinea();
        gestoreCache=new GestoreCache(parametri.nomeSalvataggioCache); 
        gestoreCache.caricaInLocale(this);
    }
    
    private void modificaGrafica(HBox dateBox, HBox buttonBox, HBox tableBox){  //02
        runtasticLabel.setStyle("-fx-font-size: 30px; -fx-font-weight:bold");
        elementiInterattivi.sceltaUtente.setPrefWidth(100);
        elementiInterattivi.dataA.setMaxWidth(100);
        elementiInterattivi.dataDa.setMaxWidth(100);
        elementiInterattivi.bottoneRefresh.setPrefWidth(80);
        elementiInterattivi.bottoneRefresh.setStyle("-fx-background-color:#5A5858; -fx-text-fill:#FFFFFF");
        elementiInterattivi.bottoneAggiungi.setPrefWidth(120);
        elementiInterattivi.bottoneAggiungi.setStyle("-fx-background-color:#5A5858; -fx-text-fill:#FFFFFF");
        elementiInterattivi.bottoneElimina.setPrefWidth(120);
        elementiInterattivi.bottoneElimina.setStyle("-fx-background-color:#5A5858; -fx-text-fill:#FFFFFF");
        tabellaCorse.tabella.setMaxWidth(492);
        tabellaCorse.tabella.setPrefWidth(492);
        tabellaCorse.tabella.setPrefHeight(400);
        tableBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(100);
        buttonBox.setAlignment(Pos.CENTER);
        dateBox.setSpacing(10);
        dateBox.setAlignment(Pos.CENTER);
    }
}

//01
/* La inizializza si occupa di instanziare tutte le classi necessarie all'esecuzione dell'applicativo. Inoltre
carica dalla cache i dati salvati precedentemente all'ultima chiusura dell'applicazione
*/

//02
/*
Si occupa di effettuare le modifiche grafiche al fine di presentare la schermata come quella disposta nel documento di analisi.
*/