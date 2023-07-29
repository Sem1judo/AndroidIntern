package sem.ua.androidintern


import android.content.Context
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import sem.ua.androidintern.adapter.UserAdapter
import sem.ua.androidintern.databinding.ActivityUserBinding
import sem.ua.androidintern.model.User
import sem.ua.androidintern.ui.UserDetailsFragment

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
        } else {
            val userDetailsFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            if (userDetailsFragment is UserDetailsFragment) {
                binding.userRv.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction().remove(userDetailsFragment).commit()
            }
        }
    }

    private val userList = mutableListOf(
        User(1, "Anna", 43),
        User(2, "John1", 25),
        User(3, "Emily1", 22, true),
        User(4, "Michael1", 30),
        User(5, "Sophia1", 19, true),
        User(6, "Daniel1", 28),
        User(7, "Olivia1", 21, true),
        User(8, "John2", 25),
        User(9, "Emilya2", 22, true),
        User(10, "Michaela2", 30),
        User(11, "Sophiaa2", 19, true),
        User(12, "Daniela2", 28),
        User(13, "Oliviaa2", 21, true),
        User(14, "John3", 25),
        User(15, "Emily3", 22, true),
        User(16, "Michael3", 30),
        User(17, "Sophia3", 19, true),
        User(18, "Daniel3", 28),
        User(19, "Olivia3", 21, true),
        User(20, "John4", 25),
        User(21, "Emily4", 22, true),
        User(22, "Michael4", 30),
        User(23, "Sophia4", 19, true),
        User(24, "Daniel4", 28),
        User(25, "Mike", 28),
        User(26, "Sam", 28, true),
        User(27, "Oleg", 28),
        User(28, "John", 21, true),
        User(29, "Milla", 27)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userRv.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter(userList) { user ->
            showUserDetailFragment(user)
        }

        binding.userRv.adapter = adapter
    }

    private fun showUserDetailFragment(user: User) {

        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userName", user.name)
        editor.putInt("userAge", user.age)
        editor.putLong("userID", user.userId)
        editor.putBoolean("userIsStudent", user.isStudent)
        editor.apply()

        binding.userRv.visibility = View.GONE

        val fragment = UserDetailsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()


    }
}
