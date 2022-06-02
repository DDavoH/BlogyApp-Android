package android.davoh.blogyapp.presentation.home

import android.davoh.api.responses.posts.Posts
import android.davoh.blogyapp.databinding.ItemEntryBinding
import android.davoh.blogyapp.utils.DateUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter : ListAdapter<Posts, RecyclerView.ViewHolder>(DiffCallback()){
    
    private var listener : OnItemClickListener?= null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         return  ItemViewHolder(ItemEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
           return (holder as ItemViewHolder).bind(getItem(position))
    }
    
    
    inner class ItemViewHolder(private val binding: ItemEntryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Posts){
            binding.tvNameAuthor.text = item.author
            binding.tvEntryContent.text = item.content
            binding.tvEntryDate.text =  DateUtil.dateTimeServerToLocal(item.createdAt)
            binding.tvEntryTitle.text = item.title
            binding.item.setOnClickListener {
                listener?.onItemClick(item)
            }
        }
    }
    

    class DiffCallback : DiffUtil.ItemCallback<Posts>(){
        override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return newItem == oldItem
        }
        
        override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return newItem == oldItem
        }
    }
    
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
    interface OnItemClickListener{
        fun onItemClick(item: Posts)
    }
    
}