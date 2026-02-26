package com.example.rickandmorty.screen.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.character.CharacterApi
import com.example.rickandmorty.data.model.character.Character

class CharactersPagingSource(
    private var charactersApi: CharacterApi
): PagingSource<Int, Character>() {

    companion object {
        const val FIRST_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: FIRST_PAGE

        return try {
            val response = charactersApi.getAllCharacters(page)
            val data = response.results
            val nextPage = if (response.info.next.isNullOrEmpty()) null else page + 1
            LoadResult.Page(
                data = data,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = nextPage
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

}