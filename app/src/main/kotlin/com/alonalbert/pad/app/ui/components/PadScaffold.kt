package com.alonalbert.pad.app.ui.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonalbert.pad.app.ui.PadViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PadScaffold(
    viewModel: PadViewModel,
    modifier: Modifier = Modifier,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val message by viewModel.messageState.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoadingState.collectAsStateWithLifecycle()


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = floatingActionButton,
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .imePadding()
    ) { padding ->
        val isImeVisible = WindowInsets.isImeVisible
        val contentPadding = remember(isImeVisible) {
            if (isImeVisible) PaddingValues(top = padding.calculateTopPadding()) else padding
        }
        LoadingContent(
            isLoading = isLoading,
            onRefresh = viewModel::refresh,
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            content()
        }

        message?.let {
            ShowSnackbar(snackbarHostState, it, viewModel)
        }
    }
}