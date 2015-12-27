package spring;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Crawler implements SourceProvider {

    String baseUrl;

    public Crawler(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<HeaderInfo> getHeaderInfo() {
        try {
            Document mainPage = Jsoup.connect(baseUrl).get();

            Elements wikiPagesRefs = mainPage.getElementById("source-list").getElementsByTag("a");

            Stream<String> wikiPagesURLS = wikiPagesRefs.stream().map(e -> e.absUrl("href"));

            return wikiPagesURLS.flatMap(p -> pageHeaders(p)).collect(Collectors.toList());

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    private Stream<HeaderInfo> pageHeaders(String page) {
        try {
            Document doc = Jsoup.connect(page).timeout(10000).get();

            Element wiki = doc.getElementById("wiki-content");

            return wiki.children().stream()
                    .filter(node -> node.tag().getName().startsWith("h"))
                    .map(tag -> headerInfoFromTag(tag, doc.location()));

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static HeaderInfo headerInfoFromTag(Element tag, String baseLocation) throws NumberFormatException {
        return new HeaderInfo(Integer.parseInt(tag.tagName().substring(1)), tag.html(), baseLocation + "#" + tag.id());
    }

}