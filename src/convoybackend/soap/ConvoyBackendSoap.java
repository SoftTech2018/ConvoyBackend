/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convoybackend.soap;

import javax.xml.ws.Endpoint;

/**
 *
 * @author ministeren
 */
public class ConvoyBackendSoap {

    public ConvoyBackendSoap() {
        System.out.println("Publicerer soap-tjeneste");
        SoapImpl soapserv = new SoapImpl();
        
// Ipv6-addressen [::] svarer til Ipv4-adressen 0.0.0.0, der matcher alle maskinens netkort og
//        Endpoint.publish("http://[::]:9943/convoyjeneste", soapserv);
        Endpoint.publish("http://localhost:9943/convoyjeneste", soapserv);
        System.out.println("Soap-tjeneste publiceret");
    }

    
    
}
