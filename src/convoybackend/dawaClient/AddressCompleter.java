/*
 DAWA client!

 */
package convoybackend.dawaClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author KimDrewes
 */
public class AddressCompleter {
    
    
 public ArrayList<String> getAddresses(String addressQuery){
  //Instantierer en client
    Client client = ClientBuilder.newClient();
     try {
         //Sørger for at mellemrum, æøå osv, kan sendes til REST serveren
         addressQuery = URLEncoder.encode(addressQuery, "UTF-8");
     } catch (UnsupportedEncodingException ex) {
         Logger.getLogger(AddressCompleter.class.getName()).log(Level.SEVERE, null, ex);
     }
     
         //Kalder GET - get() på target og accepterer JSON
        Response res = client.target("http://dawa.aws.dk/adresser?q="+addressQuery).request(MediaType.APPLICATION_JSON).get();
     
        //Sætter svaret til at være en string
        String resultString = res.readEntity(String.class);
        ArrayList<String> addressList = new ArrayList<>();
    
     try {
         //DAWA rest apiet bliver sendt som et stort JSONarray.
           JSONArray jsonarray = new JSONArray(resultString);
           
           //Der køres igennem alle elementerne i array, og der bliver lavet
           // JSON objekter af disse
           // Der bliver sorteret så vi kun henter den ressourse der hedder
           // addressebetegnelse!
         for (int i = 0; i < jsonarray.length(); i++) {
             JSONObject jsonobject = jsonarray.getJSONObject(i);
             String address = jsonobject.getString("adressebetegnelse");
             addressList.add(address);
            }       
         }catch (JSONException ex) {
             Logger.getLogger(AddressCompleter.class.getName()).log(Level.SEVERE, null, ex);
     }
client.close();
return addressList;
}   
}


