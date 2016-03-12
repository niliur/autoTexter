package gordon.getrekt;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    Button sendBtn;
    Button rapidBtn;
    EditText txtAmount;
    EditText txtMessage;
    final String phoneNo = "7786802883";
    boolean rapid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        txtAmount = (EditText) findViewById(R.id.editText);
        txtMessage = (EditText) findViewById(R.id.editText2);
        rapidBtn = (Button) findViewById(R.id.rapid);

        rapidBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        while(true) {
                            sendSMSMessage();

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int times = Integer.parseInt(txtAmount.getText().toString());
                for (int i = 0; i < times; i++) {
                    sendSMSMessage();
                }
            }
        });


    }
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String message = txtMessage.getText().toString();

            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, message, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}