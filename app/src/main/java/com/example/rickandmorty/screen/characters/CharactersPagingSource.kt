package com.example.rickandmorty.screen.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.character.CharacterApi
import com.example.rickandmorty.data.model.character.Result

class CharactersPagingSource(
    private var charactersApi: CharacterApi
): PagingSource<Int, Result>() {

    companion object {
        const val FIRST_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: FIRST_PAGE

        return try {
            val response = charactersApi.getAllCharacters(page)
            val data = response.results
            val nextPage = if (data.isEmpty()) null else page + 1
            LoadResult.Page(
                data = data,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = nextPage
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return FIRST_PAGE
    }

}