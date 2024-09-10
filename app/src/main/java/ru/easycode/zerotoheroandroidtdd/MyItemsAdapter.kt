package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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
        val diffUtil = DiffUtilCallBack(itemsList, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        itemsList.clear()
        itemsList.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    class MyItemsHolder(private val binding: ItemsLayoutBinding) : ViewHolder(binding.root) {

        fun bind(source: CharSequence) {
            binding.elementTextView.text = source
        }
    }
}

private class DiffUtilCallBack(
    private val old: List<CharSequence>,
    private val new: List<CharSequence>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}