package Controllers;

import com.google.gson.Gson;
import resources.Machine;
import states.State;

import java.util.stream.DoubleStream;

/**
 * Created by tchalas on 9/06/17.
 */
public class MachineController {
    private static MachineController instance = null;
    static Machine machine;
    static Gson gson = null;

    protected MachineController() {
        // Exists only to defeat instantiation.
    }

    public static MachineController getInstance() {
        if(instance == null) {
            instance = new MachineController();
        }
        return instance;
    }

    public void createMachine(String name, String password) {
        this.machine = new Machine(name, password);
    }

    public Object initialize()
    {
        if(machine.getState() != State.waitingInit)
            return "Already init, please  reset";
        return machine.getState().initOp();
    }

    public Object selectProduct(String productName)
    {
        gson = new Gson();
        return gson.toJson(machine.getState().selectProduct(productName));
    }

    public Object insertMoney(double coin)
    {
        gson = new Gson();
        double[] acceptedCoins = {0.05, 0.10, 0.2, 0.5, 1.0, 2.0};
        boolean contains = DoubleStream.of(acceptedCoins).anyMatch(x -> x == coin);
        if(!contains)
            return "Accepted coins:" + gson.toJson(acceptedCoins);
        return gson.toJson(machine.getState().coinInserted(coin));
    }

    public Object cancelOp()
    {
        gson = new Gson();
        return gson.toJson(machine.getState().cancelOp());
    }

    public Object resetOp(String password)
    {
        return machine.getState().resetOp(password);
    }

}
