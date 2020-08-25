package com.example.nybooks.data.repository

import android.example.nybooks.R
import com.example.nybooks.data.ApiService
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.response.BookBodyResponse
import com.example.nybooks.presentation.books.BooksViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource : BooksRepository {

    override fun getBooks(booksResultCallBack: (result: BooksResult) -> Unit){
        ApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {

            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookBodyResponse ->
                            for (results in bookBodyResponse.bookResults) {
                                val book = results.bookDetails[0].getBookModel()
                                books.add(book)
                            }
                        }
                        booksResultCallBack(BooksResult.Success(books))
                    }
                    else -> booksResultCallBack(BooksResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallBack(BooksResult.ServerError)

            }

        })
    }
}