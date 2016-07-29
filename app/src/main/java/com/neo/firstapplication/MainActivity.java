package com.neo.firstapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);
        int a = 0;

        String s = calc(100);
        s += calc(1000);
        s += calc(-10000);

        textView.setText(s);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private String calc(int n) {
        int a = 0;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                a += i;
            }
        } else {
            for (int i = 1; i >= n ; i--) {
                a += i;
            }

        }

        String s = "1부터" + String.valueOf(n) + "을 더한 값은 " + String.valueOf(a) + "\n";
        return s;
    }


}
