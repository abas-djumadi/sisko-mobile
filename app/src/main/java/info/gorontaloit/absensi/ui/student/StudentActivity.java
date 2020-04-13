package info.gorontaloit.absensi.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

import info.gorontaloit.absensi.R;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collpsing_toolbar);
        collapsingToolbarLayout.setTitle("Daftar Siswa");

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }
}
