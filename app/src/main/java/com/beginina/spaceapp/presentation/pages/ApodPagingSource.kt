package com.beginina.spaceapp.presentation.pages

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.beginina.domain.models.ApodModel
import com.beginina.domain.usecases.GetApodListUseCase
import java.time.LocalDate

class ApodPagingSource(
    private val getApodListUseCase: GetApodListUseCase,
    private val onOffline: (Boolean) -> Unit
): PagingSource<Int, ApodModel>() {
    private val pageSize = 2

    override fun getRefreshKey(state: PagingState<Int, ApodModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApodModel> {
        return try {
            val page = params.key ?: 0
            val endDate = LocalDate.now().minusDays((page * pageSize).toLong())
            val startDate = endDate.minusDays(pageSize.toLong() - 1)

            val (apods, isOffline) = getApodListUseCase(
                startDate = startDate,
                endDate = endDate
            )
            onOffline(isOffline)

            LoadResult.Page(
                data = apods,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (apods.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Log.e("ApodPagingSource", "Error loading apods", e)
            LoadResult.Error(e)
        }
    }
}