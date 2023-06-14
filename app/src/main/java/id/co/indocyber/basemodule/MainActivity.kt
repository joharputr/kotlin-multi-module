package id.co.indocyber.basemodule

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import id.co.indocyber.basemodule.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listener: SplitInstallStateUpdatedListener
    var mySessionId = 0
    private val manager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.btnDownload.setOnClickListener {
            val request = SplitInstallRequest.newBuilder()
                .addModule("Home")
                .build()

            manager.startInstall(request).addOnSuccessListener { result ->
                if (result != null) {
                    mySessionId = result
                };
                Toast.makeText(
                    this@MainActivity,
                    "Module installation started",
                    Toast.LENGTH_SHORT
                ).show();
            }
                .addOnFailureListener {
                    when (SplitInstallException(it.hashCode()).errorCode) {
                        SplitInstallErrorCode.NETWORK_ERROR -> Toast.makeText(
                            this,
                            "Error Network",
                            Toast.LENGTH_SHORT
                        ).show()
                        SplitInstallErrorCode.ACCESS_DENIED -> Toast.makeText(
                            this,
                            "Access Denied",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        listener =
            SplitInstallStateUpdatedListener { state ->
                if (state.sessionId() == mySessionId) {
                    when (state.status()) {
                        SplitInstallSessionStatus.DOWNLOADING -> {
                            val size = state.totalBytesToDownload()
                            val downloaded = state.bytesDownloaded()
                            binding.statusDownload.setText(
                                String.format(
                                    Locale.getDefault(),
                                    "%d of %d bytes downloaded.", downloaded, size
                                )
                            )
                        }
                        SplitInstallSessionStatus.INSTALLING -> binding.statusDownload.setText("Installing feature")
                        SplitInstallSessionStatus.DOWNLOADED -> binding.statusDownload.setText("Download Complete")
                        SplitInstallSessionStatus.INSTALLED -> {
                            viewModel.changeStatusDownload(true)
                            binding.statusDownload.setText("Installed - Feature is ready")
                        }
                        SplitInstallSessionStatus.CANCELED -> binding.statusDownload.setText("Installation cancelled")
                        SplitInstallSessionStatus.PENDING -> binding.statusDownload.setText("Installation pending")
                        SplitInstallSessionStatus.FAILED -> binding.statusDownload.setText(
                            "Installation Failed. Error code: " +
                                    state.errorCode()
                        )
                    }
                }
            }

        val isEnabled = manager.installedModules.contains("Home")

        binding.btnOpen.setOnClickListener {
            viewModel.isDownload.observe(this) {
                if (isEnabled || it) {
                    val i = Intent(this, Class.forName("id.co.indocyber.home.HomeActivity"))
                    i.putExtra("ExtraInt", "3") // Test intent for Dynamic feature
                    startActivity(i)
                } else {
                    Toast.makeText(this, "Feature not yet installed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        manager.registerListener(listener)
        super.onResume()
    }

    override fun onPause() {
        manager.unregisterListener(listener)
        super.onPause()
    }

}

