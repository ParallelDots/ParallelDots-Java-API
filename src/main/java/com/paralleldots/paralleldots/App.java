/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paralleldots.paralleldots;

import java.io.File;
import java.io.FileOutputStream;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.activation.MimetypesFileTypeMap;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author vaibh91
 */

public class App{
    private String api_key;
    private String host="https://apis.paralleldots.com/v3/";

    public App(String api_key){
        this.api_key = api_key;
        try{
            setUpCert("apis.paralleldots.com");
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    public void setUpCert(String hostname) throws Exception{
        SSLSocketFactory factory = HttpsURLConnection.getDefaultSSLSocketFactory();

        SSLSocket socket = (SSLSocket) factory.createSocket(hostname, 443);
        try {
            socket.startHandshake();
            socket.close();
            //System.out.println("No errors, certificate is already trusted");
            return;
        } catch (SSLException e) {
            //System.out.println("cert likely not found in keystore, will pull cert...");
        }


        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] password = "changeit".toCharArray();
        ks.load(null, password);

        SSLContext context = SSLContext.getInstance("TLS");
        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[]{tm}, null);
        factory = context.getSocketFactory();

        socket = (SSLSocket) factory.createSocket(hostname, 443);
        try {
            socket.startHandshake();
        } catch (SSLException e) {
            //we should get to here
        }
        X509Certificate[] chain = tm.chain;
        if (chain == null) {
            System.out.println("Could not obtain server certificate chain");
            return;
        }

        X509Certificate cert = chain[0];
        String alias = hostname;
        ks.setCertificateEntry(alias, cert);

        System.out.println("saving file paralleldotscacerts to working dir");
        //System.out.println("copy this file to your jre/lib/security folder");
        FileOutputStream fos = new FileOutputStream("paralleldotscacerts");
        ks.store(fos, password);
        fos.close();
    }
    private static class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager tm;
        private X509Certificate[] chain;

        SavingTrustManager(X509TrustManager tm) {
            this.tm = tm;
        }

        public X509Certificate[] getAcceptedIssuers() {

        return new X509Certificate[0];  
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            throw new UnsupportedOperationException();
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            this.chain = chain;
            tm.checkServerTrusted(chain, authType);
        }
    }
    
    public static void main(String[] args) throws Exception {                
        System.out.println("ParallelDots API");
    }
    
    public String abuse(String text) throws Exception {
        if(this.api_key!=null){
            String url = host + "abuse";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }	
    }
    
    public String abuse_batch(JSONArray text_list) throws Exception {
        if(this.api_key!=null){
            String url = host + "abuse_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text_list)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }	
    }

    public String custom_classifier(String text, JSONObject category) throws Exception {
        if(this.api_key!=null){
            String url = host + "custom_classifier";
            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, "");
            Request request = new Request.Builder()
                .url(url+"?api_key="+this.api_key+"&text="+text+"&category="+category)
                .post(body)
                .build();
            Response response = client.newCall(request).execute();
            ResponseBody respBody = response.body();
            return respBody.string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }
    
    public String emotion(String text) throws Exception {
        if(this.api_key!=null){
            String url = host + "emotion";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text+"&lang_code=en")
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }	
    }
    
    public String emotion(String text, String lang_code) throws Exception {
        if(this.api_key!=null){
            String url = host + "emotion";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text+"&lang_code="+lang_code)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }	
    }
    
    public String emotion_batch(JSONArray text_list) throws Exception {
        if(this.api_key!=null){
            String url = host + "emotion_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text_list+"&lang_code=en")
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }	
    }
    
    public String emotion_batch(JSONArray text_list, String lang_code) throws Exception {
        if(this.api_key!=null){
            String url = host + "emotion_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text_list+"&lang_code="+lang_code)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }	
    }
    
    public String facial_emotion(String path) throws Exception{
        if(this.api_key!=null){
            try{
                String url = host + "facial_emotion";
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build();
                File file = new File(path);
                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                String mimeType = mimeTypesMap.getContentType(file);
                MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
                RequestBody formBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", file.getName(),RequestBody.create(MediaType.parse(mimeType), file)).build();
                Request request = new Request.Builder()
                    .url(url+"?api_key="+this.api_key)
                    .post(formBody)
                    .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .addHeader("cache-control", "no-cache")
                    .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch(SocketTimeoutException e){
                e.printStackTrace();
                return "{ \"Error\": \"Request Timed Out\" }";
            }
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }

    public String facial_emotion_url(String url_image) throws Exception{
        if(this.api_key!=null){
            try{
                String url = host + "facial_emotion";
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build();
                MediaType mediatype = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediatype, "");
                Request request = new Request.Builder()
                  .url(url+"?api_key="+this.api_key+"&url="+url_image)
                  .post(body)
                  .addHeader("cache-control", "no-cache")
                  .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch(SocketTimeoutException e){
                e.printStackTrace();
                return "{ \"Error\": \"Request Timed Out\" }";
            }
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }

    public String intent(String text) throws Exception {
        if(this.api_key!=null){
            String url = host + "intent";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String intent_batch(JSONArray text_list) throws Exception {
        if(this.api_key!=null){
            String url = host + "intent_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text_list)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }

    public String keywords(String text) throws Exception {
        if(this.api_key!=null){
            String url = host + "keywords";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }		
    }
    
    public String keywords_batch(JSONArray text_list) throws Exception {
        if(this.api_key!=null){
            String url = host + "keywords_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text_list)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }		
    }
    
    public String language_detection(String text) throws Exception{
        if(this.api_key!=null){
            String url = host + "language_detection";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }
    
    public String language_detection_batch(JSONArray text_list) throws Exception{
        if(this.api_key!=null){
            String url = host + "language_detection_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text_list)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }
    
    public String multilang_keywords(String text, String lang_code) throws Exception{
        if(this.api_key!=null){
            String url = host + "multilang_keywords";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text+"&lang_code="+lang_code)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }
    
    public String ner(String text) throws Exception {
        if(this.api_key!=null){
            String url = host + "ner";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }

    public String ner_batch(JSONArray text_list) throws Exception {
        if(this.api_key!=null){
            String url = host + "ner_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text_list)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String nsfw(String path) throws Exception{
        if(this.api_key!=null){
            try{
                String url = host + "nsfw";
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build();
                File file = new File(path);
                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                String mimeType = mimeTypesMap.getContentType(file);
                MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
                RequestBody formBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", file.getName(),RequestBody.create(MediaType.parse(mimeType), file)).build();
                Request request = new Request.Builder()
                    .url(url+"?api_key="+this.api_key)
                    .post(formBody)
                    .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .addHeader("cache-control", "no-cache")
                    .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch(SocketTimeoutException e){
                e.printStackTrace();
                return "{ \"Error\": \"Request Timed Out\" }";
            }
            
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String nsfw_url(String url_image) throws Exception{
        if(this.api_key!=null){
            try{
                String url = host + "nsfw";
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build();
                MediaType mediatype = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediatype, "");
                Request request = new Request.Builder()
                  .url(url+"?api_key="+this.api_key+"&url="+url_image)
                  .post(body)
                  .addHeader("cache-control", "no-cache")
                  .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch(SocketTimeoutException e){
                e.printStackTrace();
                return "{ \"Error\": \"Request Timed Out\" }";
            }            
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String object_recognizer(String path) throws Exception{
        if(this.api_key!=null){
            try{
                String url = host + "object_recognizer";
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build();
                File file = new File(path);
                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                String mimeType = mimeTypesMap.getContentType(file);
                MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
                RequestBody formBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", file.getName(),RequestBody.create(MediaType.parse(mimeType), file)).build();
                Request request = new Request.Builder()
                    .url(url+"?api_key="+this.api_key)
                    .post(formBody)
                    .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .addHeader("cache-control", "no-cache")
                    .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch(SocketTimeoutException e){
                e.printStackTrace();
                return "{ \"Error\": \"Request Timed Out\" }";
            }
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }

    public String object_recognizer_url(String url_image) throws Exception{
        if(this.api_key!=null){
            try{
                String url = host + "object_recognizer";
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build();
                MediaType mediatype = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediatype, "");
                Request request = new Request.Builder()
                  .url(url+"?api_key="+this.api_key+"&url="+url_image)
                  .post(body)
                  .addHeader("cache-control", "no-cache")
                  .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch(SocketTimeoutException e){
                e.printStackTrace();
                return "{ \"Error\": \"Request Timed Out\" }";
            }
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }

    public String phrase_extractor(String text) throws Exception {
        if(this.api_key!=null){
            String url = host + "phrase_extractor";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String phrase_extractor_batch(JSONArray text_list) throws Exception {
        if(this.api_key!=null){
            String url = host + "phrase_extractor_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text_list)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String popularity(String path) throws Exception{
        if(this.api_key!=null){
            try{
                String url = host + "popularity";
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build();
                File file = new File(path);
                MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                String mimeType = mimeTypesMap.getContentType(file);
                MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
                RequestBody formBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", file.getName(),RequestBody.create(MediaType.parse(mimeType), file)).build();
                Request request = new Request.Builder()
                    .url(url+"?api_key="+this.api_key)
                    .post(formBody)
                    .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .addHeader("cache-control", "no-cache")
                    .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch(SocketTimeoutException e){
                e.printStackTrace();
                return "{ \"Error\": \"Request Timed Out\" }";
            }            
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String popularity_url(String url_image) throws Exception{
        if(this.api_key!=null){
            try{
                String url = host + "popularity";
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .build();
                MediaType mediatype = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediatype, "");
                Request request = new Request.Builder()
                  .url(url+"?api_key="+this.api_key+"&url="+url_image)
                  .post(body)
                  .addHeader("cache-control", "no-cache")
                  .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch(SocketTimeoutException e){
                e.printStackTrace();
                return "{ \"Error\": \"Request Timed Out\" }";
            }
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }

    public String sentiment(String text) throws Exception {
        if(this.api_key!=null){
            String url = host + "sentiment";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text=" + text + "&lang_code=en")
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String sentiment(String text, String lang_code) throws Exception {
        if(this.api_key!=null){
            String url = host + "sentiment";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text=" + text + "&lang_code=" + lang_code)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }

    public String sentiment_batch(JSONArray text_list) throws Exception {
        if(this.api_key!=null){
            String url = host + "sentiment_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text=" + text_list + "&lang_code=en")
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String sentiment_batch(JSONArray text_list, String lang_code) throws Exception {
        if(this.api_key!=null){
            String url = host + "sentiment_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text=" + text_list + "&lang_code=" + lang_code)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
    
    public String similarity(String text_1, String text_2) throws Exception {
        if(this.api_key!=null){
            String url = host + "similarity";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text_1=" + text_1 + "&text_2=" + text_2)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }

    public String taxonomy(String text) throws Exception {
        if(this.api_key!=null){
            String url = host + "taxonomy";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }	
    }

    public String taxonomy_batch(JSONArray text_list) throws Exception {
        if(this.api_key!=null){
            String url = host + "taxonomy_batch";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text_list)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }    

    public String text_parser(String text) throws Exception {
        if(this.api_key!=null){
            String url = host + "text_parser";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key="+this.api_key+"&text="+text)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }

    public String usage() throws Exception {
        if(this.api_key!=null){
            String url = "http://apis.paralleldots.com/usage";
            OkHttpClient client = new OkHttpClient();
            MediaType mediatype = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediatype, "");
            Request request = new Request.Builder()
              .url(url+"?api_key=" + this.api_key)
              .post(body)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }            
    }
}
