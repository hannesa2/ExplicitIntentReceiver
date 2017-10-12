package example.com.sender;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String RECEIVER_PERMISSION = "abc.def.PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        Button btn = findViewById(R.id.buttonExplicitReceiver);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendProximity(view, "example.com.explicitreceiver", "refresh");
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendProximity(view, "example.com.explicitreceiver", "refresh");
            }
        });
    }

    // adb shell am broadcast -a net.text.dev -n example.com.explicitreceiver/.ExplicitReceiver -c android.intent.category.DEFAULT   -p example.com.explicitreceiver -e abc def
    private void sendProximity(View view, String packageName, String action) {
        Intent intentBack = new Intent();
        intentBack.setAction("net.text.dev");
        intentBack.setPackage(packageName);
//        intentBack.setClass(this, ExplicitReceiver.class);
        intentBack.putExtra("command", "reset");
        intentBack.putExtra("data", action);
//        intentBack.setComponent(
//                new ComponentName(packageName, packageName + ".MainActivity"));
        intentBack.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES); // this starts the app, if not running

        sendBroadcast(intentBack, RECEIVER_PERMISSION);
        Log.i("sendBroadcast", packageName + " " + intentBack.getExtras() + " with permission '" + RECEIVER_PERMISSION + "'");
        Snackbar.make(view, "Send broadcast to `" + packageName + "` " + action , Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
