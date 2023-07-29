package sem.ua.androidintern.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sem.ua.androidintern.databinding.ItemUserBinding
import sem.ua.androidintern.model.User

class UserAdapter(
    private var userList: MutableList<User>,
    private val onClickListener: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val switchStates = mutableMapOf<Long, Boolean>()

    fun addUser(user: User) {
        userList.add(user)
        notifyItemInserted(userList.size - 1)
    }
    init {
        switchStates.putAll(userList.associateBy({ it.userId }, { it.isStudent }))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)

        holder.binding.isStudent.setOnCheckedChangeListener(null)

        val isStudent = switchStates[user.userId] ?: false
        holder.binding.isStudent.isChecked = isStudent

        holder.binding.isStudent.setOnCheckedChangeListener { _, isChecked ->
            switchStates[user.userId] = isChecked
            user.isStudent = isChecked
        }

        holder.binding.nameTv.setOnClickListener {
            onClickListener(user)
        }
    }

    override fun getItemCount(): Int = userList.size

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.nameTv.text = user.name
            binding.ageTv.text = user.age.toString()
            binding.dateOfBirthTv.text = user.dateOfBirth
            binding.isStudent.text = user.isStudent.toString()
        }
    }
}
