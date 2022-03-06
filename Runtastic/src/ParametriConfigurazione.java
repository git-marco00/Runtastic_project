/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marco
 */
public class ParametriConfigurazione {
    ParametriDatabase parametriDB;
    int xVbox, yVbox;
    String nomeSalvataggioCache;
    String coloreBackground;
    String indirizzoServer;
    String indirizzoClient;
    int portaServer;
    int portaClient;
    
    ParametriConfigurazione(ParametriDatabase parametriDB, int xVbox, int yVbox, String nomeSalvataggioCache, String coloreBackground, String indirizzoServer, String indirizzoClient, int portaServer, int portaClient){
        this.parametriDB=parametriDB;
        this.xVbox=xVbox;
        this.yVbox=yVbox;
        this.nomeSalvataggioCache=nomeSalvataggioCache;
        this.indirizzoServer=indirizzoServer;
        this.indirizzoClient=indirizzoClient;
        this.portaClient=portaClient;
        this.portaServer=portaServer;
    }
}
