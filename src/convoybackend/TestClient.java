/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convoybackend;

import convoybackend.soap.SoapInt;
import java.net.URL;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author ministeren
 */
public class TestClient {
    
    public static void main(String[] args) throws Exception {
        
        
        URL urlSoap = new URL("http://localhost:9943/convoyjeneste?wsdl");
        QName qnameSoap = new QName("http://soap.convoybackend/", "SoapImplService");
        Service soapService = Service.create(urlSoap, qnameSoap);
        SoapInt soapserv = soapService.getPort(SoapInt.class);
        
        System.out.println(soapserv.Hent("test"));
        
    }
}
