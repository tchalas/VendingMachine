package resources;

/**
 * Created by tchalas on 9/06/17.
 */

import products.Coke;
import products.Pepsi;
import products.Product;
import products.Water;
import states.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Machine {
    private State stateVar;
    private String machine_name;
    private  String password;
    private Stack stackSelected = null;
    List<List<Product>> products;
    Stack<Pepsi> pepsiStack;
    Stack<Coke> cokeStack;
    Stack<Water> waterStack;
    public Product product_selected = null;
    public Double moneyInserted;
    public Double moneyCollected;

    //Create the object and initialize its state
    public Machine(String machine_name, String password) {
        this.setState(State.waitingInit);
        stateVar.stateEnter(this);
        this.machine_name = machine_name;
        this.password = password;
        cokeStack = new Stack<Coke>();
        waterStack = new Stack<Water>();
        pepsiStack = new Stack<Pepsi>();
    }

    public void cleanMachineStacks()
    {
        cokeStack = null;
        waterStack = null;
        pepsiStack = null;
    }

    public void initialize()
    {
        for (int i = 0; i < 30; i++)
        {
            cokeStack.push(new Coke("Coke"));
            pepsiStack.push(new Pepsi("Pepsi"));
            waterStack.push(new Water("Water"));
        }
        moneyInserted = 0.0;
        moneyCollected = 0.0;
        product_selected = null;
    }

    public void setToWait()
    {
        moneyInserted = 0.0;
        product_selected = null;
    }

    // Set the new state
    public void setState(State newState) {
        if(stateVar != null)
            stateVar.stateExit(this);
        this.stateVar = newState;
        stateVar.stateEnter(this);
    }

    public State getState(){
        return this.stateVar;
    }

    public Product selectProduct(String productName)
    {
        if(productName.equals("Coke"))
        {
            this.product_selected = cokeStack.peek();
            this.stackSelected = cokeStack;
        }
        else if(productName.equals("Pepsi"))
        {
            this.product_selected = pepsiStack.peek();
            this.stackSelected = pepsiStack;
        }
        else if(productName.equals("Water"))
        {
            this.product_selected = waterStack.peek();
            this.stackSelected = waterStack;
        }
        return this.product_selected;
    }

    public Product serveProduct()
    {
        return (Product) stackSelected.pop();
    }

    public String getPassword() {
        return password;
    }

    public Product getProductSelected() {
        return this.product_selected;
    }

    public Double getMoneyInserted() {
        return moneyInserted;
    }

    public void setMoneyInserted(Double moneyInserted) {
        this.moneyInserted = moneyInserted;
    }

    public Double getMoneyCollected() {
        return moneyCollected;
    }

    public void setMoneyCollected(Double moneyCollected) {
        this.moneyCollected = moneyCollected;
    }
}


