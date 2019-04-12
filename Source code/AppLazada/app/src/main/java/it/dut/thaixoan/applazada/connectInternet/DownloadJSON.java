package it.dut.thaixoan.applazada.connectInternet;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadJSON extends AsyncTask<String, Void, String> {

    private String duongDan;
    private List<HashMap<String, String>> attribute;
    private Boolean isMethod = true;

    public DownloadJSON(String duongDan) {
        this.duongDan = duongDan;
        isMethod = false;
    }

    public DownloadJSON(String duongDan, List<HashMap<String, String>> attribute) {
        this.duongDan = duongDan;
        this.attribute = attribute;
        isMethod = true;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        try {
            URL url = new URL(duongDan);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (isMethod) {
                Log.d("POST", "doInBackground: ");
                data = methodPost(httpURLConnection);
            } else {
                Log.d("GET", "doInBackground: ");
                data = methodGet(httpURLConnection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String methodGet(HttpURLConnection httpURLConnection) {
        String data = "";
        try {
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder duLieu = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                duLieu.append(line);
            }
            data = duLieu.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String methodPost(HttpURLConnection httpURLConnection) {
        String data = "";
        String key = "";
        String value = "";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();
            int count = attribute.size();
            for (int i = 0; i < count; i++) {

                for (Map.Entry<String, String> values : attribute.get(i).entrySet()) {
                    key = values.getKey();
                    value = values.getValue();
                }
                builder.appendQueryParameter(key, value);
            }
            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            assert query != null;
            bufferedWriter.write(query);
            bufferedWriter.flush();
            bufferedWriter.close();

            outputStreamWriter.close();
            outputStream.close();
            data = methodGet(httpURLConnection);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
