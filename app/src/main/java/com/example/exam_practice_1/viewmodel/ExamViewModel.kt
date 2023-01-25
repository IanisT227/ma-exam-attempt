package com.example.exam_practice_1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam_practice_1.logTag
import com.example.exam_practice_1.model.Produs
import com.example.exam_practice_1.model.ProdusAdd
import com.example.exam_practice_1.model.ProdusBuy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException

class ExamViewModel(
    private val repository: Repository,
    private val networkService: NetworkService
) : ViewModel() {
    private val _produsList: MutableLiveData<List<Produs>> = MutableLiveData()
    val produsList: LiveData<List<Produs>>
        get() = _produsList

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError: MutableLiveData<String?> = MutableLiveData()
    val isError: LiveData<String?>
        get() = _isError

    private val _isOffline: MutableLiveData<Boolean> = MutableLiveData()
    val isOffline: LiveData<Boolean>
        get() = _isOffline

    private val _isAllowed: MutableLiveData<Boolean> = MutableLiveData()
    val isAllowed: LiveData<Boolean>
        get() = _isAllowed



    fun getRepoProductsList() {
        viewModelScope.launch(){
            _produsList.value = repository.readData()
            println("Value:" + _produsList.value?.toString())
        }

    }

    fun repoAddProduct(produs: Produs) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.addProdus(produs)
            } catch (e: Exception) {
                println(e.toString())
            }
        }
    }

    fun getProductsList() {
        viewModelScope.launch {
            try {
                _isAllowed.value = false
                _isOffline.value = false
                _isError.value = null
                _isLoading.value = true
                _produsList.value = networkService.getProduseList()
                _produsList.value = _produsList.value!!
                    .filter { produs: Produs -> produs.status }
                    .sortedWith(compareByDescending<Produs> { it.pret }.thenBy { it.cantitate })
                logTag("ProductList", _produsList.value.toString())
//                if (!_recipeList.value.isNullOrEmpty()) {
//                    offlineRepository.repopulateRecipeList(_recipeList.value!!)
//                }
//                repository.repopulateDB(_produsList.value!!)
                logTag("productList_Value", networkService.getProduseList().toString())
            } catch (e: Exception) {
                if (e is ConnectException) {
                    _isError.value = "Server unavailable"
                    _isOffline.value = true
                } else {
                    _isError.value = e.toString()
                }
                logTag("productList_Error", e.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addProdus(produs: ProdusAdd) {
        viewModelScope.launch {
            try {
                _isError.value = null
                _isLoading.value = true
                val response = networkService.addProdus(produs)
//                if (!_recipeList.value.isNullOrEmpty()) {
//                    offlineRepository.repopulateRecipeList(_recipeList.value!!)
//                }
                logTag("produse_Add", response.toString())
            } catch (e: Exception) {
                if (e is ConnectException) {
                    _isError.value = "Server unavailable"
//                    _recipeList.value = offlineRepository.getAllRecipes()
                } else {
                    _isError.value = e.toString()
                }
                logTag("produs_AddError", e.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun buyProdus(produsBuy: ProdusBuy) {
        viewModelScope.launch {
            try {
                _isAllowed.value = false
                _isError.value = null
                _isLoading.value = true
                val response = networkService.buyProdus(produsBuy)
//                if (!_recipeList.value.isNullOrEmpty()) {
//                    offlineRepository.repopulateRecipeList(_recipeList.value!!)
//                }
                _isAllowed.value = true
                logTag("produs_Buy", response.toString())
            } catch (e: Exception) {
                if (e is ConnectException) {
                    _isError.value = "Server unavailable"
                    _isAllowed.value = false
//                    _recipeList.value = offlineRepository.getAllRecipes()
                } else if (e is HttpException) {
                    _isError.value = "Quantity too large"
                } else {
                    _isError.value = e.toString()
                }
                logTag("produs_BuyError", e.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

}