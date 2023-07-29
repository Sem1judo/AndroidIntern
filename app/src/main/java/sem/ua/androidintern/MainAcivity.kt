package sem.ua.androidintern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import sem.ua.androidintern.databinding.ActivityUserBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    private val favoriteList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    fun getFavoriteList(): List<String> {
        return favoriteList
    }

    fun addFavorite(shibeUrl: String) {
        favoriteList.add(shibeUrl)
    }

    fun removeFavorite(shibeUrl: String) {
        favoriteList.remove(shibeUrl)
    }

    fun isFavorite(shibeUrl: String): Boolean {
        return favoriteList.contains(shibeUrl)
    }
}
