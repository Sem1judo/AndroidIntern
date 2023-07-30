package sem.ua.androidintern.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val favoriteList = MutableLiveData<List<String>>(emptyList())

    fun getFavoriteList(): LiveData<List<String>> = favoriteList

    fun addFavorite(shibeUrl: String) {
        val newList = favoriteList.value.orEmpty().toMutableList()
        newList.add(shibeUrl)
        favoriteList.value = newList
    }

    fun removeFavorite(shibeUrl: String) {
        val newList = favoriteList.value.orEmpty().toMutableList()
        newList.remove(shibeUrl)
        favoriteList.value = newList
    }

    fun isFavorite(shibeUrl: String): Boolean {
        return favoriteList.value?.contains(shibeUrl) ?: false
    }
}
