import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nazar
 * Date: 10/20/15
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Server {

    private Model model;
    private String hostZero;
    private String hostTwo;
    //    private int sendingPort;
    private int portTwo;
    private int portZero;
    private Socket socket;
    private Gson gson;
    private String packet;
    private String jsonText;
    private List<Integer> portList;
    private List<String> hostList;

    public Server(Model model) {

        this.model = model;

        portList = new LinkedList<Integer>();
        hostList = new LinkedList<String>();

        hostZero = model.getHostZero();
        hostTwo = model.getHostTwo();
//        sendingPort = Integer.parseInt(model.getSendingPort());

        portTwo = model.getPortTwo();
        portZero = model.getPortZero();

        portList.add(portZero);
        portList.add(portTwo);

        hostList.add(hostZero);
        hostList.add(hostTwo);

        gson = new Gson();
        jsonText = gson.toJson(model);


        try {

            /**
             * Creating separate Thread.
             * Each Thread will handle one router.
             */
            for (int i = 0; i < 2; i++) {

                BroadCast broadCast = new BroadCast(hostList.get(i), portList.get(i), gson, jsonText);
                broadCast.start();
            }

        } catch (Exception ex) {
            System.out.println("Waiting.....");
        }



    }

}
