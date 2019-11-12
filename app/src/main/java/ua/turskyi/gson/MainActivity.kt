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
        val profile = Profile(null,null,null)
        profile.name = "${editTextName.text}"
        profile.age = "${editTextAge.text}"
        saveUsersToPreferences(profile)
    }

    private fun sendToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        this.startActivity(intent)
    }

    private fun saveUsersToPreferences(user: Profile) {
        val prefEditor = PreferenceManager
            .getDefaultSharedPreferences(this).edit()
        val jsonString = Gson().toJson(user)
        prefEditor.putString("user", jsonString).apply()
    }
}
