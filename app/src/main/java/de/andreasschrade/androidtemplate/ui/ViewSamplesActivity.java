package de.andreasschrade.androidtemplate.ui;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.andreasschrade.androidtemplate.R;
import de.andreasschrade.androidtemplate.ui.base.BaseActivity;
import de.andreasschrade.androidtemplate.ui.quote.Global;

/**
 * Activity demonstrates some GUI functionalities from the Android support library.
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class ViewSamplesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_samples);
        ButterKnife.bind(this);
        setupToolbar();

        TextView t = (TextView)findViewById(R.id.sample_subhead);
        t.setText(Global.name);
        
        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dropStatus);

        String[] items = new String[] { "Pending", "Completed"};

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplication().getBaseContext(),"WHOA",Toast.LENGTH_SHORT).show();
                try {
                    EditText etUserInfoNewValue = (EditText)findViewById(R.id.sample_text_username);
                    String newValue = etUserInfoNewValue.getText().toString().trim();
                    URL reqURL = new URL("http://10.2.5.107/addchallenge/"); //the URL we will send the request to
                    HttpURLConnection request = (HttpURLConnection) (reqURL.openConnection());
                    Map<String,Object> params = new LinkedHashMap<String, Object>();
                    params.put("host_id",R.string.user_id);
                    Random r = new Random();
                    params.put("challenge_id", r.nextInt());
                    params.put("challenge_title", newValue);
                    etUserInfoNewValue = (EditText)findViewById(R.id.sample_text_email);
                    newValue = etUserInfoNewValue.getText().toString().trim();
                    params.put("challenge_summary", newValue);

                    etUserInfoNewValue = (EditText)findViewById(R.id.sample_text_description);
                    newValue = etUserInfoNewValue.getText().toString().trim();
                    params.put("challenge_description", newValue);

                    etUserInfoNewValue = (EditText)findViewById(R.id.sample_text_reward);
                    newValue = etUserInfoNewValue.getText().toString().trim();
                    params.put("challenge_reward", newValue);

                    Spinner InfoNewValue = (Spinner)findViewById(R.id.dropStatus);
                    newValue = InfoNewValue.getSelectedItem().toString().trim();
                    params.put("challenge_status", newValue);

                    StringBuilder postData = new StringBuilder();
                    for (Map.Entry<String,Object> param : params.entrySet()) {
                        if (postData.length() != 0) postData.append('&');
                        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                        postData.append('=');
                        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                    }
                    byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                    request.setDoOutput(false);
                    request.setRequestMethod("POST");
                    request.getOutputStream().write(postDataBytes);
                    BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
                    StringBuilder sb = new StringBuilder();

                    String line;
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                }catch (Exception e) {
                    Log.d("ERROR",e.toString());
                    e.printStackTrace();
                    Log.d("HELLO","EHHHHHHHHHHHH");

                }
            }
        });
    }

    @OnClick(R.id.fab)
    public void onFabClicked(View view) {
        Snackbar.make(view, "Hello Snackbar!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_samples;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
