/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convoybackend.rmi;


import java.rmi.Naming;
import brugerautorisation.transport.rmi.Bruger;

/**
 *
 * @author ministeren
 */
public class ConvoyBackendRmi
{
    
    Brugeradmin ba;

    public ConvoyBackendRmi() throws Exception{
        System.out.println("Publicerer RMI-tjeneste");
        this.ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
    }
    
}
