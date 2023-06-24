
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class Parsing {


    public static List<String> parsingNews() throws IOException {
        String URL = "https://edition.cnn.com/2023/05/26/tech/dutch-watchdog-tesla-alleged-data-breach/index.html";
        Document doc = Jsoup.connect(URL).maxBodySize(0).get();
        Elements df = doc.getElementsByClass("paragraph inline-placeholder");

        List<String> strings = new ArrayList<>();

        for (Element i : df) {
           strings.add(i.text());
        }
        return strings;
    }

    public static String da() throws IOException {
        List<String> f = parsingNews();
        String string = "";
      int count=0;
        for (int i = 0; i < f.size(); i = i + 1) {

            string = string +"\n"+(i+1)+" "+ " \n " + f.get(i);
        }
        return string;
    }
}

