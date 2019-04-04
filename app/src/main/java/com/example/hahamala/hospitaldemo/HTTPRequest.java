package com.example.hahamala.hospitaldemo;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest extends AsyncTask<Void, Void, Void> {
    public String mResult;

    @Override
    protected Void doInBackground(Void... arg0){
        try{
            HttpURLConnection mURLConnection;
            URL url = new URL("https://pan0222.panoulu.net/5gtn/iprotoxi/read/1/data/latest?ID=22");
            mURLConnection = (HttpURLConnection)
                    url.openConnection();
            InputStream in = new BufferedInputStream(
                    mURLConnection.getInputStream());
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = in.read()) != -1){
                b.append((char) ch);
            }
            mResult = new String(b);
            Log.d("ThisApp", "" + mResult);

        } catch (Exception e){}
        return null;
    }
    @Override
    protected void onPostExecute(Void v) {
        Log.d("ThisApp", "Gottem!");
        Log.d("ThisApp", "" + mResult);
    }
}
