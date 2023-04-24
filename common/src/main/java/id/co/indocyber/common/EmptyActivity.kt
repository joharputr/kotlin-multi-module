package id.co.indocyber.common

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import id.co.indocyber.common.databinding.ActivityEmptyBinding

class EmptyActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmptyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmptyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            finish()
        }, 3000)

    }
}