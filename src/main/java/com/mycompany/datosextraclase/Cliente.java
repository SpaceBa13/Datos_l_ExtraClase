package com.mycompany.datosextraclase;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SpaceBa
 */
public class Cliente implements Runnable{
    int puerto;
    String mensaje;

    public Cliente(int puerto, String mensaje){
        this.puerto = puerto;
        this.mensaje = mensaje;
    }


    @Override
    public void run() {
        String IP = "127.0.0.1";
        DataOutputStream envio;
        try{
            Socket socket_c = new Socket(IP, puerto);
            envio = new DataOutputStream(socket_c.getOutputStream());
            envio.writeUTF(mensaje);
            socket_c.close();

        }catch (Exception ex){
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE,null,ex);

        }
    }
}
