package com.raerossi.notekeeper.ui.features.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.notekeeper.domain.Category
import com.raerossi.notekeeper.domain.Task
import com.raerossi.notekeeper.domain.usecases.CreateTaskUseCase
import com.raerossi.notekeeper.domain.usecases.GetCategoriesUseCase
import com.raerossi.notekeeper.ui.features.registration.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task> = _task

    private val _listCategories = MutableLiveData<List<Category>>()
    val listCategories: LiveData<List<Category>> = _listCategories

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = TaskUiState(isLoading = true)
            delay(2500)
            _listCategories.value = getCategoriesUseCase()
            _uiState.value = TaskUiState(isLoading = false)
        }
    }

    fun onTaskChanged(task: Task) {
        _task.value = task
    }
}