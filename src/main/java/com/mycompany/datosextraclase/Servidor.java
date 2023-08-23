package com.mycompany.datosextraclase;

import java.util.Observable;

public class Servidor extends Observable implements Runnable{
    int puerto;
    public Servidor(int puerto){
        this.puerto = puerto;
    }

    @Override
    public void run() {

    }
}
