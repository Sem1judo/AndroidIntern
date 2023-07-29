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

class ShibeAdapter : ListAdapter<String, ShibeAdapter.ShibeViewHolder>(ShibeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShibeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shibe_item, parent, false)
        return ShibeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShibeViewHolder, position: Int) {
        val shibeUrl = getItem(position)
        holder.bind(shibeUrl)
    }

    inner class ShibeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

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