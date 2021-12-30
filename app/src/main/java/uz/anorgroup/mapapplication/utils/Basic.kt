package uz.anorgroup.mapapplication.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber

//fun String.startScreen(): StartScreenEnum {
//    return if (this == StartScreenEnum.LOGIN.name) StartScreenEnum.LOGIN
//    else StartScreenEnum.MAIN
//}

fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun myLog(message: String) {
    Timber.d(message)
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}
fun <T : ViewBinding> T.scope(block : T.() ->Unit) {
    block(this)
}


fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) { }
    return false
}

// file


/*
0-Register
1-Enter
2-Main
 */