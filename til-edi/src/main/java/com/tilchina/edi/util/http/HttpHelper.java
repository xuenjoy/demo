package com.tilchina.edi.util.http;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
public class HttpHelper {
    private static String UTF8 = "UTF-8";
    private static RequestConfig requestConfig;

    public static String post(Map<String,String> header,Map<String,String> params,String url) throws Exception{
        HttpPost post = null;
        post = new HttpPost(url);
        if(header!=null){
            for(String key:header.keySet()){
                post.addHeader(key, header.get(key));
            }
        }
        if(params!=null){
            List<BasicNameValuePair> list = new LinkedList<BasicNameValuePair>();
            post.setConfig(getRequestConfig());
            for(String key:params.keySet()){
                list.add(new BasicNameValuePair(key,params.get(key)));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,UTF8);
            post.setEntity(entity);
        }
        return HttpClientHelper.getHttpClient().execute(post);
    }
    public static String post(Map<String,String> header,String jsonObject,String url) throws Exception{
        HttpPost post = null;
        post = new HttpPost(url);
        if(header!=null){
            for(String key:header.keySet()){
                post.addHeader(key, header.get(key));
            }
        }
        if(jsonObject.isEmpty()){
            throw new Exception("jsonObject不能为空！");
        }
        HttpEntity entity = new StringEntity(jsonObject,"UTF-8");
        return HttpClientHelper.getHttpClient().execute(post);
    }
    public static String post(Map<String,String> params,String url) throws Exception{
        HttpPost post = null;
        post = new HttpPost(url);
        List<BasicNameValuePair> list = new LinkedList<BasicNameValuePair>();
        post.setConfig(getRequestConfig());
        for(String key:params.keySet()){
            list.add(new BasicNameValuePair(key,params.get(key)));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,UTF8);
        post.setEntity(entity);
        return HttpClientHelper.getHttpClient().execute(post);
    }

    public static String post(List<Map<String,String>> params, String url) throws Exception{
        HttpPost post = null;
        post = new HttpPost(url);
        List<BasicNameValuePair> list = new LinkedList<BasicNameValuePair>();
        post.setConfig(getRequestConfig());
        for(Map<String,String> map :params){
            for(String key:map.keySet()){
                list.add(new BasicNameValuePair(key,map.get(key)));
            }

        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,UTF8);
        post.setEntity(entity);
        return HttpClientHelper.getHttpClient().execute(post);
    }

    public static RequestConfig getRequestConfig(){
        if(requestConfig==null){
            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(20000)
                    .setConnectTimeout(20000).setSocketTimeout(20000).build();
        }
        return requestConfig;
    }


}
