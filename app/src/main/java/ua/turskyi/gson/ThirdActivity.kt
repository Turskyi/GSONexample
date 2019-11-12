package ua.turskyi.gson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity(R.layout.activity_third){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = getProfile()
        name.text = user?.name

        age.text = user?.age

        language.text = user?.language

    }

    private fun getProfile(): Profile? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val jsonString = preferences.getString("user", null)

        return if (jsonString != null)
            Gson().fromJson(jsonString)
        else
            null
    }
}