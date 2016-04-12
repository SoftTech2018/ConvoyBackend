/*
* Simpel testklasse der tester AddressCompleter, og groft sagt simulerer en
* en søgning i et adressefelt.
* Den tager imod input fra consollen, og laver en query på inputtet.
* to \\ afslutter. whileløkken.
* Herefter printer den alle adresserne ud som er fundet,
* og nulstiller adresselisten.
*
* Kendte problemer:
* Ved få indtastninger er der rigtigt mange adresser, og dette er langsomt
*
*/
package convoybackend.dawaClient;


import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author KimDrewes
 */
public class AddressTest {
    private static  ArrayList<String> list;
    public static void main(String[] args ){
        AddressCompleter ac = new AddressCompleter();
        Scanner scan = new Scanner(System.in);
        System.out.println("DAWA SØGNING ER BEGYNDT");
        String input ;
        while(!(input = scan.nextLine()).equalsIgnoreCase("\\\\")){
            list = ac.getAddresses(input);
            if(list.size()<30){
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i));
                    
                }
            }else{
                System.out.println("listen er for lang");
            }
            list = null;
        }
        
        
        
    }
}
