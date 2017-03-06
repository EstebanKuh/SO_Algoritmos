/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Procesos.proceso;
import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author Andres
 */
public abstract class AlgoritmoPlanificacion {
   
    protected ArrayList<proceso>lista_arrival= new ArrayList<proceso>() ; 
    protected ArrayList<proceso> cola_prioridad= new ArrayList<proceso>();  //Equivalente a lista listos
    protected ArrayList<proceso> lista_imprimir= new ArrayList<proceso>();
    protected proceso proceso_actual; 
    protected int contador_global=0;
    protected double SumaTotal_Espera;
    protected double SumaTotal;
    protected int tiempo_restante;
    public boolean procesador_ocupado=false;
    public AlgoritmoPlanificacion(ArrayList<proceso> lista_arrival) {
        this.lista_arrival = lista_arrival;
    
    }
    
    public int Calcular_tiempoGlobalLlegada(){
         
           for(int i=0; i<lista_arrival.size();i++){
              tiempo_restante= tiempo_restante + lista_arrival.get(i).getTiempo_llegada();
           }
         
         return tiempo_restante;
     }
    public void gestionar_procesador(){
         
       if(proceso_actual.isTerminado()==true){
           cola_prioridad.get(0).activar_proceso();
           System.out.println("Proceso"+ proceso_actual.getId_proceso()+" "+ "completado");
           SumaTotal=SumaTotal+getProceso_actual().getTiempo_total();
           SumaTotal_Espera=SumaTotal_Espera+getProceso_actual().getTiempo_espera();
           lista_imprimir.add(getProceso_actual());
           setProceso_actual(cola_prioridad.get(0));
           cola_prioridad.remove(0);
           
       }
       else{
           proceso_actual.activar_proceso();
           
       }
            
       }
            
     public void despachar_restantes(){
         if(cola_prioridad.size()>0){
         for(int i=0;i<cola_prioridad.size();i++){
             
          while(cola_prioridad.size()>0&&cola_prioridad.get(i).isTerminado()==false){        
            gestionar_procesador();
            for(int j=0;j<cola_prioridad.size();j++){
             cola_prioridad.get(j).Tiempo_espera++;
            }
          }
         
         }
         while(!proceso_actual.isTerminado()){
                 proceso_actual.activar_proceso();
             }
          SumaTotal=SumaTotal+getProceso_actual().getTiempo_total();
           SumaTotal_Espera=SumaTotal_Espera+getProceso_actual().getTiempo_espera();
           lista_imprimir.add(getProceso_actual());
           System.out.println("Proceso"+ proceso_actual.getId_proceso()+ " "+ "completado");
         }else{
             while(!proceso_actual.isTerminado()){
                 proceso_actual.activar_proceso();
             }
           System.out.println("Proceso"+ proceso_actual.getId_proceso()+ " "+ "completado");
           SumaTotal=SumaTotal+getProceso_actual().getTiempo_total();
           SumaTotal_Espera=SumaTotal_Espera+getProceso_actual().getTiempo_espera();
           lista_imprimir.add(getProceso_actual());
             
         }
     }
    
     
    
       public  void imprimir(){
         for(int i=0; i<lista_imprimir.size();i++){
             System.out.println(lista_imprimir.get(i));
         }
         System.out.println("\n" +"Promedio Total :" +Promedio(SumaTotal));
         System.out.println("\n" +"Promedio de Espera: " +Promedio(SumaTotal_Espera));
         
         
    }
    public double Promedio (double SumaTotal){
        double promedio= SumaTotal/8;
        return promedio;
    }
    
    public proceso getProceso_actual() {
        return proceso_actual;
    }

    public void setProceso_actual(proceso proceso_actual) {
        this.proceso_actual = proceso_actual;
    }
    
    public ArrayList<proceso> getLista_arrival() {
        return lista_arrival;
    }

    public void setLista_arrival(ArrayList<proceso> lista_arrival) {
        this.lista_arrival = lista_arrival;
    }

    public ArrayList<proceso> getCola_prioridad() {
        return cola_prioridad;
    }

    public void setCola_prioridad(ArrayList<proceso> cola_prioridad) {
        this.cola_prioridad = cola_prioridad;
    }

    public int getContador_global() {
        return contador_global;
    }

    public void setContador_global(int contador_global) {
        this.contador_global = contador_global;
    }
   
    
    
    
    
    
}
