/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convoybackend;

import convoybackend.soap.SoapInt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author ministeren
 */
public class TestClient {
    
    public static void main(String[] args) throws Exception {
        
        
//        URL urlSoap = new URL("http://localhost:9943/convoyjeneste?wsdl");
//        QName qnameSoap = new QName("http://soap.convoybackend/", "SoapImplService");
//        Service soapService = Service.create(urlSoap, qnameSoap);
//        SoapInt soapserv = soapService.getPort(SoapInt.class);
//        
//        System.out.println(soapserv.Hent("test"));
        
        /*
        Jons testkode
        */
        TestClient testClient = new TestClient();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", "99");
        params.put("userId", "1");
        params.put("title", "This is the title");
        params.put("body", "This is the body");
        String url = "http://localhost:8080";
        String url2 = "https://httpbin.org/post";
        String response = testClient.performPostCall(url2, params);
        String[] lines = response.split(",");
        System.out.println("**** Udskriver svar: *****");
        for (String line : lines) {
            System.out.println(line + ",");
        }
        System.out.println("**** Svar slut ****");
    }
    
    /**
     * Test af POST metode til REST server
     * @param requestURL Url til REST server
     * @param postDataParams
     * @return 
     */
     private String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first){
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
