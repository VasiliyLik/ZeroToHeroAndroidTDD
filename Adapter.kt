package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemLayoutBinding


class Adapter : RecyclerView.Adapter<MyItemViewHolder>() {

    private val list = ArrayList<CharSequence>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemViewHolder {
        return MyItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: MyItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
    fun update(newList: List<CharSequence>) {
        val diffUtil = DiffUtilCallBack(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)

    }
}

class MyItemViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(source: CharSequence) {
        binding.elementTextView.text = source
    }
}

private class DiffUtilCallBack(
    private val old: List<CharSequence>,
    private val new: List<CharSequence>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

}