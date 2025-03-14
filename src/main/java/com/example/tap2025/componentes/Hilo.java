package com.example.tap2025.componentes;

import javafx.scene.control.ProgressBar;

public class Hilo extends Thread{
    private ProgressBar pgbRuta;

    public Hilo(String nombre, ProgressBar pgb){
        /*Es una forma de ponerle nombre al Hilo,
        en este caso el super hace referencia a un elemento de la clase padre,
        y como ya tiene implicita la propiedad nombre, se pasa*/
        super(nombre);
        this.pgbRuta=pgb;
    }

    @Override
    public void run() {
        super.run();
        double avance=0;
        while (avance<1){
            avance+=Math.random()*0.01;
            this.pgbRuta.setProgress(avance);
            try{
                sleep((long)(Math.random()*500));
            }catch (InterruptedException i){}
        }
    }

    /*
    @Override
    public void run() {
        super.run();
        for(int i=1;i<=10;i++){
            try{
                sleep((long)(Math.random()*3000));
            }catch (InterruptedException e){
                throw new RuntimeException(e);//Se puede quitar
            }
            System.out.println("El corredor "+this.getName()+" llego al KM "+i);
        }
    }
*/
}
