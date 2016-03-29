/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convoybackend;

import convoybackend.rmi.ConvoyBackendRmi;
import convoybackend.soap.ConvoyBackendSoap;
import convoybackend.soap.SoapImpl;
import java.io.IOException;
import javax.xml.ws.Endpoint;

/**
 *
 * @author ministeren
 */
public class ConvoyBackend {
    public static void main(String[] arg) throws IOException, Exception{
        
        new ConvoyBackendSoap();
//        new ConvoyBackendRmi();
        
    }
}
