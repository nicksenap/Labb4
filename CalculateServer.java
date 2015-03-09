import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.regex.*;

public class CalculateServer {
    final public static int DEFAULT_SERVER_PORT = 1888;
    ArrayList<String> history= new ArrayList<String>();

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
            serverSocket = new ServerSocket(DEFAULT_SERVER_PORT);
            System.out.println("Server is running...");
            socketObj = serverSocket.accept();
            out = new PrintStream(socketObj.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socketObj.getInputStream()));
        }
            catch(IOException e){
                System.out.println("Server starts fail！");
            }
            boolean flag = true;

            Pattern p = Pattern.compile("(.*([a-zA-Z]+).*)+");   //To deal with the bad inputs.
            try {
                while (flag) {
                    String inputString = in.readLine();
                    System.out.println("Received:" + inputString);
                    history.add(inputString);
                    if (inputString.equals("bye")) {             //To break the program.
                        flag = false;
                        continue;
                    }
		    
  		    Matcher m = p.matcher(inputString);
		    if (m.matches()) {
			 out.println("Bad input！");                          //To deal with the bad inputs.
			 continue;
		    }
                    if (inputString == null) {
                        out.println("Empty input");
                    } else {
                        exp = new Expression(inputString);
                        out.println(exp.getResult());
                    }
                }
            } catch (NoSuchElementException e) {
                out.println("Bad input(NoSuchElement).");

            } finally {
                try {
                    out.close();
                    in.close();
                    socketObj.close();
                    serverSocket.close();
                    System.out.println("Here shows you the history in"+ " "+DEFAULT_SERVER_PORT+" "+": ");
                    for (int i = 0;i<history.size();i++){
                        System.out.print(i + 1 + ":" + history.get(i) + "||");}

                }catch (NullPointerException e){
                    System.exit(1);
                }

            }

        }
    }
