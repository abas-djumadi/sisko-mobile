package info.gorontaloit.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import info.gorontaloit.absensi.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://sisko.smartdataintegrasi.com/api/";

    Button btnMulai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMulai = findViewById(R.id.btnMulai);
        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
