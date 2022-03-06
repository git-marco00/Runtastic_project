
import javafx.collections.*;
import javafx.scene.chart.*;

/**
 *
 * @author marco
 */
public class GestoreGrafico {
    private XYChart.Series series;
    
    GestoreGrafico(){
        series=new XYChart.Series();
        series.setName("Andamento calorie");
    }
    
    XYChart.Series Aggiorna(ObservableList<Corsa> lista){ //01
        series.getData().clear();
        lista.forEach((corsa)->{
            if(corsa.getCalorie()!=0){
                series.getData().add(new XYChart.Data(corsa.getData(), corsa.getCalorie()));
            }
        });
        return series;
    }
}

//01
/* Si occupa di aggiornare la serie di valori xy data una ObservableList di corse. Prima fa la pulizia di tutti i valori
precedenti e poi uno a uno li aggiunge alla serie.
*/
