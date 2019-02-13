/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahma
 */
public class Client {
    private final String serverName;
    private final int serverPort;
    private Socket clientSocket;
    private InputStream serverIn ;
    private OutputStream serverOut;
    private BufferedReader  bufferedIn;
    private ArrayList <UserStatusListener> userStatusListeners=new ArrayList(); 
    private ArrayList <MessageListener> messageListeners=new ArrayList(); 



    /**
     * @param args the command line arguments
     */
    //constractor 
    public Client(String serverName , int serverPort)
    {
     this.serverName =serverName;
     this.serverPort=serverPort;
   
    
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Client newClient =new Client ("localhost",4444);
        newClient.addUserStatusListener(new UserStatusListener(){

            @Override
            public void online(String userEmail) {
             System.out.println("Online:"+userEmail);
            
            }

            @Override
            public void offline(String userEmail) {
             System.out.println("Offline:"+userEmail);
            }
        
        
        
        });
        
        newClient.addMessageListener(new MessageListener(){

            @Override
            public void onMessage(String fromLoginEmail, String msgBody) {

           System.out.print("You got message from"+fromLoginEmail +"==>"+msgBody); 
            }
        });
        
        
       if(!newClient.connectToServer()) 
       {  System.err.println("Connect failed ");
       }else {
       System.out.println("Connect successful");
       // newClient.login("guest", "guest");
        newClient.msg("jim", "Hello World");
        
       }
        
    }//main

    public boolean connectToServer() {
        try {
            Socket clientSokect =new Socket(serverName , serverPort);
            System.out.println ("Client port is "+clientSokect.getLocalPort());
            this.serverOut=clientSokect.getOutputStream();
            this.serverIn=clientSokect.getInputStream();
            this.bufferedIn= new BufferedReader(new InputStreamReader(serverIn));
            return true ;
        } catch (IOException ex) {
           ex.printStackTrace();      
        }
        
        return false ;
        
        
    }

    public  void login(String userEmail, String userPassword) throws IOException {
        
        String cmd = "LOGIN "+userEmail +" "+userPassword +"\n";
        serverOut.write(cmd.getBytes());
        String serverResponse =bufferedIn.readLine();
        System.out.println("Server Responce :"+serverResponse);
        
//       if(serverResponse."ok login".equalsIgnoreCase(serverResponse))
//                     
//       return true ;
//       else 
//           return false ;
        
        startMessageReader();
               
        
    }
    
    private void loggoff () throws IOException
    { String cmd ="loggoff\n";
      serverOut.write(cmd.getBytes());
    
    }
    
    
    public void addUserStatusListener(UserStatusListener listener )
    {
       userStatusListeners.add(listener);
       
    }
    
      public void removeUserStatusListener(UserStatusListener listener )
    {
       userStatusListeners.remove(listener);
       
    }

    private void startMessageReader() {
        Thread messagesThread =new Thread()
        {
            public void run()
            {
             readMessageLoop();
            }
        
        
        };
        messagesThread.start();
        
    }
    
   private void  readMessageLoop()
   { try{
    String clientMessage ;
    while ((clientMessage =bufferedIn.readLine()) !=null){
        
          String [] inputTokens=clientMessage.split(" ");
            if(inputTokens.length > 0){
                String cmd=inputTokens[0];
                if("online".equalsIgnoreCase(cmd)){
                  handleOnline(inputTokens);
                
                }else if ("offline".equalsIgnoreCase(cmd))
                {handleOffline(inputTokens);
                }else if ("msg".equalsIgnoreCase(cmd))
                {
                   String [] tokensMsg=clientMessage.split(" ");
                   handeleMessage(tokensMsg);
                }
                
    
    }
    }
   }catch(Exception ex) {
   ex.printStackTrace();
       try {
           clientSocket.close();
       } catch (IOException ex1) {
            ex1.printStackTrace();
       }
   
   
   }
   
   
    
}
   
 public void   handleOnline(String [] tokens){
 String userEmail=tokens[1];
 for(UserStatusListener listener : userStatusListeners)
 {
     listener.online(userEmail);
 }
  
 }
 
 public void   handleOffline(String [] tokens){
 String userEmail=tokens[1];
 for(UserStatusListener listener : userStatusListeners)
 {
     listener.offline(userEmail);
 }
  
 }
 
 

    private void msg(String sendTo, String msgBody) throws IOException {
        
        String cmd ="msg "+sendTo+" "+msgBody+"\n";
        serverOut.write(cmd.getBytes());
        
    }
    
    public void addMessageListener(MessageListener listener)
    { messageListeners.add(listener);
    }
     public void removeMessageListener(MessageListener listener)
    { messageListeners.remove(listener);
    }

    private void handeleMessage(String[] tokensMsg) {

    String userEmail=tokensMsg[1];
    String msgBody=tokensMsg[2];
    for(MessageListener listener :messageListeners)
    {
      listener.onMessage(userEmail , msgBody);
    
    }
    
    
}
}