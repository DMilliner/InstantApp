package com.something.submodule

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.something.submodule.databinding.ActivtiySubmoduleBinding
import com.google.android.gms.common.wrappers.InstantApps.isInstantApp
import com.google.android.gms.instantapps.InstantApps
import com.google.android.gms.tasks.Task


const val INSTANT_PROMPT_REQUEST_CODE = 11111
private const val INSTALLED_PACKAGE_NAME = "com.something.instantapptest"
class SubmoduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivtiySubmoduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivtiySubmoduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isInstant =  isInstantApp(this)

        if (isInstant) {
            binding.tryItButton.visibility = View.VISIBLE
        } else {
            binding.tryItButton.visibility = View.GONE
        }

        binding.tryItButton.setOnClickListener {
            showInstallPrompt()
        }


    }

    private fun showInstallPrompt() {
        val postInstall = Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_DEFAULT)
            .setPackage(INSTALLED_PACKAGE_NAME)//installed-experience-package-name

        // The request code is passed to startActivityForResult().
        InstantApps.showInstallPrompt(this@SubmoduleActivity, postInstall, INSTANT_PROMPT_REQUEST_CODE, null)
    }

//    private fun checkInstantApp() {
//        val client = InstantApps.getInstantAppsClient(this@SubmoduleActivity)
//        val iAEnableTask: Task<Boolean> = client.areInstantAppsEnabledForDevice()
//        iAEnableTask
//            .addOnCompleteListener { _iAEnableTask -> _iAEnableTask.getResult() }
//            .addOnFailureListener { exception -> exception }
//    }
}