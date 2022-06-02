package android.davoh.blogyapp.presentation.addEntry

import android.davoh.blogyapp.databinding.FragmentAddPostBinding
import android.davoh.blogyapp.presentation.home.HomeViewModel
import android.davoh.blogyapp.utils.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEntryFragment : BaseFragment() {
    
    private lateinit var binding : FragmentAddPostBinding
    private val viewModel : HomeViewModel by activityViewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPostBinding.inflate(inflater,container, false)
        
        listenView()
        observables()
        return binding.root
    }
    
    private fun listenView(){
        binding.btnSave.setOnClickListener {
            viewModel.createPost(binding.etTitle.text.toString(), binding.etContent.text.toString(), binding.etNameAuthor.text.toString())
        }
    }
    
    private  fun observables(){
        viewModel.created.observe(viewLifecycleOwner, Observer {
            binding.etTitle.setText("")
            binding.etContent.setText("")
            binding.etNameAuthor.setText("")
        })
        viewModel.loader.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.loader.progressbar.visibility = View.VISIBLE
            }else{
                binding.loader.progressbar.visibility = View.GONE
            }
        })
        
        viewModel.httpError.observe(viewLifecycleOwner, this::showError)
    }
    
}