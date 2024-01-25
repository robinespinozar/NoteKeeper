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
import com.raerossi.notekeeper.ui.features.registration.SignUpUser
import com.raerossi.notekeeper.utils.extensions.toLocalTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
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
            _listCategories.value = getCategoriesUseCase()
            _uiState.value = TaskUiState(isLoading = false)
        }
    }

    fun onTaskChanged(task: Task) {
        _task.value = task
    }

    fun onTaskHandlerSelected(task: Task, toHomeScreen: () -> Unit) {
        val uiState = task.toTaskUiState()
        if (uiState.validateTask()) {
            createTask(task) { toHomeScreen() }
        } else {
            onUiStateChanged(task)
        }
    }

    private fun createTask(task: Task, toHomeScreen: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = TaskUiState(isLoading = true)
            val isCreationSuccessful = createTaskUseCase(task)
            if (isCreationSuccessful) {
                toHomeScreen()
            } else {
                _uiState.value = TaskUiState(showErrorDialog = true)
            }
            _uiState.value = TaskUiState(isLoading = false)
        }
    }

    private fun onUiStateChanged(task: Task) {
        _uiState.value = task.toTaskUiState()
    }

    fun hideErrorDialog() {
        _uiState.value = TaskUiState(showErrorDialog = false)
    }

    fun isValidTitle(title: String) = title.length > 6

    fun isValidTime(startTime: LocalTime, endTime: LocalTime) = startTime.compareTo(endTime) < 0

    fun Task.toTaskUiState(): TaskUiState {
        return TaskUiState(
            isValidTitle = isValidTitle(this.title),
            isValidTime = isValidTime(this.startTime.toLocalTime(), this.endTime.toLocalTime())
        )
    }
}