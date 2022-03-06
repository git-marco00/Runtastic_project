import javafx.beans.property.*;

public class Corsa {
    private SimpleIntegerProperty idCorsa;
    private SimpleStringProperty nomeUtente;
    private SimpleStringProperty data;
    private SimpleDoubleProperty distanza;
    private SimpleStringProperty tempo;
    private SimpleDoubleProperty andatura;
    private SimpleIntegerProperty calorie;
    
    public Corsa (int idCorsa, String nomeUtente, String data, double distanza, String tempo, double andatura, int calorie){
        this.idCorsa=new SimpleIntegerProperty(idCorsa);
        this.nomeUtente=new SimpleStringProperty(nomeUtente);
        this.data=new SimpleStringProperty(data);
        this.distanza=new SimpleDoubleProperty(distanza);
        this.tempo=new SimpleStringProperty(tempo);
        this.andatura=new SimpleDoubleProperty(andatura);
        this.calorie=new SimpleIntegerProperty(calorie);
    }
    public int getIdCorsa(){
        return idCorsa.get();
    }
    public String getNomeUtente(){
        return nomeUtente.get();
    }
    public String getData(){
        return data.get();
    }
    public double getDistanza(){
        return distanza.get();
    }
    public String getTempo(){
        return tempo.get();
    }
    public Double getAndatura(){
        return andatura.get();
    }
    public int getCalorie(){
        return calorie.get();
    }
    public void setIdCorsa(int idCorsa){
        this.idCorsa= new SimpleIntegerProperty(idCorsa);
    }
    public void setNomeUtente(String nomeUtente){
        this.nomeUtente=new SimpleStringProperty(nomeUtente);
    }
    public void setData(String data){
        this.data=new SimpleStringProperty(data);
    }
    public void setDistanza(double distanza){
        this.distanza=new SimpleDoubleProperty(distanza);
    }
    public void setTempo(String tempo){
        this.tempo=new SimpleStringProperty(tempo);
    }
    public void setAndatura(double andatura){
        this.andatura=new SimpleDoubleProperty(andatura);
    }
    public void setCalorie(int calorie){
        this.calorie=new SimpleIntegerProperty(calorie);
    }
}
