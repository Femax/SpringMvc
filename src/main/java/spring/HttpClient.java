package spring;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface HttpClient {

    Document load(String url) throws IOException;

}