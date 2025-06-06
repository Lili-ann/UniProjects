/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalmobileapp;

/**
 *
 * @author okafo
 */
public class Mobil {
    int id;
    String nameMobil;
    double tarifPerDay;
    
    public Mobil(int id, String nameMobil, int tarifPerDay){
            this.id = id;
            this.nameMobil = nameMobil;
            this.tarifPerDay = tarifPerDay;
}
    
    public String getNameMobil(){
        return nameMobil;
    }

    public double getTarifPerDay(){
        return tarifPerDay;
}
}

