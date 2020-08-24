package com.example.nybooks.presentation.books

import android.example.nybooks.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.data.model.Book
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        toolbarMain.title = getString(R.string.books_title)
        setSupportActionBar(toolbarMain)

        with(recyclerBooks){
            adapter = BooksAdapter(getBooks())
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
        }
    }

    fun getBooks(): List<Book>{
        return listOf(
            Book("title 1", "Author 1"),
            Book("title 2", "Author 2"),
            Book("title 3", "Author 3")
        )
    }
}