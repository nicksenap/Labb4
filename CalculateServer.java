import java.io.*;
import java.net.*;
import java.util.regex.*;

public class CalculateServer {
    public static void main(String [] args){
        CalculateServer calculateServer=new CalculateServer();
        try {
            calculateServer.startServer();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server error！");
        }
    }


    //socketServer
    public void startServer() throws Exception{
        ServerSocket serverSocket = null;
        PrintStream out = null;
        BufferedReader in = null;
        Socket socketObj = null;
        Expression exp;


        try {
            serverSocket = new ServerSocket(1888);
            System.out.println("Server is running...");
            socketObj = serverSocket.accept();
            out = new PrintStream(socketObj.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socketObj.getInputStream()));
        }
            catch(IOException e){
                System.out.println("Server starts fail！");
            }
            boolean flag = true;

            Pattern p = Pattern.compile("(.*([a-zA-Z]+).*)+");
            try {
                while (flag) {
                    String inputString = in.readLine();
                    System.out.println("Received:" + inputString);
                    if (inputString.equals("bye")) {
                        flag = false;
                        continue;
                    }
		    
  		    Matcher m = p.matcher(inputString);
		    if (m.matches()) {
			 out.println("Wrong ！");
			 continue;
		    }

                    if (inputString == null) {
                        out.println("Wrong ！");
                    } else {
                        exp = new Expression(inputString);
                        out.println(exp.getResult());
                    }
                }
            } catch (Exception e) {
                out.println("Wrong！");
                out.println("Server close.");

            } finally {
                try {
                out.close();
                in.close();
                socketObj.close();
                serverSocket.close();
                }catch (NullPointerException e){
                    System.exit(1);
                }

            }

        }
    }
