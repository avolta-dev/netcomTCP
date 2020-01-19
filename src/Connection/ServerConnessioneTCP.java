/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monica Ciuchetti
 */
public class ServerConnessioneTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // porta del server maggiore di 1024 
        int port=2000;
        // tempo in millisecondi di attesa
        int time=5000;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection = null;

        try {
            // il server si mette in ascolto sulla porta voluta
            sSocket = new ServerSocket(port);
            System.out.println("In attesa di connessioni con il client!");
        } catch (IOException ex) {
           System.err.println("Errore di I/O nell'istanza del server!");
        }
                
        while(true){
            try{
                // il server imposta un tempo di attesa
                if (sSocket!=null){
                    sSocket.setSoTimeout(time);
                    //si è stabilita la connessione
                    connection = sSocket.accept();
                    System.out.println("Connessione stabilita!");
                    System.out.println("Socket server: " + connection.getLocalSocketAddress());
                    System.out.println("Socket client: " + connection.getRemoteSocketAddress());
                }
                        
            } catch (SocketTimeoutException ex) {
                System.err.println("Tempo di attesa scaduto!");
            } catch (IOException ex) {
                System.err.println("Errore di I/O della connessione!");
            }
                        
            //chiusura della connessione con il client
            try {
                if (connection!=null) connection.close();
                System.out.println("Connessione chiusa!");
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            
        }
      }
}
