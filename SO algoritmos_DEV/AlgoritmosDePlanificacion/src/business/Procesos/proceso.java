/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Procesos;

/**
 *
 * @author Andres
 */
public class proceso {

    public int prioridad;

    public int Tiempo_llegada;
    public int Tiempo_rafaga;
    public int Tiempo_total;
    public int Tiempo_espera;
    public int id_proceso;
    public boolean terminado;
    public int Tiempo_rafaga2;
    public int quantum;
    public int quantumClon;
    public boolean quantumTerminado;

    public proceso(int prioridad, int quantum, int Tiempo_llegada, int Tiempo_rafaga, int id_proceso) {
        this.prioridad = prioridad;
        this.quantum = quantum;
        this.Tiempo_llegada = Tiempo_llegada;
        this.Tiempo_rafaga = Tiempo_rafaga;
        this.quantum=quantum=8;
        this.id_proceso = id_proceso;
        quantumClon=this.quantum;

    }

    public proceso(int prioridad, int Tiempo_llegada, int Tiempo_rafaga, int id_proceso) {
        this(Tiempo_llegada,Tiempo_rafaga,id_proceso);
        this.prioridad = prioridad;
        this.Tiempo_rafaga2 = Tiempo_rafaga;
//        this.Tiempo_llegada = Tiempo_llegada;
//        this.Tiempo_rafaga = Tiempo_rafaga;
//        this.id_proceso = id_proceso;
         quantum=8;
         quantumClon = quantum;
    }

    public proceso(int Tiempo_llegada, int Tiempo_rafaga, int id_proceso) {
        this.Tiempo_llegada = Tiempo_llegada;
        this.Tiempo_rafaga = Tiempo_rafaga;
         this.Tiempo_rafaga2 = Tiempo_rafaga;
        this.id_proceso = id_proceso;
        quantum=8;
        quantumClon = quantum;
    }

    public boolean ComparaPrioridad(proceso compara_con) {
        boolean flag = false;
        if (getPrioridad() > compara_con.getPrioridad()) {
            flag = true;
        }
        if (getPrioridad() == compara_con.getPrioridad()) {
            flag = Evalua_llegada(compara_con);
        }

        return flag;
    }

    public boolean ComparaRafagas(proceso compara_con) {
        boolean flag = false;
        if (Tiempo_rafaga2 < compara_con.Tiempo_rafaga2) {
            flag = true;
        }
        if (Tiempo_rafaga == compara_con.Tiempo_rafaga) {
            flag = Evalua_llegada(compara_con);
        }

        return flag;
    }

    public boolean Evalua_llegada(proceso compara_con) {
        boolean flag = false;
        if (Tiempo_llegada == compara_con.getTiempo_llegada()) {
            return (getId_proceso() < compara_con.getId_proceso());
        }

        return flag = Tiempo_llegada < compara_con.getTiempo_llegada();
    }

    public boolean activar_proceso() {
        setTerminado(false);
        Tiempo_rafaga2--;
        if (Tiempo_rafaga2 <= 0) {
            setTerminado(true);
            setTiempo_total(Tiempo_rafaga + getTiempo_espera());
        }
        return terminado;
    }

    public boolean activarProceso_quantum() {
        setTerminado(false);
        Tiempo_rafaga2--;
        quantumClon--;
        if (quantumClon == 0) {
            setQuantumTerminado(true);

            quantumClon = getQuantum();

        }
        if (Tiempo_rafaga2 <= 0) {
            setTerminado(true);
            setTiempo_total(Tiempo_rafaga + getTiempo_espera());
        }
        return terminado;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public boolean isQuantumTerminado() {
        return quantumTerminado;
    }

    public void setQuantumTerminado(boolean quantumTerminado) {
        this.quantumTerminado = quantumTerminado;
    }

    public int getId_proceso() {
        return id_proceso;
    }

    public void setId_proceso(int id_proceso) {
        this.id_proceso = id_proceso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getTiempo_llegada() {
        return Tiempo_llegada;
    }

    public void setTiempo_llegada(int Tiempo_llegada) {
        this.Tiempo_llegada = Tiempo_llegada;
    }

    public int getTiempo_rafaga() {
        return Tiempo_rafaga;
    }

    public void setTiempo_rafaga(int Tiempo_rafaga) {
        this.Tiempo_rafaga = Tiempo_rafaga;
    }

    public int getTiempo_total() {
        return Tiempo_total;
    }

    public void setTiempo_total(int Tiempo_total) {
        this.Tiempo_total = Tiempo_total;
    }

    public int getTiempo_espera() {
        return Tiempo_espera;
    }

    public void setTiempo_espera(int Tiempo_espera) {
        this.Tiempo_espera = Tiempo_espera;
    }

    @Override
    public String toString() {
        return "\n" + "No.Proceso=" + id_proceso + "\n"
                + "Tiempo de llegada=" + Tiempo_llegada + "\n"
                + "Tiempo de rafaga=" + Tiempo_rafaga + "\n"
                + "Prioridad=" + prioridad + "\n"
                + "Quantum=" + quantum + "\n"
                + "Tiempo total=" + Tiempo_total + "\n"
                + "Tiempo de espera=" + Tiempo_espera;
    }

}
