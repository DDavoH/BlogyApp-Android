package android.davoh.blogyapp.presentation.detailEntry

import android.davoh.api.responses.posts.Posts
import android.davoh.blogyapp.R
import android.davoh.blogyapp.databinding.ActivityDetailEntryBinding
import android.davoh.blogyapp.utils.DateUtil
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class DetailEntryActivity : AppCompatActivity(){
    
    private lateinit var binding : ActivityDetailEntryBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.myToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        supportActionBar?.title = getString(R.string.detalles)
        toolbar.setTitleTextColor(Color.WHITE)
        
        
        val post = intent.extras?.getParcelable<Posts>("post")
        
        binding.tvTitle.text = post?.title
        binding.tvNameAuthor.text = post?.author
        binding.tvContent.text = post?.content
        binding.tvDate.text = DateUtil.dateTimeServerToLocal(post?.createdAt)
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}