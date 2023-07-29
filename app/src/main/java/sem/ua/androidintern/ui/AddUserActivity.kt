package sem.ua.androidintern.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sem.ua.androidintern.databinding.MenuUserActivityBinding
import sem.ua.androidintern.model.User

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: MenuUserActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuUserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val age = binding.ageEditText.text.toString().toInt()
            val dateOfBirth = "${binding.datePicker.dayOfMonth}/${binding.datePicker.month + 1}/${binding.datePicker.year}"

            val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val userId = System.currentTimeMillis() // You can use any unique identifier here
            editor.putString("userName_$userId", name)
            editor.putInt("userAge_$userId", age)
            editor.putString("userDateOfBirth_$userId", dateOfBirth)
            editor.apply()

            val newUser = User(userId, name, age, dateOfBirth, false)

            val resultIntent = Intent().apply {
                putExtra("user", newUser)
            }
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
            Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
        }
    }
}
