package org.uneca.developer.myfirstapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.telephony.SmsManager;


public class DisplayMessageActivity extends Activity {
    TextView txtView;
    String smsMessage;
    String phoneNumber;

    public void btn_close_click(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        smsMessage = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        phoneNumber= intent.getStringExtra(MyActivity.EXTRA_PHONE_NO);

        //TextView txtView = new TextView(this);
        txtView = new TextView(this);
        txtView.setTextSize(20);
        txtView.setText(smsMessage + "\nyour location info is: \n .....");

        setContentView(txtView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_sms_builtin:
                sendSMSUsingBuiltIn(item);
                return true;
            case R.id.action_sms_direct:
                sendSMSDirect(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendSMSDirect(MenuItem item) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, smsMessage, null, null);

        DisplayMessage(item);
    }

    private void sendSMSUsingBuiltIn(MenuItem item) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.putExtra("sms_body", smsMessage);
        smsIntent.putExtra("address", phoneNumber);
        smsIntent.setType("vnd.android-dir/mms-sms");

        startActivity(smsIntent);

        DisplayMessage(item);
    }

    private void DisplayMessage(MenuItem item) {

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

        dlgAlert.setMessage("Message is sent!\nThank you for using our service.");
        dlgAlert.setTitle("MyFirstApp");
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick (DialogInterface dialog,int whichButton){
                finish();
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}
