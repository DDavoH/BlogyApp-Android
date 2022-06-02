package android.davoh.blogyapp.presentation.home

import android.content.Intent
import android.davoh.api.responses.posts.Posts
import android.davoh.blogyapp.databinding.FragmentHomeBinding
import android.davoh.blogyapp.presentation.detailEntry.DetailEntryActivity
import android.davoh.blogyapp.utils.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeViewModel by activityViewModels()
    
    private val adapter = PostsAdapter()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPosts()
        
        configView()
        observables()
    }
    
    private fun configView(){
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rv.adapter = adapter
        adapter.setOnItemClickListener(object: PostsAdapter.OnItemClickListener{
            override fun onItemClick(item: Posts) {
               val intent = Intent(requireActivity(), DetailEntryActivity::class.java)
                intent.putExtra("post", item)
                startActivity(intent)
            }
        })
    }
    
    private fun observables(){
        viewModel.posts.observe(viewLifecycleOwner, Observer { posts->
            adapter.submitList(posts.result?.data?.posts)
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