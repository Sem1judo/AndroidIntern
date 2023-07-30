package sem.ua.androidintern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import sem.ua.androidintern.databinding.ActivityUserBinding
import sem.ua.androidintern.ui.SharedViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]


        val pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Junior"
                1 -> "Favorites"
                else -> ""
            }
        }.attach()
    }

    fun getSharedViewModel(): SharedViewModel {
        return sharedViewModel
    }
}
