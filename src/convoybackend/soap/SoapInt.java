/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convoybackend.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author ministeren
 */
@WebService
public interface SoapInt {
    
@WebMethod boolean Tilføj(Object spot);
@WebMethod boolean Slet(Object spot);
@WebMethod boolean Rediger(Object spot);
@WebMethod Object Hent(String dato);
@WebMethod Object Synkroniser();
@WebMethod Object Login(String user, String pass);
@WebMethod Object adrCheck(String adr);

}
