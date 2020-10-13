package com.example.tel306;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public  class ContadorViewModel extends ViewModel {


    private Thread hilo=null;
    private MutableLiveData<Integer> trabajo = new MutableLiveData<>(0);
    private MutableLiveData<Integer> descanso = new MutableLiveData<>(0);
    private int finTrabajo=5;
    private int finDescanso=3;
    private String activo="trabajo";

    public void cuentaTrabajo()
    {

        final int tiempoFin = finTrabajo;
        if (hilo == null)
        {
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    activo ="trabajo";
                    int contadorLocal=trabajo.getValue();
                    while (contadorLocal<tiempoFin) {
                        try {
                            Thread.sleep(1000);
                            contadorLocal++;
                            trabajo.postValue(contadorLocal);
                            Log.d("contador", "run: " + String.valueOf(contadorLocal));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    hilo=null;
                }
            });
            hilo.start();
        }

    }

    public void cuentaDescanso()
    {


        if(hilo == null)
        {
            final int tiempoFin = finDescanso;
            hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    activo="descanso";
                    int contadorLocal=descanso.getValue();
                    while (contadorLocal<tiempoFin)
                    {
                        try {
                            Thread.sleep(1000);
                            contadorLocal++;
                            descanso.postValue(contadorLocal);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }

                    }
                    hilo=null;
                }
            });

            hilo.start();
        }


    }

    public  void detenerContador()
    {
        if (hilo != null)
        {
            hilo.interrupt();
        }

    }

    public Thread getHilo() {
        return hilo;
    }

    public void setHilo(Thread hilo) {
        this.hilo = hilo;
    }

    public MutableLiveData<Integer> getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(MutableLiveData<Integer> trabajo) {
        this.trabajo = trabajo;
    }

    public MutableLiveData<Integer> getDescanso() {
        return descanso;
    }

    public void setDescanso(MutableLiveData<Integer> descanso) {
        this.descanso = descanso;
    }

    public int getFinTrabajo() {
        return finTrabajo;
    }

    public void setFinTrabajo(int finTrabajo) {
        this.finTrabajo = finTrabajo;
    }

    public int getFinDescanso() {
        return finDescanso;
    }

    public void setFinDescanso(int finDescanso) {
        this.finDescanso = finDescanso;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
