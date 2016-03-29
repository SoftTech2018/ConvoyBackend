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
    
    /**
     * Henter alle spots i databasen
     * @return Alle spots der findes i DB
     * @throws SQLException
     */
    public ArrayList<Spot> getSpots() throws SQLException{
        ArrayList<Spot> spots = new ArrayList<>();
        
        ResultSet rs = con.doQuery("SELECT * from " + con.tabelNavn);
        while (rs.next()) {
            spots.add(new Spot());
//            UserDTO(Integer.toString(rs.getInt("opr_id")), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getBoolean("admin"), rs.getBoolean("farmaceut"), rs.getBoolean("varkforer"), rs.getBoolean("operatoer"))
        }
        return spots;
    }
    
    /**
     * Opret et givent spot
     * @param spot Det spot der skal oprettes. Spottets ID skal være null.
     * @return True hvis spottet blev oprettet. False hvis det ikke blev oprettet.
     * @throws SQLException
     */
    public boolean createSpot(Spot spot) throws SQLException{
        String cmd = "";
        if(con.doUpdate(cmd) == 0 ){
            return false;
        }
        return true;
    }
    
    /**
     * Opdaterer et givent spot
     * @param spot Det spot der skal opdateres. Spottets ID bestemmer hvilket spot der opdateres i DB
     * @return True hvis et spot blev rettet. False hvis intet blev rettet
     * @throws SQLException
     */
    public boolean updateSpot(Spot spot) throws SQLException{
        String cmd = "";
        if (con.doUpdate(cmd) ==0 ){
            return false;
        }
        return true;
    }
    
    /**
     * Slet et givent spot
     * @param spot Det spot der skal slettes. Spottets ID bestemmer hvilket spot der slettes i DB
     * @return True hvis et spot blev slettet. False hvis intet blev slettet
     * @throws SQLException
     */
    public boolean deleteSpot(Spot spot) throws SQLException{
        String cmd = "";
        if (con.doUpdate(cmd) ==0 ){
            return false;
        }
        return true;
    }
    
    /**
     * Hent alle spots der er opdateret siden den defineret tid
     * @param time Tiden siden 01.01.1970 i ms
     * @return
     * @throws SQLException 
     */
    public ArrayList<Spot> getUpdatedSpots(long time) throws SQLException{
        ArrayList<Spot> spots = new ArrayList<>();
        
        ResultSet rs = con.doQuery("SELECT * from " + con.tabelNavn + " WHERE lastUpdated >= " + time);
        while (rs.next()) {
            spots.add(new Spot());
//            UserDTO(Integer.toString(rs.getInt("opr_id")), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getBoolean("admin"), rs.getBoolean("farmaceut"), rs.getBoolean("varkforer"), rs.getBoolean("operatoer"))
        }
        return spots;
    }
}
