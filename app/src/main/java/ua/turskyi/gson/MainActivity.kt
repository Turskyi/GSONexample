package ua.turskyi.gson

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnSendToSecondActivity.setOnClickListener {
            saveProfile()
            sendToSecondActivity()
        }

        btnShowData.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveProfile() {
        val profile = Profile("${editTextName.text}","${editTextAge.text}")
        saveProfileToPreferences(profile)
    }

    private fun saveProfileToPreferences(profile: Profile) {
        val prefEditor = PreferenceManager
            .getDefaultSharedPreferences(this).edit()
        val jsonString = Gson().toJson(profile)
        prefEditor.putString("profile", jsonString).apply()
    }

    private fun sendToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        this.startActivity(intent)
    }
}
