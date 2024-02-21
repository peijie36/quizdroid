package edu.uw.ischool.peijie36.quizdroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class Preferences : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, PreferencesFragment())
            .commit()
    }

    class PreferencesFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preference, rootKey)

            findPreference<Preference>("data_url")?.setOnPreferenceChangeListener { _, newValue ->
                true
            }

            findPreference<Preference>("download_interval")?.setOnPreferenceChangeListener { _, newValue ->
                true
            }
        }
    }
}