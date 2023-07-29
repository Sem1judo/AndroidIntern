package sem.ua.androidintern.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sem.ua.androidintern.R
import sem.ua.androidintern.databinding.FragmentUserDetailsBinding
import sem.ua.androidintern.model.User

class UserDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayUserDetails(getUser())
    }

    private fun getUser(): User {
        val sharedPreferences = activity?.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userName = sharedPreferences?.getString("userName", "") ?: ""
        val userAge = sharedPreferences?.getInt("userAge", 0) ?: 0
        val userId = sharedPreferences?.getLong("userId", 0) ?: 0
        val userIsStudent = sharedPreferences?.getBoolean("userIsStudent", false) ?: false

        return User(userId, userName, userAge, userIsStudent)
    }

    private fun displayUserDetails(user: User) {
        val nameText = getString(R.string.user_details_name, user.name)
        val ageText = getString(R.string.user_details_age, user.age)
        val isStudentText = getString(R.string.user_details_is_student, user.isStudent.toString())

        binding.nameTv.text = nameText
        binding.ageTv.text = ageText
        binding.isStudent.text = isStudentText
    }
}
