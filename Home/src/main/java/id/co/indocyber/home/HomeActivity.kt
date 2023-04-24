package id.co.indocyber.home

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.co.indocyber.common.BaseActivity

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("testInt"," = ${intent.getStringExtra("ExtraInt")}")
    }
}