/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import Procesos.proceso;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author Andres
 */
public class AlgoritmoRR extends AlgoritmoPlanificacion {

    /**
     * @param args the command line arguments
     */
    
    

    public AlgoritmoRR(ArrayList<proceso> lista_arrival) {
        super(lista_arrival);
    }
    
    
    public void IniciaSimulacion(){
         
          boolean BanderaPaso=true;
          int i=0;
          Calcular_tiempoGlobalLlegada();
          if(tiempo_restante==0){
             for(int n=0;n<lista_arrival.size();n++){
                 ordenar_llegada(lista_arrival.get(n));
                 gestionarProcesadorConQuantum();
             }
             despachar_restantes();
          }
          else{
         while(contador_global<=tiempo_restante){
            
             if(BanderaPaso==true){
             ordenar_llegada(lista_arrival.get(i));
             i++;
             contador_global=proceso_actual.getTiempo_llegada();
             
            BanderaPaso=false;
             }
             else{
         if(i<lista_arrival.size()&&(lista_arrival.get(i).getTiempo_llegada()
                 == contador_global||lista_arrival.get(i).getTiempo_llegada()==lista_arrival.get(i-1).getTiempo_llegada())){
             
             ordenar_llegada(lista_arrival.get(i));
            i++;
         }
         
         contador_global++;
         gestionarProcesadorConQuantum();
         evalua_quantum();
         for(int j=0;j<cola_prioridad.size();j++){
             cola_prioridad.get(j).Tiempo_espera++;
         }
         }
         
         
         }
         despachar_restantesConQuantum();
          }
     }

  public void ordenar_llegada(proceso proceso){
                   if(getProceso_actual()==null){
      
                 procesador_ocupado=false;
             }
         if(cola_prioridad.size()==0&&procesador_ocupado==false){
             cola_prioridad.add(proceso);
             procesador_ocupado=true;
             setProceso_actual(cola_prioridad.get(0));
             cola_prioridad.remove(0);

                     }
         else{
             cola_prioridad.add(proceso);
            for(int i=0 ; i<cola_prioridad.size()-1 ; i++)
        {
            for(int j=i+1 ; j<cola_prioridad.size() ;j++)
            {
                proceso p1 = cola_prioridad.get(i);
                proceso p2 = cola_prioridad.get(j);
                if(p2.Evalua_llegada(p1))
                {
                    cola_prioridad.set(i, p2);
                    cola_prioridad.set(j, p1);
                }
            }
        }
         }
        
        
     }
   public void gestionarProcesadorConQuantum(){
         if(getProceso_actual()!=null){
       if(proceso_actual.isTerminado()==true){
           if(cola_prioridad.size()==0){
            System.out.println("Proceso"+ proceso_actual.getId_proceso()+" "+ "completado");
           SumaTotal=SumaTotal+getProceso_actual().getTiempo_total();
           SumaTotal_Espera=SumaTotal_Espera+getProceso_actual().getTiempo_espera();
           lista_imprimir.add(getProceso_actual());
           setProceso_actual(null);
           }
           else{
           cola_prioridad.get(0).activarProceso_quantum();
           System.out.println("Proceso"+ proceso_actual.getId_proceso()+" "+ "completado");
           SumaTotal=SumaTotal+getProceso_actual().getTiempo_total();
           SumaTotal_Espera=SumaTotal_Espera+getProceso_actual().getTiempo_espera();
           lista_imprimir.add(getProceso_actual());
           setProceso_actual(cola_prioridad.get(0));
           cola_prioridad.remove(0);
           }
       }
       else{
           
           proceso_actual.activarProceso_quantum();
           
       }
         }
            
       }
             
     public void despachar_restantesConQuantum(){
         if(cola_prioridad.size()>0){
         for(int i=0;i<cola_prioridad.size();i++){
             
          while(cola_prioridad.size()>0&&cola_prioridad.get(i).isTerminado()==false){
             gestionarProcesadorConQuantum();
             evalua_quantum();
            for(int j=0;j<cola_prioridad.size();j++){
             cola_prioridad.get(j).Tiempo_espera++;
            }
          }
         
         }
         while(!proceso_actual.isTerminado()){
                 proceso_actual.activarProceso_quantum();
             }
           SumaTotal=SumaTotal+getProceso_actual().getTiempo_total();
           SumaTotal_Espera=SumaTotal_Espera+getProceso_actual().getTiempo_espera();
           lista_imprimir.add(getProceso_actual());
           System.out.println("Proceso"+ proceso_actual.getId_proceso()+ " "+ "completado");
         }else{
             if(proceso_actual!=null){
             while(!proceso_actual.isTerminado()){
                 proceso_actual.activarProceso_quantum();
             }
           System.out.println("Proceso"+ proceso_actual.getId_proceso()+ " "+ "completado");
           SumaTotal=SumaTotal+getProceso_actual().getTiempo_total();
           SumaTotal_Espera=SumaTotal_Espera+getProceso_actual().getTiempo_espera();
           lista_imprimir.add(getProceso_actual());
           setProceso_actual(null);
             }
         }
     }
     
     
   public void evalua_quantum(){
       if(getProceso_actual()!=null&&proceso_actual.isQuantumTerminado()==true&& cola_prioridad.size()>0&&proceso_actual.isTerminado()==false){
            System.out.println("\n"+ "El proceso: "+proceso_actual.getId_proceso()+ "regresa a la cola");
             proceso aux;
             aux=proceso_actual;
             setProceso_actual(cola_prioridad.get(0));
             cola_prioridad.remove(0);
            cola_prioridad.add(cola_prioridad.size(),aux);
         }
   }
}
