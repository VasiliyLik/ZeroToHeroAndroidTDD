package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import java.util.HashMap

class App : Application(), ProvideViewModel {

    private lateinit var factory: ProvideViewModel
    private val store = HashMap<Class<out ViewModel>, ViewModel?>()

    private val clear = object : ClearViewModel {
        override fun clearViewModel(clazz: Class<out ViewModel>) {
            store[clazz] = null
        }
    }

    override fun onCreate() {
        super.onCreate()
        val core = Core(this)
        factory = ProvideViewModel.Base(core, clear)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        if (store[clazz] == null)
            store[clazz] = factory.viewModel(clazz)
        return store[clazz] as T
    }
}

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(clazz: Class<T>): T

    class Base(core: Core, private val clear: ClearViewModel) : ProvideViewModel {

        private val repository = Repository.Base(core.dao(), Now.Base())
        private val liveDataWrapper = ListLiveDataWrapper.Base()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(clazz: Class<T>): T =
            (if (clazz == MainViewModel::class.java)
                MainViewModel(repository, liveDataWrapper)
            else
                AddViewModel(repository, liveDataWrapper, clear)) as T
    }
}

interface ClearViewModel {

    fun clearViewModel(clazz: Class<out ViewModel>)
}