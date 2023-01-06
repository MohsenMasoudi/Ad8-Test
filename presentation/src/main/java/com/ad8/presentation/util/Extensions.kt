package com.ad8.presentation.util

import android.util.Patterns
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun <T : ViewModel> Fragment.obtainViewModel(
    owner: ViewModelStoreOwner,
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory
) =
    ViewModelProvider(owner, viewModelFactory).get(viewModelClass)

// To avoid "java.lang.IllegalArgumentException: navigation destination is unknown to this NavController", se more https://stackoverflow.com/q/51060762/6352712
fun NavController.navigateSafe(
    @IdRes destinationId: Int,
    navDirection: NavDirections,
    callBeforeNavigate: () -> Unit
) {
    if (currentDestination?.id == destinationId) {
        callBeforeNavigate()
        navigate(navDirection)
    }
}

fun NavController.navigateSafe(@IdRes destinationId: Int, navDirection: NavDirections) {
    if (currentDestination?.id == destinationId) {
        navigate(navDirection)
    }

    fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


}