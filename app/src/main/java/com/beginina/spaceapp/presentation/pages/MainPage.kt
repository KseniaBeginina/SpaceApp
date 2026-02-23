package com.beginina.spaceapp.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.beginina.spaceapp.R
import com.beginina.spaceapp.navigation.Routes
import com.beginina.spaceapp.presentation.components.FilledButton
import com.beginina.spaceapp.presentation.components.PreviewCard
import com.beginina.spaceapp.ui.theme.Black
import com.beginina.spaceapp.ui.theme.LightGray
import com.beginina.spaceapp.ui.theme.medium16
import com.beginina.spaceapp.ui.theme.semibold24

@Composable
fun MainPage(
    apodViewModel: ApodViewModel,
    navController: NavController
) {
    val pager = remember {apodViewModel.getApodPager().flow.cachedIn(apodViewModel.viewModelScope)}
    val lazyPagingItems = pager.collectAsLazyPagingItems()

    val isOffline by apodViewModel.isOffline
    var showOfflineDialog by remember { mutableStateOf(false) }
    LaunchedEffect(isOffline) {
        if (isOffline) {
            showOfflineDialog = true
        }
    }

    val columnState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ){
        if (lazyPagingItems.loadState.refresh is LoadState.Loading){
            Indicator()
        } else if (lazyPagingItems.itemCount == 0 && lazyPagingItems.loadState.refresh !is LoadState.Loading){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.offline_no_data),
                style = semibold24,
                color = LightGray
            )
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            state = columnState
        ){
            items(lazyPagingItems.itemCount) { item ->
                val apod = lazyPagingItems[item]
                apod?.let {
                    PreviewCard(apod) {
                        navController.currentBackStackEntry?.savedStateHandle?.set("apod_id", apod.id)
                        navController.navigate(Routes.ABOUT)
                    }
                }
            }
            lazyPagingItems.apply{
                when(loadState.append){
                    is LoadState.Loading -> item { Indicator() }
                    is LoadState.Error -> item { Error() }
                    else -> {}
                }
            }
        }

        }
        if (showOfflineDialog) {
            AlertDialog(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                containerColor = LightGray,
                onDismissRequest = { showOfflineDialog = false },
                text = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(R.string.offline_message),
                        style = medium16,
                        color = Black
                    )
                },
                confirmButton = { FilledButton("ок") {  showOfflineDialog = false } }
            )
        }
    }

@Composable
fun Indicator(){
    CircularProgressIndicator(
        modifier = Modifier.size(60.dp),
        color = LightGray
    )
}

@Composable
fun Error(){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        textAlign = TextAlign.Center,
        text = stringResource(R.string.error_data_load),
        style = semibold24,
        color = LightGray
    )
}