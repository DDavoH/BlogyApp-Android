package android.davoh.blogyapp.utils

import android.davoh.api.responses.HttpError
import android.davoh.blogyapp.dialogs.ErrorDialogFragment
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    
    private var errorDialog: ErrorDialogFragment? = null
    
    
    fun showError(httpError: HttpError){
        errorDialog = ErrorDialogFragment.newInstance(httpError.error)
        if (errorDialog != null && !errorDialog!!.isAdded) {
            errorDialog!!.show(childFragmentManager, "error")
        }
    }
    
    
}