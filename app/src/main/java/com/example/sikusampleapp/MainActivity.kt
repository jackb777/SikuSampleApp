package com.example.sikusampleapp

import android.R.attr.value
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.sikusampleapp.data.model.Country
import com.example.sikusampleapp.ui.addcountry.AddCountryActivity
import com.example.sikusampleapp.ui.model.Error
import com.example.sikusampleapp.ui.model.Loading
import com.example.sikusampleapp.ui.model.State
import com.example.sikusampleapp.ui.model.Success
import com.example.sikusampleapp.ui.theme.SikuSampleAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCountries()
        setContent {
            SikuSampleAppTheme {
                val event = viewModel.state.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        event = event.value,
                        onFabClicked = { openFragment() }
                    )
                }
            }
        }
    }


    private fun openFragment() {
        val intent = Intent(this, AddCountryActivity::class.java)
        this.startActivity(intent)
    }
}


@Composable
fun HomeScreen(
    event: State,
    onFabClicked: () -> Unit
) {
    when (event) {
        is Error -> Error(message = event.errorMessage)
        is Loading -> Loading()
        is Success -> Success(event.data, onFabClicked)
    }
}


@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}


@Composable
fun Error(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            fontSize = 22.sp,
            text = message
        )
    }
}


@Composable
fun Success(
    countries: List<Country>,
    onFabClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Countries(modifier = Modifier.fillMaxSize(), countries = countries)
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { onFabClicked.invoke() }
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}


@Composable
fun Countries(modifier: Modifier, countries: List<Country>) {
    LazyColumn(modifier = modifier) {
        items(countries) {
            Country(modifier = Modifier.padding(16.dp), country = it)
            HorizontalDivider(color = Color.Gray)
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Country(modifier: Modifier, country: Country) {
    Row(modifier = modifier) {
        GlideImage(
            modifier = Modifier.size(48.dp),
            model = country.flag,
            contentDescription = "${country.name} flag",
            loading = placeholder(ColorPainter(Color.Gray)),
            failure = placeholder(ColorPainter(Color.Gray))
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = country.name, fontSize = 18.sp)
            if (country.capital != null) {
                Text(text = country.capital, fontSize = 14.sp, fontStyle = FontStyle.Italic)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    SikuSampleAppTheme {
        Loading()
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    SikuSampleAppTheme {
        Error(message = "Sorry, something went wrong")
    }
}


@Preview(showBackground = true)
@Composable
fun SuccessPreview() {
    SikuSampleAppTheme {
        Success(
            countries = listOf(
                Country(
                    name = "United Kingdom",
                    flag = "https://flagcdn.com/gs.svg",
                    capital = "London"
                ),
                Country(
                    name = "United State",
                    flag = "https://flagcdn.com/gs.svg",
                    capital = "Washington D.C"
                ),
                Country(
                    name = "Canada",
                    flag = "https://flagcdn.com/gs.svg",
                    capital = "Ottawa"
                )
            ),
            onFabClicked = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CountryPreview() {
    SikuSampleAppTheme {
        Country(
            Modifier.padding(16.dp),
            country = Country(
                name = "United Kingdom",
                flag = "https://flagcdn.com/gs.svg",
                capital = "London"
            )
        )
    }
}