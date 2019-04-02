package com.example.sample;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class backgroundregister extends AsyncTask<String, Void, String> {

    AlertDialog dialog;
    Context context;
    public Boolean login = false;
    String result="";
    public backgroundregister(Context context) {
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
       ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Please wait...");
        pd.show();
        super.onPreExecute();
    }
    protected void onPostExecute(String s) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setMessage(s);
        dialog.show();
        if(s.contains("Registered Successfully!"))
        {
            Intent v = new Intent(context.getApplicationContext(),MainActivity.class);
            context.startActivity(v);

        }
    }
    @Override
    protected String doInBackground(String... voids) {

        String sname = voids[0];
        String sroll = voids[1];
        String spasswr = voids[2];
        String semail = voids[3];
        String sdeptt = voids[4];
        String syear = voids[5];

        String connstr = "https://outbred-targets.000webhostapp.com/newRegister.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(sname, "UTF-8")
                    + "&&" + URLEncoder.encode("rollno", "UTF-8") + "=" + URLEncoder.encode(sroll, "UTF-8")
                    + "&&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(spasswr, "UTF-8")
                    + "&&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(semail, "UTF-8")
                    + "&&" + URLEncoder.encode("dept", "UTF-8") + "=" + URLEncoder.encode(sdeptt, "UTF-8")
                    + "&&" + URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode(syear, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
            String line = "";
            while ((line = reader.readLine()) != null) {
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
