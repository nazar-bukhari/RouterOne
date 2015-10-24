import java.io.FileWriter;
import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: nazar
 * Date: 10/20/15
 * Time: 12:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {

        /**
         * Self Value Declaration
         */
        Model model = new Model();

        model.setRouterZero("Router0");
        model.setWeightRZero("1");

        model.setRouterOne("R1");
        model.setWeightROne("0");

        model.setRouterTwo("R2");
        model.setWeightRTwo("1");

        model.setRouterThree("R3");
        model.setWeightRThree("NA");

        model.setHostZero("localhost"); //Set IP for Router One (Client)
        model.setPortTwo(1501);

        model.setHostTwo("localhost");
        model.setPortZero(1504);

        Gson gson = new Gson();
        String json = gson.toJson(model);

        try {
            FileWriter writer = new FileWriter("Conf.json");
            writer.write(json);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        new Server(model);
    }
}
