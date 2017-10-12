package example.com.explicitreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class ExplicitReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received !", Toast.LENGTH_SHORT).show();
        Log.d("ExplicitReceiver", intent.getExtras().toString());
    }
}
