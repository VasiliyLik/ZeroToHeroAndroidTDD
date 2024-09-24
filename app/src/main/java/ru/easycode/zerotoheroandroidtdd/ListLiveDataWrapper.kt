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

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface Mutable : UpDate, Read

    interface All : Mutable, Add, Delete

    class Base : All {

        private val liveData = MutableLiveData<List<ItemUi>>()

        override fun update(value: List<ItemUi>) {
            liveData.value = value
        }

        override fun liveData(): LiveData<List<ItemUi>> {
            return liveData
        }

        override fun add(value: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.add(value)
            update(list)
        }

        override fun delete(item: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.remove(item)
            update(list)
        }
    }
}