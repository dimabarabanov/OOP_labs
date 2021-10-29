import java.io.*;
import java.util. *;
public class Scaner {
    static LinkedList<URLDepthPair> site;

    public static void main(String[] args) throws IOException {
        args = new String[] {"http://google.com", "1", };
        site = new LinkedList <URLDepthPair>();
        Crawler crawler = new Crawler();

        URLDepthPair startUrl = new URLDepthPair(args[0],0);

        if (!startUrl.isURL() || args[1].matches("\\D + ")) {
            System.out.println("java Crawler <" + args[0] + "><" + args[1] + "> ");
            return;
        }

        site.add(startUrl);
        int i=0;
        while(i<site.size()){
            LinkedList<URLDepthPair> siteList = crawler.work(site.get(i),80);
            if (siteList.size()>0)
                site.addAll(i+1,siteList);
            if (site.get(i).depth>=Integer.parseInt(args[1])-1){
                if (siteList.size()==0)
                    i++;
                else
                    i+=siteList.size()+1;
            }
            else
                i++;
        }
        for(URLDepthPair url: site){
            System.out.println(url);
        }
    }
}
