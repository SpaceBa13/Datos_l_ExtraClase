package com.mycompany.datosextraclase;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Servidor representa un espacio en el cual pueden conectarse usuarios
 *
 * @author SpaceBa
 */
public class Servidor extends Observable implements Runnable{
    //Definicion de los atributos

    private int puerto;

    /**
     * Crea un servidor recibiendo como parametro el puerto que va a utlizar
     * @param puerto
     */
    public Servidor(int puerto){
        this.puerto = puerto;
    }

    /**
     *Mantiene la instancia en ejecuccion
     */
    @Override
    public void run() {
        try{
            ServerSocket servidor = new ServerSocket(puerto); //inicia el servidor
            while(true){ //Mantiene el socket activo
                Socket cliente = servidor.accept(); //acepta la coneccion del usuario al Socket servidor
                DataInputStream recibir = new DataInputStream(cliente.getInputStream());  //Se define como la variable que va recibir los datos del socket
                String mensaje = recibir.readUTF(); //define la variable como la lectura de los datos del socket

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
