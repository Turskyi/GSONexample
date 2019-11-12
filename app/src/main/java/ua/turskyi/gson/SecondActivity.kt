package ua.turskyi.gson

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(R.layout.activity_second){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnSendToThirdActivity.setOnClickListener {
            saveProfile()
            sendToThirdActivity()
        }
    }

    private fun saveProfile() {
        val user = getUser()
        val profile = Profile(user?.name, user?.age, "${editTextLanguage.text}")
        saveUsersToPreferences(profile)
    }

    private fun sendToThirdActivity() {
        val intent = Intent(this, ThirdActivity::class.java)
        this.startActivity(intent)
    }

    private fun saveUsersToPreferences(user: Profile) {
        val prefEditor = PreferenceManager
            .getDefaultSharedPreferences(this).edit()
        val jsonString = Gson().toJson(user)
        prefEditor.putString("user", jsonString).apply()
    }

    private fun getUser(): Profile? {
        val preferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        val jsonString = preferences.getString("user", null)

        return if (jsonString != null)
            Gson().fromJson(jsonString)
        else
            Profile(null,null,null)
    }
}