/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sftpclient;

import java.io.*; 
import java.net.*; 

/**
 *
 * @author tofupudding
 */

public class SFTPClient {

    /**
     * @param args the command line arguments
     */
    static String[] sftpCommands;
    static String mode;
    
    static String ip;
    static int port;
    
    // static String[] args = new String[]{"localhost", "6789"};
    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        //SFTPClient client = new SFTPClient();
        
        // USER=1, ACCT=2, PASS=3, TYPE=4, LIST=5, CDIR=6, KILL=7, NAME=8, DONE=9, RETR=10, STOR=11
        sftpCommands = new String[]{"USER", "ACCT", "PASS", "TYPE", "LIST", "CDIR", "KILL", "NAME", "DONE", "RETR", "STOR"};
        
        if (args.length == 2){
            try {
                ip = args[0];
                port = Integer.parseInt(args[1]);
                System.out.println("Client will connect to " + ip + " port " + port);
                        
                while(true){
                    System.out.print("> ");
                    String[] commandArgs = selectMode();
                    enterMode(commandArgs);
                } 
            }
            catch (NumberFormatException e) {
               System.out.println("PORT ERROR: Port argument needs to be a number"); 
            } catch (ConnectException e) {
               System.out.println("Connection refused. Server may not be online."); 
            };
        } else {
            System.out.println("ARG ERROR: No arguments. Needs to have 2 arguments IP PORT");
        }
        
//        String sentence; 
//        String modifiedSentence; 
//	
//        BufferedReader inFromUser = 
//	    new BufferedReader(new InputStreamReader(System.in)); 
//	
//        Socket clientSocket = new Socket("localhost", 6789); 
//        
//        DataOutputStream outToServer = 
//	    new DataOutputStream(clientSocket.getOutputStream()); 
//	
//        
//	BufferedReader inFromServer = 
//	    new BufferedReader(new
//		InputStreamReader(clientSocket.getInputStream())); 
//	
//        sentence = inFromUser.readLine(); 
//	
//        outToServer.writeBytes(sentence + '\n'); 
//	
//        modifiedSentence = inFromServer.readLine(); 
//	
//        System.out.println("FROM SERVER: " + modifiedSentence); 
//	
//        clientSocket.close(); 
        
    }
    
    public static String[] selectMode() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] commands = input.split(" ");
        boolean noCommand = false;
        
        for (String sftpCommand : sftpCommands){
            if (commands[0].equals(sftpCommand)){
                System.out.println(sftpCommand);
                mode = sftpCommand;
                noCommand = true;
            }
        }
        
        if (!noCommand){
            System.out.println("INPUT ERROR: Command not recognised");
            System.out.println("Commands available: "
                    + "\"USER\", \"ACCT\", \"PASS\", \"TYPE\", \"LIST\","
                    + "\"CDIR\", \"KILL\", \"NAME\", \"DONE\", \"RETR\", \"STOR\"");
        }
        
        for (int i = 1; i < commands.length; i++){
            System.out.println(commands[i]);
        }
        
        return commands;
    }
    
    public static void enterMode(String[] commandArgs) throws Exception{
        //"USER", "ACCT", "PASS", "TYPE", "LIST", "CDIR", "KILL", "NAME", "DONE", "RETR", "STOR"
        if (null != mode)
        
        switch (mode) {
            case "USER":
                user(commandArgs);
                break;
            case "ACCT":
                acct(commandArgs);
                break;
            case "PASS":
                pass(commandArgs);
                break;
            case "TYPE":
                break;
            case "LIST":
                break;
            case "CDIR":
                break;
            case "KILL":
                break;
            case "NAME":
                break;
            case "DONE":
                break;
            case "RETR":
                break;
            case "STOR":
                break;
            default:
                break;
        }
    }
    
    public static void user(String[] commandArgs) throws Exception{
        if (commandArgs.length != 2){
            System.out.println("ERROR: expected " + commandArgs.length +
                    " arguments, 2 needed. Require USER user-id");
        } else {
            
            try (Socket clientSocket = new Socket("localhost", 6789)) {
                
                DataOutputStream outToServer =
                        new DataOutputStream(clientSocket.getOutputStream());
                
                BufferedReader inFromServer =
                        new BufferedReader(new
                            InputStreamReader(clientSocket.getInputStream()));
                                
                outToServer.writeBytes("USER " + commandArgs[1] + '\n');
                
                String response = inFromServer.readLine();
                
                System.out.println(response);
            } 
        }
    }
    
    public static void acct(String[] commandArgs) throws Exception{
        if (commandArgs.length != 2){
            System.out.println("ERROR: expected " + commandArgs.length +
                    " arguments, 2 needed. Require ACCT account");
        } else {
            
            try (Socket clientSocket = new Socket("localhost", 6789)) {
                
                DataOutputStream outToServer =
                        new DataOutputStream(clientSocket.getOutputStream());
                
                BufferedReader inFromServer =
                        new BufferedReader(new
                            InputStreamReader(clientSocket.getInputStream()));
                                
                outToServer.writeBytes("ACCT " + commandArgs[1] + '\n');
                
                String response = inFromServer.readLine();
                
                System.out.println(response);
            } 
        }
    }
    
    public static void pass(String[] commandArgs) throws Exception{
        if (commandArgs.length != 2){
            System.out.println("ERROR: expected " + commandArgs.length +
                    " arguments, 2 needed. Require PASS password");
        } else {
            
            try (Socket clientSocket = new Socket("localhost", 6789)) {
                
                DataOutputStream outToServer =
                        new DataOutputStream(clientSocket.getOutputStream());
                
                BufferedReader inFromServer =
                        new BufferedReader(new
                            InputStreamReader(clientSocket.getInputStream()));
                                
                outToServer.writeBytes("PASS " + commandArgs[1] + '\n');
                
                String response = inFromServer.readLine();
                
                System.out.println(response);
            } 
        }
    }
}
