package spring;

        import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.ResponseBody;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

        import static org.mockito.Mockito.mock;

@Controller
public class MainController {

    @RequestMapping(path = "/index")
    public String helloWorld() {
        return "index";
    }

    @RequestMapping(path = "/res")
   // @ResponseBody
    public String hello(Model m, @RequestParam(value = "Url", required = false) String Url,@RequestParam(value="Coi",required = false) Integer Coi) {
        System.out.println("Url=" + Url + " Coi=" + Coi);
        m.addAttribute("Url", Url);
        m.addAttribute("Coi", Coi);
        Crawler crawler = new Crawler(Url);

        List<HeaderInfo> headers = crawler.getHeaderInfo();
        List<HeaderInfo> resHeaders = new ArrayList<>();
        for (HeaderInfo header:headers){
            if(header.getLevel()==Coi)
                resHeaders.add(header);
        }
        m.addAttribute("HeaderInfo",resHeaders);
        return "res";
    }

}