package id.co.indocyber.common

import android.util.Log
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {

    open fun hello() {
        Log.d("hello", "3")
    }
}