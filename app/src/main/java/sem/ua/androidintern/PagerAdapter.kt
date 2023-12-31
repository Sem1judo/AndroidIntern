package sem.ua.androidintern

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import sem.ua.androidintern.ui.FavoritesFragment
import sem.ua.androidintern.ui.ShibeFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ShibeFragment()
            1 -> FavoritesFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
