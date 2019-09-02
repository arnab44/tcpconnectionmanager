package com.tcpmanager.BrokerServer;


import com.tcpmanager.Message.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//@Slf4j
@Builder
@Getter
@Setter
public class BrokerServer {

    private static int listenerPort= 5001;
    private  static String listenerUrl="127.0.0.1";
    private String listenUrl;
    private int listenPort;
    private ServerSocket serverSocket = null;
    private ObjectInputStream in   =  null;


    public static BrokerServer getBrokerServer(){
        ServerSocket serverSocket = null;
        Socket socket = null;

        ObjectInputStream in = null;
        try {
            serverSocket = new ServerSocket(listenerPort);
        }
        catch (IOException ex) {
           // log.error("Error while creating server socket " + ex.toString());
            System.out.println("Error while creating server socket "+ ex.toString());
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return BrokerServer.builder()
                .serverSocket(serverSocket)
                .listenUrl(listenerUrl)
                .listenPort(listenerPort)
                .build();

    }

    public Message receiveMessage() {
        Message message = null;
        try {
            message = (Message) in.readObject();
        }
        catch(ClassNotFoundException ex ){
         //   log.error("Error while receiving message "+ ex.toString());
        }
        catch (IOException ex ){
         //   log.error("IO Error while receiving message "+ex.toString());
        }

        return message;
    }

    public void closeServer() {
        try {
        //    socket.close();
            serverSocket.close();
            in.close();
        }
        catch(IOException ex) {
        //    log.error("Error while closing connection "+ ex.toString());
        }
    }
}
