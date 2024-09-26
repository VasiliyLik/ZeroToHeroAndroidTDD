package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemLayoitBinding

class Adapter(private val deleteItem: DeleteItemUi) : RecyclerView.Adapter<MyItemViewHolder>() {

    private val list = mutableListOf<ItemUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyItemViewHolder(deleteItem, ItemLayoitBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(newList: List<ItemUi>) {
        val diffUtil = DiffUtilCallBack(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }
}

class MyItemViewHolder(
    private val deleteItem: DeleteItemUi,
    private val binding: ItemLayoitBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemUi: ItemUi) {
        itemUi.show(binding.elementTextView)
        itemView.setOnClickListener {
            itemUi.delete(deleteItem)
        }
    }
}

private class DiffUtilCallBack(
    private val oldList: List<ItemUi>,
    private val newList: List<ItemUi>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].areItemsSame(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
