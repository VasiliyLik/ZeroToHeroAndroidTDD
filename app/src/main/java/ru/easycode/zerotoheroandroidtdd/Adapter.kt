package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemLayoutBinding

class Adapter : RecyclerView.Adapter<Adapter.MyItemViewHolder>() {

    private val list = ArrayList<CharSequence>() //mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemViewHolder {
        return MyItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(newList: List<CharSequence>) {
        val diffUtilCallBack = DiffUtilCallBack(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtilCallBack)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    class MyItemViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(value: CharSequence) {
            binding.elementTextView.text = value
        }
    }
}

private class DiffUtilCallBack(
    private val oldList: List<CharSequence>,
    private val newList: List<CharSequence>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}