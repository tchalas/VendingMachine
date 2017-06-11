package states;

import resources.Machine;
import products.Product;

/**
 * Created by tchalas on 10/06/17.
 */
public class WaitingInit  extends State {
    @Override
    public void stateExit(Machine machine) {
        this.machine = null;
    }

    @Override
    public void stateEnter(Machine machine) {
        this.machine = machine;
    }

    @Override
    public Product serveProduct() {
        return null;
    }

    @Override
    public Product selectProduct(String productName) {
        return null;
    }

    @Override
    public Object coinInserted(double value) {
        return null;
    }

    @Override
    public Object cancelOp() {
        return null;
    }

    @Override
    public Object resetOp(String password) {
        return null;
    }

    @Override
    public Object initOp() {
        machine.initialize();
        machine.setState(State.waitingSelection);
        return "Machine Initialized";
    }
}
