import java.io.*;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    LinkedList<URLDepthPair> siteList;
    int depth;
    Pattern regHTTP;

    public LinkedList <URLDepthPair> work(URLDepthPair urlDepthPair,int port) throws IOException{
        try {
            socket = new Socket(urlDepthPair.getUrl(), port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            depth = urlDepthPair.depth;
            siteList = new LinkedList<URLDepthPair>();
            regHTTP = Pattern.compile("(http:\\/\\/[\\w\\-\\.!~?&=+\\*'(),\\/\\#\\:]+)((?!\\<\\/\\w\\>))*?");
        }
        catch (Exception exc){
            System.out.println(exc);
            return new LinkedList<URLDepthPair>();
        }
        //end
        //connection
        printWriter.println("GET / HTTP/1.1");
        printWriter.println("Host: "+urlDepthPair.getUrl()+":"+port);
        printWriter.println("Connection: Close");
        printWriter.println();
        //end
        try{
            String line;
            while ((line=bufferedReader.readLine())!=null) {
                while(line.contains("<a")){
                    while (line.indexOf(">", line.indexOf("<a"))==-1) line+=bufferedReader.readLine();

                    String http = line.substring(line.indexOf("<a"),line.indexOf(">", line.indexOf("<a")));
                    if (http.contains("http://")){
                        Matcher matcher = regHTTP.matcher(http);
                        matcher.find();
                        String url = matcher.group();
                        siteList.add(new URLDepthPair(url,depth+1));
                    }
                    line=line.replace(http,"");
                }
            }
        }
        catch (IOException except){
            System.out.println(except);
        }
        socket.close();
        return siteList;
    }
}


