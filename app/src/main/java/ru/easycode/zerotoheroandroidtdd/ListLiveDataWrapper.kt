package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    interface UpDate {
        fun update(value: List<ItemUi>)
    }

    interface Read {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Add {
        fun add(value: ItemUi)
    }

    interface Change {
        fun delete(item: ItemUi)
        fun update(item: ItemUi)
    }

    interface Mutable : UpDate, Read

    interface All : Mutable, Add, Change

    class Base : All {

        private val livaData = MutableLiveData<List<ItemUi>>()

        override fun update(value: List<ItemUi>) {
            livaData.value = value
        }

        override fun update(item: ItemUi) {
            val list = livaData.value?.toMutableList() ?: ArrayList()
            list.find { it.areItemsSame(item) }?.let {
                list[list.indexOf(it)] = item
            }
            update(list)
        }

        override fun liveData(): LiveData<List<ItemUi>> {
            return livaData
        }

        override fun add(value: ItemUi) {
            val list = livaData.value?.toMutableList() ?: ArrayList()
            list.add(value)
            update(list)
        }

        override fun delete(item: ItemUi) {
            val list = livaData.value?.toMutableList() ?: ArrayList()
            list.remove(item)
            update(list)
        }
    }
}