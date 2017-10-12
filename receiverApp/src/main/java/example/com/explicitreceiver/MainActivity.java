package example.com.explicitreceiver;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Handler scheduler;
    private ExplicitReceiver mExplicitReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scheduler = new Handler();
        mExplicitReceiver = new ExplicitReceiver();

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction("net.text.dev");
        String permission = "abc.def.PERMISSION";
        registerReceiver(mExplicitReceiver, filter, permission, scheduler);
        Log.w("register receiver", mExplicitReceiver.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mExplicitReceiver);
        Log.w("unregister receiver", mExplicitReceiver.getClass().getSimpleName());
    }
}
