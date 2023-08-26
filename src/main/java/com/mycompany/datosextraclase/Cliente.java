package com.mycompany.datosextraclase;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SpaceBa
 */
public class Cliente implements Runnable{
    //Definicion de los atributos
    int puerto_destino;
    String mensaje;

    //Constructor
    public Cliente(int puerto_destino, String mensaje){
        //Se
        this.puerto_destino = puerto_destino;
        this.mensaje = mensaje;
    }
    @Override
    public void run() {
        //Se definen las variables que se van a utilizar para el socket
        String IP = "127.0.0.1";
        DataOutputStream envio; //Envio de Datos
        try{
            Socket socket_c = new Socket(IP, puerto_destino); //Creacion del Socket
            envio = new DataOutputStream(socket_c.getOutputStream()); //Definicion de envio como parte del socket
            envio.writeUTF(mensaje); //Se escribe el mensaje en el Socket
            socket_c.close(); //Se cierra el socket

        }catch (Exception ex){
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE,null,ex);

        }
    }
}
