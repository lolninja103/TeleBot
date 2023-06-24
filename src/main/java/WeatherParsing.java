import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherParsing {
    public static void main(String[] args) throws IOException {
        parsingNews();
    }

    public static List<String> parsingNews() throws IOException {
        String URL = "https://www.accuweather.com/en/world-weather";
        Document doc = Jsoup.connect(URL).maxBodySize(0).get();
        Elements df = doc.getElementsByClass("nearby-location weather-card");

        List<String> strings = new ArrayList<String>();
        for (Element i : df) {
            strings.add(i.text());
            System.out.println(i.text());
        }
        return strings;
    }
}