package sem.ua.androidintern


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import sem.ua.androidintern.adapter.UserAdapter
import sem.ua.androidintern.databinding.ActivityUserBinding
import sem.ua.androidintern.model.User
import sem.ua.androidintern.ui.AddUserActivity
import sem.ua.androidintern.ui.UserDetailsFragment
class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
        } else {
            val userDetailsFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            if (userDetailsFragment is UserDetailsFragment) {
                binding.userRv.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction().remove(userDetailsFragment).commit()
            }
        }
    }

    private val addUserLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                data?.getParcelableExtra<User>("user")?.let { newUser ->
                    // Add the new user to the list
                    (binding.userRv.adapter as? UserAdapter)?.addUser(newUser)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val userList = getUsersFromSharedPreferences()
        val adapter = UserAdapter(userList as MutableList<User>) { user ->
            showUserDetailFragment(user)
        }
        binding.userRv.layoutManager = LinearLayoutManager(this)
        binding.userRv.adapter = adapter
    }

    private fun getUsersFromSharedPreferences(): List<User> {
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userList = mutableListOf<User>()

        sharedPreferences.all.forEach { (key, _) ->
            if (key.startsWith("userName_")) {
                val userId = key.removePrefix("userName_").toLong()
                val name = sharedPreferences.getString(key, "") ?: ""
                val ageKey = "userAge_$userId"
                val age = sharedPreferences.getInt(ageKey, 0)
                val dateOfBirthKey = "userDateOfBirth_$userId"
                val dateOfBirth = sharedPreferences.getString(dateOfBirthKey, "") ?: ""
                val isStudentKey = "userIsStudent_$userId"
                val isStudent = sharedPreferences.getBoolean(isStudentKey, false)

                val userDetails = User(userId, name, age, dateOfBirth, isStudent)
                userList.add(userDetails)
            }
        }

        return userList
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_user_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_user -> {
                openAddUserActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openAddUserActivity() {
        val intent = Intent(this, AddUserActivity::class.java)
        addUserLauncher.launch(intent)
    }

    private fun showUserDetailFragment(user: User) {
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userName", user.name)
        editor.putInt("userAge", user.age)
        editor.putLong("userID", user.userId)
        editor.putString("userDateOfBirth", user.dateOfBirth)
        editor.putBoolean("userIsStudent", user.isStudent)
        editor.apply()

        binding.userRv.visibility = View.GONE

        val fragment = UserDetailsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
