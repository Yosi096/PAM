package login.splash.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.input.InputManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText)findViewById(R.id.bookInput);
        mTitleText = (TextView)findViewById(R.id.titleText);
        mAuthorText = (TextView)findViewById(R.id.authorText);
    }

    public void searchBooks (View view) {
        //Mengambil string pada text yang telah diketik pada text field
        String queryString = mBookInput.getText().toString();

        // Menyembunyikan keyboard ketika telah di push
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        // Mengecek status koneksi internet
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //Kondisi jika internet lancar dan text field tidak kosong
        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0){
            new FetchBook(mTitleText, mAuthorText, mBookInput).execute(queryString);
        }

        //Kondisi jika internet tidak ada atau text field kosong
        else {
            if (queryString.length()==0){
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            }else {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }
}
