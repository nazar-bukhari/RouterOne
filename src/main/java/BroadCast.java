import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: nazar
 * Date: 10/21/15
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class BroadCast extends Thread{

    private Socket socket;
    private String host;
    private int port;
    private String packet;
    private Gson gson;
    private String jsonText;

    public BroadCast(String host, int port,Gson gson,String jsonText) {
        this.host = host;
        this.port = port;
        this.gson = gson;
        this.jsonText = jsonText;

    }

    @Override
    public void run() {

        ServerSocket server;

        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        Socket socket = null;
        try {
            server = new ServerSocket(port);
            System.out.println("Trying to Accept client request from client " + host+" At port "+port);
            socket = server.accept();

            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            packet = br.readLine();
            //Can display Client Packet here.

            if (socket.isConnected()) {
                System.out.println("Success !! Connected with "+host+".Accepting packet.....");

                while (true) {

                    if (packet != null) {
//                        System.out.println(packet);
//                        Thread.sleep(2000);
                        //Display Received Packet...
                    } else {
                        System.out.println("null Packet");
                    }

                    try {

                        System.out.println("Trying to connect with Client at port " + port); //sendingPort
                        Thread.sleep(2000);
                        if (socket.isConnected()) {
                            System.out.println("Success !! Connected with Client("+host+").Sending Server Routing Table...");
                        }
                        PrintWriter writer = new PrintWriter(socket.getOutputStream());
                        while (true) {

                            writer.write(jsonText);
                            writer.write("\r\n");
                            writer.flush();
                            writer.write("");
                            writer.flush();
                            break;
                        }

                    } catch (Exception ex) {

                        System.out.println("Client is not accepting response");
                        break;
                    }
                    break;
                }
            } else {
                System.out.println("Client yet not connected.");
            }


        } catch (Exception ex) {
//               ex.printStackTrace();
            System.out.println("Connection Closed from Client Side");
        }
    }
}
