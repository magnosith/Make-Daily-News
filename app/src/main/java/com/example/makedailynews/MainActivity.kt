package com.example.makedailynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.makedailynews.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.sendNewsButton.setOnClickListener {
        val title = binding.editTitleNews.text.toString()
        val news = binding.editWriteNews.text.toString()
        val dataPublished = binding.editDatePost.text.toString()
        val author = binding.editAuthorPost.text.toString()


        if(title.isEmpty() || news.isEmpty() || dataPublished.isEmpty() || author.isEmpty()){
            Toast.makeText(this, "Insert data in all boxes", Toast.LENGTH_SHORT).show()

        }else {
            sendNews(title, news, dataPublished, author)
        }
    }




    }

    private fun cleanInserts() {
        binding.editTitleNews.setText("")
        binding.editWriteNews.setText("")
        binding.editDatePost.setText("")
        binding.editAuthorPost.setText("")
    }

    private fun sendNews(title: String, news: String, dataWrite: String, author: String) {

        val mapNews = hashMapOf(
            "title" to title,
            "news" to news,
            "date" to dataWrite,
            "author" to author
        )

        db.collection("news").document("news")
            .set(mapNews).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Published is sucessfull!", Toast.LENGTH_SHORT).show()
                    cleanInserts()
                }
            }. addOnFailureListener {
                Toast.makeText(this, "Error occurred, Try again!", Toast.LENGTH_SHORT).show()
            }
    }

}
