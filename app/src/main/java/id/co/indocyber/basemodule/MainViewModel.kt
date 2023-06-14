package id.co.indocyber.basemodule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val isDownload = MutableLiveData<Boolean>()

    init {
        changeStatusDownload(false)
    }

    fun changeStatusDownload(boolean: Boolean) {
        isDownload.postValue(boolean)
    }
}