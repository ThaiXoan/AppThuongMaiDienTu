package it.dut.thaixoan.applazada.views.manHinhChao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import it.dut.thaixoan.applazada.R;
import it.dut.thaixoan.applazada.views.trangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(intent);
                }
            }
        });
        thread.start();
    }
}
