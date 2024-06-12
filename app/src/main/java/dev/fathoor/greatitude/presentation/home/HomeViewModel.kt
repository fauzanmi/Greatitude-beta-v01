package dev.fathoor.greatitude.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fathoor.core.domain.usecase.user.GetLocalUserUseCase
import dev.fathoor.core.domain.usecase.user.GetSessionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getSessionUseCase: GetSessionUseCase
) : ViewModel() {
    private val _session = MutableStateFlow<Long>(0)
    val session: StateFlow<Long> = _session

    init {
        getSession()
    }

    private fun getSession() {
        viewModelScope.launch {
            getSessionUseCase.execute(Unit)
                .collect { id ->
                    _session.value = id
                }
        }
    }

    private fun retrieveDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return String.format("%02d/%02d/%04d", day, month, year)
    }
}
