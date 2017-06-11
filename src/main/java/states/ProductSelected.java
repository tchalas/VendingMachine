package states;

import resources.Machine;
import products.Product;

/**
 * Created by tchalas on 9/06/17.
 */
public class ProductSelected extends State {

    @Override
    public void stateExit(Machine machine) {
        machine = null;
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
    public Object selectProduct(String productName) {
        Product product = machine.selectProduct(productName);
        return product;
    }

    @Override
    public Object coinInserted(double value) {
        machine.setState(State.moneyInserted);
        return machine.getState().coinInserted(value);
    }

    @Override
    public Object cancelOp() {
        machine.setToWait();
        machine.setState(State.waitingSelection);
        return machine.getMoneyInserted();
    }

    @Override
    public Object resetOp(String password) {
        if(machine.getPassword().equals(password))
        {
            machine.cleanMachineStacks();
            machine.initialize();
            machine.setState(State.waitingSelection);
            return machine.getMoneyCollected();
        }
        return null;
    }

    @Override
    public Object initOp() {
        return null;
    }
}

