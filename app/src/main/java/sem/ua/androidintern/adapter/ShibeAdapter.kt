package sem.ua.androidintern.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import sem.ua.androidintern.R
import sem.ua.androidintern.ui.SharedViewModel

class ShibeAdapter(private val sharedViewModel: SharedViewModel) :
    ListAdapter<String, ShibeAdapter.ShibeViewHolder>(ShibeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShibeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shibe_item, parent, false)
        return ShibeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShibeViewHolder, position: Int) {
        val shibeUrl = getItem(position)
        holder.bind(shibeUrl)

        val isFavorite = sharedViewModel.isFavorite(shibeUrl)
        holder.favoriteIcon.setImageResource(
            if (isFavorite) android.R.drawable.star_big_off
            else android.R.drawable.star_off
        )
        holder.favoriteIcon.setOnClickListener {
            if (isFavorite) {
                sharedViewModel.removeFavorite(shibeUrl)
            } else {
                sharedViewModel.addFavorite(shibeUrl)
            }
            notifyItemChanged(position)
        }
    }

    inner class ShibeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)
        fun bind(shibeUrl: String) {
            Picasso.get().load(shibeUrl).into(imageView)
        }
    }

    private class ShibeDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}