package android.example.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.repository.BooksRepository
import com.example.nybooks.presentation.books.BooksViewModel
import org.junit.Rule
import org.junit.Test

class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule( )

    private lateinit var viewModel: BooksViewModel

    @Test
    fun `when view model getBooks get success then sets booksLiveData`(){
        val books = listOf<Book>(
            Book("titulo A", "Autor 1", "descrição Lalala")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel= BooksViewModel(resultSuccess)

        viewModel.getBooks()
    }

}

class MockRepository(private val result: BooksResult) : BooksRepository {
    override fun getBooks(booksResultCallBack: (result: BooksResult) -> Unit) {
        booksResultCallBack(result)
    }

}