package com.example.anotherpokedex.core.di

import com.example.anotherpokedex.data.remote.PokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "pokeapi.co"
                    path("api/v2/")
                }
            }
        }
    }

    @Provides
    @Singleton
    fun providePokeApiService(client: HttpClient): PokeApiService {
        return PokeApiService(client)
    }
}