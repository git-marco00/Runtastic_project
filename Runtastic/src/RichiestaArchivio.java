
import java.sql.*;
import java.util.*;


public class RichiestaArchivio {
    private final ParametriDatabase parametri;
    
    public RichiestaArchivio(ParametriDatabase parametri){
        this.parametri=parametri;
    }
    
    public List<Corsa> restituisciCorse(String utente, String dataDa, String dataA){
        List<Corsa> listaCorse=new ArrayList<>();
        String url="jdbc:mysql://";
        url=url.concat(parametri.ip);
        url=url.concat(":");
        url=url.concat(parametri.porta);
        url=url.concat("/");
        url=url.concat(parametri.nomeDatabase);
        try ( Connection co = DriverManager.getConnection(url, parametri.userDatabase, parametri.passwordDatabase);
               PreparedStatement ps =co.prepareStatement("SELECT idcorsa, nomeutente, data, distanza, tempo, andatura, calorie FROM runtastic.utenti NATURAL JOIN runtastic.corse WHERE nomeUtente=? AND data>=? AND data<=?");
            ){
            ps.setString(1, utente);
            ps.setString(2, dataDa);
            ps.setString(3, dataA);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                listaCorse.add(new Corsa(rs.getInt("idcorsa"), rs.getString("nomeutente"), rs.getString("data"), rs.getDouble("distanza"), rs.getString("tempo"), rs.getDouble("andatura"), rs.getInt("calorie")));
            }
        } catch (SQLException e){System.err.println(e.getMessage());}
        
        return listaCorse;
    }
    
    public List<String> restituisciUtenti(){
        List<String> listaUtenti=new ArrayList<>();
        String url="jdbc:mysql://";
        url=url.concat(parametri.ip);
        url=url.concat(":");
        url=url.concat(parametri.porta);
        url=url.concat("/");
        url=url.concat(parametri.nomeDatabase);
        try ( Connection co = DriverManager.getConnection(url, parametri.userDatabase, parametri.passwordDatabase);
               Statement st =co.createStatement();
            ){
            ResultSet rs=st.executeQuery("SELECT nomeUtente FROM runtastic.utenti");
            while(rs.next()){
                listaUtenti.add(rs.getString("nomeutente"));
            }
        } catch(SQLException e){System.err.println(e.getMessage());}
        return listaUtenti;
    }
    
    public void inserisciCorsa(String utente, Corsa nuovaCorsa){
        String url="jdbc:mysql://";
        url=url.concat(parametri.ip);
        url=url.concat(":");
        url=url.concat(parametri.porta);
        url=url.concat("/");
        url=url.concat(parametri.nomeDatabase);
        try ( Connection co = DriverManager.getConnection(url, parametri.userDatabase, parametri.passwordDatabase);
               PreparedStatement ps =co.prepareStatement("INSERT INTO runtastic.corse(nomeutente, data, distanza, tempo, andatura, calorie) VALUES (?,?,?,?,?,?)");
            ){
            ps.setString(1, nuovaCorsa.getNomeUtente());
            ps.setString(2, nuovaCorsa.getData());
            ps.setDouble(3, nuovaCorsa.getDistanza());
            ps.setString(4, nuovaCorsa.getTempo());
            ps.setDouble(5, nuovaCorsa.getAndatura());
            ps.setInt(6, nuovaCorsa.getCalorie());
            ps.executeUpdate();
        }catch (SQLException e) {System.err.println(e.getMessage());}
    }
    
    public void eliminaCorsa(Corsa corsaDaEliminare){
        String url="jdbc:mysql://";
        url=url.concat(parametri.ip);
        url=url.concat(":");
        url=url.concat(parametri.porta);
        url=url.concat("/");
        url=url.concat(parametri.nomeDatabase);
        try ( Connection co = DriverManager.getConnection(url, parametri.userDatabase, parametri.passwordDatabase);
               PreparedStatement ps =co.prepareStatement("DELETE FROM runtastic.corse WHERE idcorsa=?");
            ){
            ps.setInt(1, corsaDaEliminare.getIdCorsa());
            ps.executeUpdate();
        }catch (SQLException e) {System.err.println(e.getMessage());}
    }
    
    public int restituisciPeso(String utente){
        int pesoUtente=0;
        String url="jdbc:mysql://";
        url=url.concat(parametri.ip);
        url=url.concat(":");
        url=url.concat(parametri.porta);
        url=url.concat("/");
        url=url.concat(parametri.nomeDatabase);
        try ( Connection co = DriverManager.getConnection(url, parametri.userDatabase, parametri.passwordDatabase);
               PreparedStatement ps =co.prepareStatement("SELECT pesoUtente FROM runtastic.utenti WHERE nomeutente=?");
            ){
            ps.setString(1, utente);
            ResultSet rs = ps.executeQuery();
            rs.next();
            pesoUtente=rs.getInt("pesoUtente");
        }catch (SQLException e) {System.err.println(e.getMessage());}
        return pesoUtente;
    }
}

/*
La classe si occupa di effettuare tutte le richieste al database. In particolare restituisce la lista delle corse relative
ad un dato periodo di tempo e di un dato utente, la lista degli utenti, e il peso dato un utente.
Inoltre si occupa di inserire o eliminare una corsa dal database.
*/
