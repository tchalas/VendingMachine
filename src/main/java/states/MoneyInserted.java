package states;

import com.google.gson.JsonObject;
import resources.Machine;
import products.Product;

/**
 * Created by tchalas on 9/06/17.
 */
public class MoneyInserted extends State {


    @Override
    public void stateExit(Machine machine) {
        this.machine = null;
    }

    @Override
    public void stateEnter(Machine machine) {
        this.machine = machine;
    }

    @Override
    public Object serveProduct() {

        Product product = this.machine.serveProduct();
        double change = this.machine.getMoneyInserted() - product.getPrice();

        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("Change", change);
        innerObject.addProperty("Product", product.getName());
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Served", innerObject);
        this.machine.setMoneyCollected(machine.getMoneyCollected() + product.getPrice());
        this.machine.setToWait();
        this.machine.setState(State.waitingSelection);
        return jsonObject;
    }

    @Override
    public Object selectProduct(String productName) {
        if(machine.getProductSelected() != null)
            return "Operation not allowed, canlel to resellect";
        Product product = machine.selectProduct(productName);
        if(machine.getMoneyInserted() >= machine.getProductSelected().getPrice())
        {
            return this.serveProduct();
        }
        else
        {
            return product.getPrice();
        }
    }

    @Override
    public Object coinInserted(double value) {
        machine.setMoneyInserted(machine.getMoneyInserted() + value);
        if(machine.getProductSelected() != null)
        {
            if(machine.getMoneyInserted() >= machine.getProductSelected().getPrice())
            {
                return this.serveProduct();
            }
        }
        return machine.getMoneyInserted();
    }

    @Override
    public Object cancelOp() {
        double inserted = machine.getMoneyInserted();
        machine.setToWait();
        machine.setState(State.waitingSelection);
        return inserted;
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
