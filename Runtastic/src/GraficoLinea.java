

import javafx.collections.*;
import javafx.scene.chart.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marco
 */
public class GraficoLinea {
    LineChart grafico;
    private final GestoreGrafico gestore;
    private final CategoryAxis xAxis;
    private final NumberAxis yAxis;
    
    GraficoLinea(){  //01
        xAxis=new CategoryAxis();
        yAxis=new NumberAxis();
        xAxis.setLabel("Data");
        yAxis.setLabel("Calorie");
        grafico=new LineChart(xAxis, yAxis);
        gestore=new GestoreGrafico();
        grafico.setAnimated(false);
    }
    
    void aggiornaGrafico(ObservableList<Corsa> lista){ //02
        XYChart.Series series=gestore.Aggiorna(lista);
        grafico.getData().clear();
        grafico.getData().add(series);
    }
}

// 01
/* Crea i due assi, setta le label relative agli assi, crea il linechart a partire dagli assi appena creati e instanzia GestoreGrafico
setAnimated(false) serve per evitare problemi di visualizzazione.
*/

//02
/* Instanzia una serie di valori a partire da ciò che ritorna la Aggiorna di gestoreGrafico, inserendo come argomento una
lista di corse. Fa la clear dei dati presenti nel grafico e successivamente aggiunge la serie appena creata.
*/
