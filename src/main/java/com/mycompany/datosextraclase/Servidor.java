package com.mycompany.datosextraclase;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Observable implements Runnable{
    int puerto;
    public Servidor(int puerto){
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket cliente = null;
        DataInputStream recibir;
        String mensaje;

        try{
            servidor = new ServerSocket(puerto);
            while(true){
                cliente = servidor.accept();
                recibir = new DataInputStream(cliente.getInputStream());
                mensaje = recibir.readUTF();

                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                cliente.close();
            }

        }catch (IOException ex){
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
