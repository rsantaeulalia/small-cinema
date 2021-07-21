package com.example.smallcinema.infra.dataprovider.http

import com.example.smallcinema.domain.dataprovider.OmdbDataProvider
import com.example.smallcinema.domain.exception.ImdbClientFailureException
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import java.util.*

@SpringBootTest
@ContextConfiguration(initializers = [WireMockContextInitializer::class])
class OmdbClientTest {

    @Autowired
    private lateinit var wireMockServer: WireMockServer

    @Autowired
    lateinit var omdbDataProvider: OmdbDataProvider

    var validImdbId = "tt1013752"
    var invalidImdbId = "invalidId"

    @AfterEach
    fun afterEach() {
        wireMockServer.resetAll()
    }

    @BeforeEach
    fun init() {
        val jsonResponse = OmdbClientTest::class.java.getResource("/samples/movieDetails.json").openStream()
        wireMockServer.stubFor(
            get(urlPathMatching("/apikey=(.*)\\&i=.*")).willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(Scanner(jsonResponse, "UTF-8").useDelimiter("\\A").next())
            )
        )
    }


    @Test
    fun givenAGetPackageDetailsToTrackingApiThenReturnAPackageDetailsList() {
        val movie = omdbDataProvider.getMovieDetailByImdbId(validImdbId)
        assertNotNull(movie)
    }

    @Test
    fun givenAGetPackageDetailsByInvalidCodeToTrackingApiThenReturnAnException() {
        wireMockServer.stubFor(
            get(urlPathMatching("/apikey=(.*)\\&i=$invalidImdbId")).willReturn(
                aResponse()
                    .withStatus(404)
                    .withHeader("Content-Type", "application/json")
            )
        )

        assertThrows(ImdbClientFailureException::class.java) {
            omdbDataProvider.getMovieDetailByImdbId(invalidImdbId)
        }
    }

}