/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyscan;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
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
                if(code == 200){
                    ProxyScan.listData.add(proxyAddr);
                    ProxyScan.myList.setListData(ProxyScan.listData);
                }
                    //ProxyScan.validProxy.add(proxyAddr);
                    //ProxyScan.pList.add(proxyAddr, null);
            }
            catch(Exception e){  
                
            }
            finally{
                ProxyScan.updateThread();
            }
        }
    
}