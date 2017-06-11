package api; /**
 * Created by tchalas on 9/06/17.
 */
import Controllers.MachineController;
import com.fasterxml.jackson.databind.JsonNode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/machine")
public class MachineAPI {

    @POST
    @Path("init")
    @Consumes(MediaType.APPLICATION_JSON)
    public void initMachine(JsonNode data) {
        MachineController.getInstance().initialize();
    }

    @POST
    @Path("product")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object selectProduct(JsonNode product) {
        return MachineController.getInstance().selectProduct(product.get("Product").asText());
    }

    @POST
    @Path("coin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object insertCoin(JsonNode coin) {
        double coinValue = coin.get("Coin").asDouble();
        return MachineController.getInstance().insertMoney(coinValue);
    }

    @POST
    @Path("cancel")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object cancelOp(){
        return MachineController.getInstance().cancelOp();
    }

    @POST
    @Path("reset")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void resetOp(JsonNode product){
        MachineController.getInstance().resetOp(product.get("Password").asText());
    }

}
