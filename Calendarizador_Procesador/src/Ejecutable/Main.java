/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecutable;

import Algoritmos.*;
import Procesos.proceso;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Andres
 */
public class Main {
    
    public ArrayList<proceso> Pre_procesarDatos(ArrayList<proceso> lista_arrival){
          for(int i=0 ; i<lista_arrival.size()-1 ; i++)
        {
            for(int j=i+1 ; j<lista_arrival.size() ;j++)
            {
                proceso p1 = lista_arrival.get(i);
                proceso p2 = lista_arrival.get(j);
                if(p2.Evalua_llegada(p1))
                {
                    lista_arrival.set(i, p2);
                    lista_arrival.set(j, p1);
                }
            }
        }
        return lista_arrival;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        Scanner sc= new Scanner(System.in);
        Scanner ab= new Scanner(System.in);
        Scanner sL= new Scanner(System.in);
        Main main = new Main();
        int rafaga, tiempo_llegada, prioridad;
        int NoProceso=0;
        int cargar;
        ArrayList<proceso> lista_arrival= new ArrayList();
        boolean usuario= true;
        System.out.println("¿Usar valores pre-establecidos o personalizados?(1 o 0)");
        cargar=sc.nextInt();
        if(cargar==1){
          //prioridad,tiempo_llegada,rafaga,NoProceso
          proceso proceso1 = new proceso(5,8,14,1);
          proceso proceso2 = new proceso(2,12,22,2);
          proceso proceso3 = new proceso(8,0,8,3);
          proceso proceso4 = new proceso(5,6,16,4);
          proceso proceso5 = new proceso(7,24,26,5);
          proceso proceso6 = new proceso(9,16,24,6);
          proceso proceso7= new proceso(4,20,12,7);
          proceso proceso8 = new proceso(8,22,18,8);
          proceso prueba1 = new proceso(1,15,3,1);
          proceso prueba2 = new proceso(1,10,1,2);
          proceso prueba3 = new proceso(1,20,15,3);
          
          lista_arrival.add(proceso1);
          lista_arrival.add(proceso2);
          lista_arrival.add(proceso3);
          lista_arrival.add(proceso4);
          lista_arrival.add(proceso5);
          lista_arrival.add(proceso6);
          lista_arrival.add(proceso7);
          lista_arrival.add(proceso8);
       
          /*
          lista_arrival.add(prueba1);
          lista_arrival.add(prueba2);
          lista_arrival.add(prueba3);*/
        main.Pre_procesarDatos(lista_arrival);
        System.out.println("Elegir algoritmo de ordenamiento ");
        System.out.println("Prioridad: 1"+ "\n"+
                           "Prioridad preferente: 2"+ "\n"
                            +"SJF: 3"+ "\n"+
                            "SRJF: 4"+ "\n"
                            +"FCFS: 5"+ "\n"
                            +"RR: 6"+"\n");
        int resp= sL.nextInt();
        switch(resp){
            
            case 1:
                AlgoritmoPrioridad prioridad1 = new AlgoritmoPrioridad(lista_arrival);
                prioridad1.IniciaSimulacion();
                prioridad1.imprimir();
                break;
            case 2:
                 PrioridadPreferente prioridad2 = new PrioridadPreferente(lista_arrival);
                 prioridad2.IniciaSimulacion();
                 prioridad2.imprimir();
                 break;
            case 3:
                 AlgoritmoSJF SJF = new AlgoritmoSJF(lista_arrival);
                 SJF.IniciaSimulacion();
                 SJF.imprimir();
                break;
            case 4:
                AlgoritmoSRJF SRJF = new AlgoritmoSRJF(lista_arrival);
                SRJF.IniciaSimulacion();
                SRJF.imprimir();
                break;
            case 5:
               AlgoritmoFCFS FCFS= new AlgoritmoFCFS(lista_arrival);
               FCFS.IniciaSimulacion();
               FCFS.imprimir();
                break;
            case 6:
               AlgoritmoRR RR= new AlgoritmoRR(lista_arrival);
               RR.IniciaSimulacion();
               RR.imprimir();
                break;
          
        }
        }
        else{
       do{
        NoProceso++;
        System.out.println("Elegir tiempo de rafaga");
        rafaga=sc.nextInt();
        
        System.out.println("Elegir tiempo de llegada");
        tiempo_llegada= sc.nextInt();
        
        System.out.println("Elegir Prioridad");
        prioridad=sc.nextInt();
        proceso proceso = new proceso(prioridad,tiempo_llegada,rafaga,NoProceso);
        lista_arrival.add(proceso);
        System.out.println("Desea introducir otro proceso?");
        usuario= ab.nextBoolean();
       
        }while(usuario==true&&lista_arrival.size()<=8);
        main.Pre_procesarDatos(lista_arrival);
        System.out.println("Elegir Calendarizador");
        String resp= sL.nextLine();
        switch(resp){
            
            case "normal":
                AlgoritmoPrioridad prioridad1 = new AlgoritmoPrioridad(lista_arrival);
                prioridad1.IniciaSimulacion();
                prioridad1.imprimir();
                break;
            case "preferente":
                 PrioridadPreferente prioridad2 = new PrioridadPreferente(lista_arrival);
                 prioridad2.IniciaSimulacion();
                 prioridad2.imprimir();
                 break;
            case "SJF":
                 AlgoritmoSJF SJF = new AlgoritmoSJF(lista_arrival);
                 SJF.IniciaSimulacion();
                 SJF.imprimir();
                break;
            case "SRJF":
                AlgoritmoSRJF SRJF = new AlgoritmoSRJF(lista_arrival);
                SRJF.IniciaSimulacion();
                SRJF.imprimir();
                break;
            case "FCFS":
               AlgoritmoFCFS FCFS= new AlgoritmoFCFS(lista_arrival);
               FCFS.IniciaSimulacion();
               FCFS.imprimir();
                break;
             case "RR":
               AlgoritmoRR RR= new AlgoritmoRR(lista_arrival);
               RR.IniciaSimulacion();
               RR.imprimir();
                break;
          
    }
        }
    
    }
}
