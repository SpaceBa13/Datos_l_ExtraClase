package com.mycompany.datosextraclase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Cliente representa un espacio por el cual nos conectaremos a un servidor
 * @author SpaceBa
 */
public class Cliente implements Runnable{
    int puerto_destino;
    String mensaje;
    String IP;

    /**
     * Crea una instancia del objeto cliente, usando como parametros el puerto de destino y un mensaje
     * @param puerto_destino puerto del servidor al que se conectara el cliente
     * @param mensaje mensaje que sera enviado mediante el socket
     */
    public Cliente(int puerto_destino, String mensaje, String IP){
        //Se
        this.puerto_destino = puerto_destino;
        this.mensaje = mensaje;
        this.IP = IP;
    }
    /**
     * Mantiene la instancia en ejecuccion
     */
    @Override
    public void run() {
        //Se definen las variables que se van a utilizar para el socket
        try{
            Socket socket_c = new Socket(IP, puerto_destino); //Creacion del Socket
            DataOutputStream envio = new DataOutputStream(socket_c.getOutputStream()); //Definicion de envio como parte del socket
            envio.writeUTF(mensaje); //Se escribe el mensaje en el Socket
            socket_c.close(); //Se cierra el socket

        }catch (Exception ex){
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE,null,ex);

        }
    }
}
