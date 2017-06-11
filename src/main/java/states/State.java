package states;

import resources.Machine;
import products.Product;

/**
 * Created by tchalas on 9/06/17.
 */
public abstract class State {
    static State initialState;
    public static final State productSelected = new ProductSelected();
    public static final State moneyInserted = new MoneyInserted();
    public static final State waitingSelection = new WaitingSelection();
    public static final State waitingInit = new WaitingInit();
    protected Machine machine;


    protected State() {
        if (initialState == null) initialState = this;
    }

    public abstract void stateExit(Machine machine);
    public abstract void stateEnter(Machine machine);
    public abstract Object serveProduct();
    public abstract Object selectProduct(String productName);
    public abstract Object coinInserted(double value);
    public abstract Object cancelOp();
    public abstract Object resetOp(String password);
    public abstract Object initOp();

}
