public class ParametriDatabase {
    public String ip;
    public String porta;
    public String nomeDatabase;
    public String userDatabase;
    public String passwordDatabase;
    
    public ParametriDatabase(String ip, String porta, String nomeDatabase, String userDatabase, String passwordDatabase){
        this.ip=ip;
        this.porta=porta;
        this.nomeDatabase=nomeDatabase;
        this.userDatabase=userDatabase;
        this.passwordDatabase=passwordDatabase;
    }
}
