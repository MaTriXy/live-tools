package iammert.com.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.raqun.live.core.LiveResult
import com.raqun.live.connection.ConnectionLiveData
import com.raqun.live.connection.ConnectionType

class LiveConnectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ConnectionLiveData(this).observe(this, Observer {
            when (it) {
                is LiveResult.LiveValue<ConnectionType> -> handleConnectionType(it.value)
                is LiveResult.PermissionRequired -> handlePermissions(it.requiredPermissions)
            }
        })
    }

    private fun handleConnectionType(connectionType: ConnectionType?) {
        when (connectionType) {
            ConnectionType.WIFI -> alert("You have a wifi connection!")
            ConnectionType.MOBILE -> alert("You're connected over mobile!")
            ConnectionType.UNAVAILABLE -> alert("Upps.. Something went wrong. We cannot define your connection!")
        }
    }

    private fun handlePermissions(permissions: Array<String>) {
        // you need to handle permissions
    }
}