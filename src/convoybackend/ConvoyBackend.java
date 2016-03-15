/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convoybackend;

import convoybackend.soap.SoapImpl;
import java.io.IOException;
import javax.xml.ws.Endpoint;

/**
 *
 * @author ministeren
 */
public class ConvoyBackend {
    public static void main(String[] arg) throws IOException{
        
        System.out.println("Publicerer tjenester");
        SoapImpl soapserv = new SoapImpl();
        
// Ipv6-addressen [::] svarer til Ipv4-adressen 0.0.0.0, der matcher alle maskinens netkort og
//        Endpoint.publish("http://[::]:9943/convoyjeneste", soapserv);
        Endpoint.publish("http://localhost:9943/convoyjeneste", soapserv);
        System.out.println("Tjenester publiceret");
    }
}
