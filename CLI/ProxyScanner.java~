
package proxyscanner;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sushil
 */

class ScannerThread implements Runnable{
    String proxyAddr;
    URL myURL;
    Proxy proxy;
    HttpURLConnection con;
    int code;
    ScannerThread(String S,String url){
        proxyAddr = S;
        try{
            myURL = new URL(url);
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddr, 3128));
            con = (HttpURLConnection) myURL.openConnection(proxy);
            con.setConnectTimeout(3000);
            con.setReadTimeout(6000);
        }
        catch(Exception e){
            System.out.println("Invalid url");
        }
        }
    @Override
    public void run(){  
            try{
                con.setRequestMethod("GET");
                con.connect();
                code = con.getResponseCode();
                if(code == 200)
                    System.out.println(proxyAddr);
            }
            catch(Exception e){  
                
            }
        }
    
}


public class ProxyScanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        String Base = "172.30.0.";
        ArrayList<String>proxyList = new ArrayList();
        Scanner S = new Scanner(System.in);
        System.out.println("Enter URL - https://");
        String URL = "http://" + S.next();
        if(URL.contains("www."))
            URL = "http://" + URL.substring(4, URL.length());
       for(int i = 1;i<100;i++){
           String Str = Base + i;
            proxyList.add(Str);
           new Thread(new ScannerThread(proxyList.get(i - 1),URL)).start();
       }
    }
    
}
