package com.tcpmanager.BrokerClient;


import com.tcpmanager.Message.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

//@Slf4j
@Getter
@Setter
@Builder
public class BrokerClient {
    private static final Logger log = Logger.getLogger("tcpconnectionmanager.log");
//    private final static Logger LOGGER =
//            Logger.getLogger("sdfd");

    // @Value("${listen.port}")
    private static final int listenerPort= 5001;
    //  @Value("${listen.url}")
    private static final  String listenerUrl="127.0.0.1";
    private String listenUrl;
    private int listenPort;
    private Socket socket = null;
    private ObjectOutputStream objectOutputStream  = null;

    public static BrokerClient getBrokerClient(){
        Socket socket=null;
        ObjectOutputStream objectOutputStream=null;

        try {
            socket = new Socket(listenerUrl, listenerPort);
            objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());

        }
        catch (IOException ex){
            System.out.println("Error while creating socket "+ ex.toString());
          //  log.log("Error while creating socket "+ ex.toString());
        }
        return BrokerClient.builder().listenPort(listenerPort)
                .listenUrl(listenerUrl)
                .socket(socket)
                .objectOutputStream(objectOutputStream)
                .build();
    }


    public void sendMessage(Message message) {
        //compressMessage(message);
        try {
            objectOutputStream.writeObject(message);
        }
        catch(IOException ex) {
           // log.error("Error while writing object to stream "+ ex.toString());
        }

    }

    public void closeConnection() {

        try{
            socket.close();
            objectOutputStream.close();
        }
        catch(IOException ex) {
          //  log.error("Error while closing client connetion "+ex.toString());
        }
    }
}
