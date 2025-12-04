package com.example.anotherpokedex.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun <T : Any> previewLazyPagingItems(items: List<T>): LazyPagingItems<T> {
    val flow = remember { MutableStateFlow(PagingData.from(items)) }
    return flow.collectAsLazyPagingItems()
}