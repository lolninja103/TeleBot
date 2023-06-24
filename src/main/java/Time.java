import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Time {
    public static void main(String[] args) throws IOException {
       String first = function("EUR", "1000000");
        System.out.println(first);





    }

    public static String function(String currency, String sumn) throws IOException {

        String url_str = "https://v6.exchangerate-api.com/v6/cf24fb622f6685ab83d850ec/latest/" + currency.toUpperCase();

        // Making Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        JsonElement req_result = jsonobj.get("conversion_rates");
        String map[] = req_result.toString().split(",");
        for (int i = 0; i < map.length; i = i + 1) {

            map[i] = map[i].replaceAll("\"", "");
            map[i] = map[i].replaceAll("}", "");
            map[i] = map[i].replaceAll("\\{", "");

        }
        HashMap<String, BigDecimal> hashMap = new HashMap<String, BigDecimal>();
        BigDecimal amount = new BigDecimal(sumn);
        for (int i = 0; i < map.length; i = i + 1) {
            String s[] = map[i].split(":");
            BigDecimal bigDecimal = new BigDecimal(s[1]);
            BigDecimal bigDecimal1=new BigDecimal(0);
            bigDecimal1 = amount.multiply(bigDecimal);

            if (check(s[0])) {
                hashMap.put(s[0], bigDecimal1);
            }
        }
    String finall="";
     for (Map.Entry<String,BigDecimal>entry: hashMap.entrySet()){
         finall=finall+"\n"+entry;

     }
        finall=finall.replaceAll("\\{","");
        finall=finall.replaceAll("}","");
     return finall;
    }

    public static boolean check(String currency) {
        NewCurrencyEnum g[] = NewCurrencyEnum.values();
        List<String> total = new ArrayList<>();
        for (int i = 0; i < NewCurrencyEnum.values().length; i = i + 1) {
            String timing = String.valueOf(g[i]);
            total.add(timing);
        }
        for (int i = 0; i < total.size(); i = i + 1) {
            if (total.get(i).equals(currency.toUpperCase())) {

                return true;
            }
        }
        return false;
    }
}
