/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.datosextraclase;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 *Ventana sera la interfaz que utilizara el usuario durante la ejeccion del programa
 * @author SpaceBa
 */
public class Ventana extends javax.swing.JFrame implements Observer{
    /**
     * Representa los clientes que se han registrado en el servidor
     */
    LinkedList Clientes = new LinkedList();

    /**
     * agrega un puerto no registrado en el servidor y verifica que no esten repetidos
     * @param puerto es el puerto que se va a agregar y verificar
     */
    public void verify(Object puerto){
        if (Clientes.size() == 0){
            Clientes.add(puerto);
        }else{
            for (int i = 0; i < Clientes.size(); i++) {
                if(i == Clientes.size() - 1) {
                    if (Clientes.get(i).equals(puerto)) {
                        break;
                    }else{
                        Clientes.add(puerto);
                    }
                }else{
                    if (Clientes.get(i).equals(puerto)) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Crea una instancia del objeto cliente para enviar un mensaje por cada puerto resgistrado en el servidor
     * imprime en el espacio de texto el usuario y el mensaje que se envio
     */
    public void enviar(){
        String mensaje = this.user.getText() + ": " + this.envio_txt.getText() + "\n";
        int puerto_destino = Integer.parseInt(this.puerto_destino.getText()); //Convierte el texto en un puerto leible por el socket
        this.chat_txt.append(mensaje); //Le anade a la caja de texto del chat, el mensaje que se acaba de enviar
        this.envio_txt.setText("");
        verify(puerto_destino);
        for (int i = 0; i < Clientes.size(); i++) {
            Cliente usuario = new Cliente(Integer.parseInt(Clientes.get(i).toString()), mensaje, this.host.getText()); //Se crea una instancia de la clase cliente
            Thread usuario_hilo = new Thread(usuario); //Se crea un hilo para ejecutar la instancia
            usuario_hilo.start();//Se inicia el hilo
        }
    }

    /**
     * llama al metodo initComponents y crea una instancia de la clase servidor
     * Inicia un hilo con la clase servidor para mantenerlo activo
     */
    public Ventana() {
        initComponents();
        Random puerto = new Random();
        int puerto_salida = puerto.nextInt(5000, 20000);
        Servidor servidor = new Servidor(puerto_salida); //Crea una instancia de la clase Servidor
        this.chat_txt.append("--Servidor Iniciado--" + "\n" + "Puerto: " + String.valueOf(puerto_salida) + "\n"); //Le anade a la caja de texto del chat, el mensaje que se acaba de enviar

        servidor.addObserver((Observer) this); //Se anade un observer apuntado a la instancia creada anteriormente
        Thread servidor_hilo = new Thread(servidor);//Crea un hilo para su ejeccucion
        servidor_hilo.start();//Inicia el hilo

        this.getRootPane().setDefaultButton(this.envio_boton);
    }

    /**
     * Inicia los componentes de la interfaz grafica
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        envio_txt = new javax.swing.JTextField();
        envio_boton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat_txt = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        host = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        puerto_destino = new javax.swing.JTextField();
        ip_destino = new javax.swing.JTextField();
        Start = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        envio_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        envio_boton.setText("Enviar");
        envio_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envio_botonActionPerformed(evt);
            }
        });

        chat_txt.setColumns(20);
        chat_txt.setRows(5);
        jScrollPane1.setViewportView(chat_txt);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Puerto Destino");


        host.setText("0");
        host.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("IP destino");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Host");

        puerto_destino.setText("0");

        host.setText("127.0.0.1");

        ip_destino.setText("127.0.0.1");
        ip_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ip_destinoActionPerformed(evt);
            }
        });

        Start.setText("Iniciar Chat");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Usuario:");

        user.setText("User");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(envio_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(envio_boton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(puerto_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ip_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(host, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(Start)))
                                .addGap(16, 16, 16)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(puerto_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ip_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(host, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Start)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(envio_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(envio_boton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Metodo que maneja las funciones de la caja de texto "enviar"

    //Metodo que maneja las funciones de la caja de texto "Ip destino"
    private void ip_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ip_destinoActionPerformed

    }
    /**
     * Metodo que maneja las funciones de la caja de texto "host"
     * @param evt
     */
    private void hostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostActionPerformed

    }
    /**
     * Recibe como parametro un click del usuario
     * @param evt
     */
    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
    }

    /**
     * Recibe como parametro un click del usuario y llama a la funcion enviar
     * @param evt
     */
    private void envio_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envio_botonActionPerformed
        enviar();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Start;
    private javax.swing.JTextArea chat_txt;
    private javax.swing.JButton envio_boton;
    private javax.swing.JTextField envio_txt;
    private javax.swing.JTextField host;
    private javax.swing.JTextField ip_destino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField puerto_destino;
    private javax.swing.JTextField user;

    /**
     * Actualiza los datos recogidos por los observables
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.chat_txt.append((String) arg);
    }
    // End of variables declaration//GEN-END:variables
}
