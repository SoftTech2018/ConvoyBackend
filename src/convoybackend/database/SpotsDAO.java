/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package convoybackend.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klassens formål: At kommunikere med databasen,
 * oversætte ResultSet til Java objekter og Java metodekald til SQL statements
 * @author Jon
 */
public class SpotsDAO {
    
    private final Connecter con;
    
    public SpotsDAO(Connecter con){
        this.con = con;
    }
    
    public ArrayList<Spot> getSpots() throws SQLException{
        ArrayList<Spot> spots = new ArrayList<>();
        
        ResultSet rs = con.doQuery("SELECT * from " + con.tabelNavn);
        while (rs.next()) {
            spots.add(new Spot());
//            UserDTO(Integer.toString(rs.getInt("opr_id")), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getBoolean("admin"), rs.getBoolean("farmaceut"), rs.getBoolean("varkforer"), rs.getBoolean("operatoer"))
        }     
        return spots;
    }
    
    public boolean createSpot(Spot spot) throws SQLException{
        String cmd = "";
        if(con.doUpdate(cmd) == 0 ){
            return false;
        }
        return true;
    }
    
    public boolean updateSpot(Spot spot) throws SQLException{
        String cmd = "";
        if (con.doUpdate(cmd) ==0 ){
            return false;
        }
        return true;
    }
    
    
        public boolean deleteSpot(Spot spot) throws SQLException{
        String cmd = "";
        if (con.doUpdate(cmd) ==0 ){
            return false;
        }
        return true;
    }
    
}
