package it.dut.thaixoan.applazada.connectInternet;

import android.os.AsyncTask;
import java.util.HashMap;
import java.util.List;

public class DownloadJSON extends AsyncTask<String, Void, String> {

    private String duongDan;
    private List<HashMap<String, String>> attribute;

    public DownloadJSON(String duongDan) {
        this.duongDan = duongDan;
    }

    public DownloadJSON(String duongDan, List<HashMap<String, String>> attribute) {
        this.duongDan = duongDan;
        this.attribute = attribute;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
