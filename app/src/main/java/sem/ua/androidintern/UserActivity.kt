package sem.ua.androidintern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sem.ua.androidintern.databinding.ActivityUserBinding
import sem.ua.androidintern.ui.ShibeFragment

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ShibeFragment())
                .commit()
        }
    }
}