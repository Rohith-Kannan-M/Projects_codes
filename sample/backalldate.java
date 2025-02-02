package com.example.sample;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class backalldate extends AsyncTask<String, Void,String> {
    AlertDialog dialog;
    Context context;
    public backalldate(Context context)
    {
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Please wait...");
        pd.show();
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Searched Date's Activity");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        //super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... voids) {
        String result="";
        String sdate=voids[0];

        String connstr = "https://outbred-targets.000webhostapp.com/newIndex.php";

        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("adate","UTF-8")+"="+URLEncoder.encode(sdate,"UTF-8");
            //+"&&"+URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line ="";
            while ((line = reader.readLine()) != null)
            {
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }


        return result;
    }
}
