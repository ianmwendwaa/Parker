package org.example.parker.pages

import org.example.parker.ServerResponse

sealed interface ResultUiState {
    object Loading: ResultUiState
    data class Success(val data: ServerResponse): ResultUiState
    data class Error(val message: String): ResultUiState
}