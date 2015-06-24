package com.example.scrumboard.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.scrumboard.model.Member;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by j on 24.06.15.
 */
public class GetData {

    private String global_url = "https://amascrumboard.herokuapp.com/";
    private Gson gson;
    List<Member> members;

    public GetData() {
        gson = new Gson();
    }

    public void getAllMembers() {
        members = new ArrayList<>();
        final String url = global_url + "members/";
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return getJSONfromUrl(url);
            }

            @Override
            protected void onPostExecute(String jsonString) {
                Type listType = new TypeToken<List<Member>>(){}.getType();
                members = (List<Member>) gson.fromJson(jsonString, listType);
                for (Member m: members)
                    Log.i("gson",m.toString());
            }
        }.execute();
    }

    public void addMember(Member m) {
        final String url = global_url + "member/new/";
        System.out.println("adding new member: " + m);
        ;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", String.valueOf(m.getId()));
        hashMap.put("name", m.getName());
        hashMap.put("surname", m.getSurname());
        hashMap.put("mail", m.getMail());
        hashMap.put("password", m.getPassword());
        JSONObject jsonobj = new JSONObject();
        try {
            jsonobj.put("id", String.valueOf(m.getId()));
            jsonobj.put("name", m.getName());
            jsonobj.put("surname", m.getSurname());
            jsonobj.put("mail", m.getMail());
            jsonobj.put("password", m.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        post(url, hashMap);
    }

    public void post(final String urlString, final HashMap<String, String> hashMap) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                Log.i("AsyncTask", "POST in background");

                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                for(String key: hashMap.keySet()){
                    pairs.add(new BasicNameValuePair(key, hashMap.get(key)));
                }
                System.out.println(pairs);

                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(getPostDataString(hashMap));
                    writer.flush();
                    writer.close();
                    os.close();
                    int responseCode=conn.getResponseCode();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    private String getJSONfromUrl(String urlString) {
        System.out.println(urlString);
        InputStream is = null;
        String jsonStr = "";
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-length", "0");
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            reader.close();
            return sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
