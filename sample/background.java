package com.example.sample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;

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

class background extends AsyncTask<String, Void,String> {
    ProgressDialog pd;
    AlertDialog dialog;
    Context context;
    public Boolean login = false;

    public background(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setMessage("Please wait...");
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setMessage(s);
        dialog.show();
        if(s.contains("Welcome to academic planar!"))
        {

            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(), Welcome.class);
            context.startActivity(intent_name);
        }
    }

    @Override
    protected String doInBackground(String... voids) {

        String result = "";
        String str_rollno = voids[0];
        String str_passw = voids[1];

        String connstr = "https://outbred-targets.000webhostapp.com/newLogin.php";
            try {
                URL url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(str_rollno, "UTF-8")
                        + "&&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(str_passw, "UTF-8");
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

