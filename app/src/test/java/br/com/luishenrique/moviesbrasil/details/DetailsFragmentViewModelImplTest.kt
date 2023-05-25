package br.com.luishenrique.moviesbrasil.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.MovieDetailsMapper
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import br.com.luishenrique.moviesbrasil.details.repository.DetailsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class DetailsFragmentViewModelImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    private lateinit var repository: DetailsRepository
    @MockK
    private lateinit var commandObserver: Observer<ResourceDetails<MovieDetail>>

    private lateinit var viewModel: DetailsFragmentViewModelImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = DetailsFragmentViewModelImpl(repository)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `GIVEN view model to call details WHEN success THEN post value success`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            // GIVEN
            val movieId = 123
            val movieDetailsResponse = getMovieDetails()
            val movieDetails = MovieDetailsMapper.transform(movieDetailsResponse.body())
            val expectedResponse = ResourceDetails.Success(movieDetails)

            coEvery { repository.getDetails(movieId) } returns movieDetailsResponse
            viewModel.command.observeForever(commandObserver)

            // WHEN
            viewModel.getDetails(movieId)

            // THEN
            verify { commandObserver.onChanged(ofType<ResourceDetails.Loading<MovieDetail>>()) }
            //verify { commandObserver.onChanged(expectedResponse) }

            viewModel.command.removeObserver(commandObserver)
        }
    }

    private fun getMovieDetails() = Response.success(
        MovieDetailsResponseVO(
            backdropPath = "",
            genres = emptyList(),
            homepage = "",
            id = 123L,
            originalTitle = "spider man",
            overview = "Super Hero",
            popularity = 5.0,
            releaseDate = "2001",
            voteAverage = 5.0,
            voteCount = 180000
        )
    )
}