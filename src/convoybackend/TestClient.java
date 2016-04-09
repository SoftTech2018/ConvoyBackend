/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convoybackend;

import convoybackend.soap.SoapInt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        String url3 = "https://httpbin.org/get";
        
        // Test af POST metode (Android)
        String response = testClient.performPostCall(url2, params);
        String[] lines = response.split(",");
        System.out.println("**** Udskriver svar: *****");
        for (String line : lines) {
            System.out.println(line + ",");
        }
        System.out.println("**** Svar slut ****");
        
        // Test af GET metode vha. Jersey Client
        String response2 = testClient.performGetCall(url3, System.currentTimeMillis());
        String[] lines2 = response2.split(",");
        System.out.println("**** Udskriver svar2: *****");
        for (String line : lines2) {
            System.out.println(line + ",");
        }
        System.out.println("**** Svar slut ****");
        
        // Test af GET metode til android
        String response3 = testClient.performGetCallAndroid(url3, System.currentTimeMillis());
        String[] lines3 = response3.split(",");
        System.out.println("**** Udskriver svar3 (Android): *****");
        for (String line : lines3) {
            System.out.println(line + ",");
        }
        System.out.println("**** Svar slut ****");
    }
    
    /**
     * Test af POST/PUT metode til REST server
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
    
    /**
     * Udfører et GET kald til en REST server
     * @param url URL til REST server
     * @param lastUpdated long - hvornår er spots sidst blevet opdateret for klienten?
     * @return 
     */
    private String performGetCall(String url, long lastUpdated){
        //Instantierer en client
        Client client = ClientBuilder.newClient();
        //Kalder GET - get() på target og accepterer JSON
        Response res = client.target(url).queryParam("lastUpdated", Long.toString(lastUpdated)).request().get();
        //Parse resultatet til en String
        String resultString = res.readEntity(String.class);
        return resultString;
    }
    
    private String performGetCallAndroid(String urlString, long lastUpdated){
        String data = "";
                InputStream iStream = null;
                HttpURLConnection urlConnection = null;
                try {
                    try {
                        URL url = new URL(urlString + "?lastUpdated=" + Long.toString(lastUpdated));
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.connect();
                        iStream = urlConnection.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(
                                iStream));
                        StringBuffer sb = new StringBuffer();
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        data = sb.toString();
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        iStream.close();
                        urlConnection.disconnect();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
                return data;
    }
}
