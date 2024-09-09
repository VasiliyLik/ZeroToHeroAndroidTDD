package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.ItemsLayoutBinding

class MyItemsAdapter : RecyclerView.Adapter<MyItemsAdapter.MyItemsHolder>() {

    private val itemsList = ArrayList<CharSequence>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemsHolder {
        return MyItemsHolder(ItemsLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: MyItemsHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    fun update(newList: List<CharSequence>) {
        itemsList.clear()
        itemsList.addAll(newList)
        notifyDataSetChanged()
    }

    class MyItemsHolder(private val binding: ItemsLayoutBinding) : ViewHolder(binding.root) {

        fun bind(source: CharSequence) {
            binding.elementTextView.text = source
        }
    }
}