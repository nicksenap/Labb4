import java.io.*;
import java.net.*;

public class CalculateClient {
    public static void main(String [] args){
        CalculateClient calculateClient=new CalculateClient();
        try {
            calculateClient.startClient();
        } catch (Exception e) {
            System.out.println("Error while start！");
        }
    }
    public void startClient () throws Exception{
        Socket socketObj=new Socket("localhost",1888);
        PrintStream out=null;
        BufferedReader in=null;
        out=new PrintStream(socketObj.getOutputStream());
        in=new BufferedReader(new InputStreamReader(socketObj.getInputStream()));
        BufferedReader localInput=null;
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
                //System.out.println("Result：");
                System.out.println(input);
            }
        }
        out.close();
        in.close();
        localInput.close();
        socketObj.close();
    }
}