package ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class viewModel1 extends ViewModel {


    Thread hilo;
    MutableLiveData<Integer> trabajo = new MutableLiveData<>();
    MutableLiveData<Integer> descanso = new MutableLiveData<>();
    int finTrabajo=25*60;
    int finDescanso=5*60;

    public void cuentaTrabajo()
    {

        final int tiempoFin = finTrabajo;
        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                int contadorLocal=0;
                while (contadorLocal<tiempoFin)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contadorLocal++;
                    trabajo.postValue(contadorLocal);
                }
            }
        });
        hilo.start();
    }

    public void cuentaDescanso()
    {

        final int tiempoFin = finDescanso;
        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                int contadorLocal=0;
                while (contadorLocal<tiempoFin)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contadorLocal++;
                    descanso.postValue(contadorLocal);
                }
            }
        });
        hilo.start();
    }


}
