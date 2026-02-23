package com.beginina.spaceapp.presentation.pages

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.beginina.domain.models.ApodModel
import com.beginina.domain.usecases.GetApodByIdUseCase
import com.beginina.domain.usecases.GetApodListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApodViewModel @Inject constructor(
    private val getApodListUseCase: GetApodListUseCase,
    private val getApodByIdUseCase: GetApodByIdUseCase
): ViewModel() {

    private val _isOffline = mutableStateOf(false)
    val isOffline: State<Boolean> = _isOffline



    fun getApodPager(): Pager<Int, ApodModel> {
        return Pager(
            config = PagingConfig(pageSize = 2, enablePlaceholders = false),
            pagingSourceFactory = { ApodPagingSource(getApodListUseCase){offline ->
                _isOffline.value = offline
            } }
        )
    }

    suspend fun getApodById(id: Int): ApodModel?{
        return getApodByIdUseCase(id)
    }
}