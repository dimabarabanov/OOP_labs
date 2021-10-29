import java.net.*;

public class URLDepthPair {
    String url;
    int depth;

    URLDepthPair(String url, int depth){
        this.url=url;
        this.depth=depth;
    }

    public boolean isURL(){
        return url.matches("\\b(http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    public String getUrl() {
        try {
            URL url = new URL(this.url);
            return url.getHost();
        }
        catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e.getMessage());
            return null;
        }
    }

    public int getDepth() {
        return depth;
    }
    public String toString(){
        return url+" ["+depth+"]";
    }
}
