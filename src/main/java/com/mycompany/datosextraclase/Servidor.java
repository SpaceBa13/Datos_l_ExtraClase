package com.mycompany.datosextraclase;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Observable implements Runnable{
    //Definicion de los atributos
    int puerto;
    //Constructor
    public Servidor(int puerto){
        this.puerto = puerto;
    }

    //Se utiliza el metodo run para los hilos
    @Override
    public void run() {
        ServerSocket servidor = null;   //Se define el Socket Servidor
        Socket cliente = null;    //Se define el Socket cliente
        DataInputStream recibir;    //Se define la variable para recibir los datos del Socket
        String mensaje;         //Variable para el envio de los mensajes

        try{
            servidor = new ServerSocket(puerto); //inicia el servidor
            while(true){ //Mantiene el socket activo
                cliente = servidor.accept(); //acepta la coneccion del usuario al Socket servidor
                recibir = new DataInputStream(cliente.getInputStream());  //Se define como la variable que va recibir los datos del socket
                mensaje = recibir.readUTF(); //define la variable como la lectura de los datos del socket

                this.setChanged(); //Marca como modificado al objeto
                this.notifyObservers(mensaje); //Notifica a los observadores que hubo un cambio en la variable mensaje
                this.clearChanged(); //Indica que ya no hay mas cambios

                cliente.close(); //Cierra el socket
            }

        }catch (IOException ex){
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
