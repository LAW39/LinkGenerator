package code;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLAndLinksClass {
    String url;
    String html;
    String regEx = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    List<String> links = new ArrayList<>();

    public URLAndLinksClass(String url) {
        this.url = url;
    }

    public void downloadHTML() throws IOException {
        html = Jsoup.connect(url).get().html();
    }

    public String getUrl() {
        return url;
    }

    public void generateMatches() {
        Matcher m = Pattern.compile(regEx)
                .matcher(html);
        while (m.find()) {
            links.add(m.group());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String link: links) {
            sb.append(url);
            sb.append(",");
            sb.append(link);
            sb.append("\n");

        }

        return sb.toString();

    }
}
