import java.io.*;
import java.net.*;

public class CalculateClient {

    final public static int DEFAULT_SERVER_PORT = 1888;
    PrintStream out=null;
    BufferedReader in=null;
    BufferedReader localInput=null;

    // The main method.
    public static void main(String [] args) throws Exception{
        CalculateClient calculateClient=new CalculateClient();
        if (args.length == 2) {
            try {
                calculateClient.startClient(args[0],args[1]);
            } catch (Exception e){
            }
        }
        try {
            calculateClient.startClient();
        } catch (Exception e) {
            System.out.println("Error while it starting！");
        }
    }
    //Start Client with interactive "user interface".
    public void startClient () throws Exception{
        Socket socketObj=new Socket("localhost",DEFAULT_SERVER_PORT);
        out=new PrintStream(socketObj.getOutputStream());
        in=new BufferedReader(new InputStreamReader(socketObj.getInputStream()));
        localInput=new BufferedReader(new InputStreamReader(System.in));
        boolean flag=true;
        while (flag) {
            System.out.println("Enter numbers,t.ex 1+2+3+4 or Bye for quit:");
            String inputString = localInput.readLine();
            out.println(inputString);
            if(inputString.equals("bye")){
                flag=false;
            }else{
                String input=in.readLine();
                System.out.println(input);
            }
        }
        out.close();
        in.close();
        localInput.close();
        socketObj.close();
    }
    //Start Client with a "one times run" in Terminal.
    public void startClient (String IPadress , String MathExpression) throws Exception{
        Socket socketObj=new Socket(IPadress,DEFAULT_SERVER_PORT);
        out=new PrintStream(socketObj.getOutputStream());
        in=new BufferedReader(new InputStreamReader(socketObj.getInputStream()));
        String inputString = MathExpression;
        out.println(inputString);
        String input = in.readLine();
        System.out.println(input);
        out.close();
        in.close();
        socketObj.close();
        System.exit(1);
    }




}