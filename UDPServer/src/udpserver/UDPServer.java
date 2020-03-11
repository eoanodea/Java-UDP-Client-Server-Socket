/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 *
 * @author n00162393
 */
public class UDPServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        
        while(true) {
            DatagramPacket receivePacket = new DatagramPacket(
                    receiveData,
                    receiveData.length
            );
            serverSocket.receive(receivePacket);
            
            String clientSentence = new String(
                    receivePacket.getData(),
                    0,
                    receivePacket.getLength()
            );
            
            
            System.out.println("Incoming Message: " + clientSentence + "\n");
            System.out.println("Enter a message send back");
            
            Scanner inFromUser = new Scanner(System.in);
            
            
            
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            
            String sentence = inFromUser.nextLine();
           
            sendData = sentence.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(
                    sendData, 
                    sendData.length, 
                    IPAddress, 
                    port
            );
            
            serverSocket.send(sendPacket);
        }
    }
    
}
