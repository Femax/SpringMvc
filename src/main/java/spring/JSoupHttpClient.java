package spring;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JSoupHttpClient implements HttpClient{

    @Override
    public Document load(String url) throws IOException {

        return Jsoup.connect(url).timeout(timeout).get();

    }
    private int timeout = 10000;

}