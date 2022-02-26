import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.DocumentFragment;

public class Scraper{
    private Document doc;
    private String[] mainTopics;
    private String[] topicUrls;

    Scraper() throws IOException {
        doc = Jsoup.connect("https://www.bbc.com/news").timeout(6000).get();
        Elements body = doc.select("nav.nw-c-nav__wide");
        Elements list = body.select("li");
        mainTopics = new String[10];
        topicUrls = new String[10];
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && i < 11) {
                mainTopics[index++] = list.get(i).text();
            }
        }
        index = 0;
        list = body.select("a");
        topicUrls = new String[10];
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && i < 11) {
                topicUrls[index++] = list.get(i).attr("href");
            }
        }
    }

    public String[] getMainTopics() {
        return this.mainTopics;
    }

    public String[] getUrls() {
        return this.topicUrls;
    }
}