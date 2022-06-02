package android.davoh.blogyapp.dialogs

import android.davoh.blogyapp.databinding.LayoutErrorBinding
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class ErrorDialogFragment : DialogFragment() {
    private lateinit var binding : LayoutErrorBinding
    var title = ""
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutErrorBinding.inflate(layoutInflater, container,false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        binding.btnRetry.setOnClickListener {
            this.dismiss()
        }
        
        if(title.isNotEmpty()){
            binding.title.text = title
        }
        
        return binding.root
    }
    
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
        }
    }
    
    companion object {
        fun newInstance(title: String): ErrorDialogFragment {
            val frag = ErrorDialogFragment()
            frag.isCancelable = true
            frag.title = title
            return frag
        }
    }
}
