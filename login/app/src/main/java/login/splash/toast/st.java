package login.splash.toast;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class st extends AppCompatActivity {
    private int waktu = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st);

        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_LONG).show();
    }
}
