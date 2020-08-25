package android.example.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.repository.BooksRepository
import com.example.nybooks.presentation.books.BooksViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {
    private lateinit var viewModel: BooksViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule( )

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    @Test
    fun `when view model getBooks get success then sets booksLiveData`(){
        val books = listOf<Book>(
            Book("titulo A", "Autor 1", "descrição Lalala")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel= BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.getBooks()

        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(BooksViewModel.VIEW_FLIPPER_BOOKS, null))

    }
}

class MockRepository(private val result: BooksResult) : BooksRepository {
    override fun getBooks(booksResultCallBack: (result: BooksResult) -> Unit) {
        booksResultCallBack(result)
    }
}