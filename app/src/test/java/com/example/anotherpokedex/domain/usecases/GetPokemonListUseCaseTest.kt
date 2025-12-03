package com.example.anotherpokedex.domain.usecases

import androidx.paging.PagingData
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.model.PokemonTypePairing
import com.example.anotherpokedex.domain.model.Type
import com.example.anotherpokedex.domain.repository.PokemonRepository
import junit.framework.TestCase.assertSame
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetPokemonListUseCaseTest {

    private val repository: PokemonRepository = mock()
    private lateinit var useCase: GetPokemonListUseCase

    @Before
    fun setup() {
        useCase = GetPokemonListUseCase(repository)
    }

    //TODO - this is a pretty worthless test, consider writing an instrumented Test that can delve into the paging data
    @Test
    fun `invoke should return flow from repository`() = runTest {
        val expectedList = listOf(
            Pokemon(
                dexNumber = 1,
                name = "Bulbasaur",
                imageUrl = "bulbasaurl",
                shinyImageUrl = "shinybulbasaurl",
                types = PokemonTypePairing(Type.Grass, Type.Poison)
            ),
            Pokemon(
                dexNumber = 7,
                name = "Squirtle",
                imageUrl = "squrltle",
                shinyImageUrl = "shinysqurltle",
                types = PokemonTypePairing(Type.Water)
            )
        )

        val pagingData = PagingData.from(expectedList)
        whenever(repository.getPagedPokemonList()).thenReturn(flowOf(pagingData))

        val result = useCase().first()

        assertSame(pagingData, result)
        verify(repository).getPagedPokemonList()
    }
}