package com.neo.chatapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        mInputMessage = (EditText) findViewById(R.id.input_message);
        mSendMessage = (Button) findViewById(R.id.send_message);
        mMessageLog = (LinearLayout) findViewById(R.id.message_log);
        mCpuMessage = (TextView) findViewById(R.id.cpu_message);

        mInputMessage.setText("hoge");
        mSendMessage.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onClick(View view) {
        if (view.equals(mSendMessage)) {
            //Toast.makeText(this,"onclick()",Toast.LENGTH_SHORT).show();
            String inputText = mInputMessage.getText().toString();
            String answer;

            TextView userMessage = new TextView(this);

            int messageColor = getResources().getColor(R.color.message_color);
            userMessage.setTextColor(messageColor);

            userMessage.setBackgroundResource(R.drawable.user_message);
            userMessage.setText(inputText);
            //userMessage.setGravity(Gravity.END);

            LinearLayout.LayoutParams userMessageLayout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            userMessageLayout.gravity = Gravity.END;

            final int marginSize = getResources().getDimensionPixelSize(R.dimen.message_margin);
            userMessageLayout.setMargins(0, marginSize, 0, marginSize);
            mMessageLog.addView(userMessage, 0, userMessageLayout);


            if (inputText.contains("안녕")) {
                answer = "안녕하세요";
            } else if (inputText.contains("피곤")) {
                answer = "고생했어요";
            } else if (inputText.contains("운세")) {
                double random = Math.random() * 5d;

                if (random < 1d) {
                    answer = "몹시 나쁨";
                } else if (random < 2d) {
                    answer = "나쁨";
                } else if (random < 3d) {
                    answer = "보통";
                } else if (random < 4d) {
                    answer = "행운";
                } else if (random < 5d) {
                    answer = "대박";
                } else {
                    answer = "운수대통";
                }
            } else if (inputText.contains("시간")) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                answer = String.format("현재 시간은 %d시 %d분 %d초 입니다 ", hour, minute, second);
            } else {
                answer = "그거 괜찮네요";
            }

            final TextView cpuMessage = new TextView(this);

            cpuMessage.setTextColor(messageColor);
            cpuMessage.setBackgroundResource(R.drawable.cpu_message);
            cpuMessage.setText(answer);
            cpuMessage.setGravity(Gravity.START);

            mInputMessage.setText("");
            TranslateAnimation userMessageAnimation = new TranslateAnimation(mMessageLog.getWidth(), 0, 0, 0);
            userMessageAnimation.setDuration(1000);
            userMessageAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    LinearLayout.LayoutParams cpuMessageLayout = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    cpuMessageLayout.gravity = Gravity.START;
                    cpuMessageLayout.setMargins(marginSize, marginSize, marginSize, marginSize);

                    mMessageLog.addView(cpuMessage, 0, cpuMessageLayout);
                    TranslateAnimation cpuAnimation = new TranslateAnimation(-mMessageLog.getWidth(),0,0,0);
                    cpuAnimation.setDuration(1000);
                    mCpuMessage.setAnimation(cpuAnimation);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            userMessage.startAnimation(userMessageAnimation);




        }
    }


}
