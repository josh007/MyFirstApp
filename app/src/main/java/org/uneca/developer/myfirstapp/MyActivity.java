package org.uneca.developer.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MyActivity extends Activity {

    public final static String EXTRA_MESSAGE = "org.uneca.developer.myfirstapp.MESSAGE";
    public final static String EXTRA_PHONE_NO = "org.uneca.developer.myfirstapp.PHONE_NO";

    public void btn_send_click(View view) {
        // do something
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText edit_message = (EditText)findViewById(R.id.edit_message);
        EditText edit_phone = (EditText)findViewById(R.id.edit_phone);

        String msg = edit_message.getText().toString();
        String phone = edit_phone.getText().toString();

        intent.putExtra(EXTRA_MESSAGE,msg);
        intent.putExtra(EXTRA_PHONE_NO, phone);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                openSettings();
                return true;
            case R.id.action_search:
                openSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSettings() {

    }

    private void openSearch() {

    }
}
