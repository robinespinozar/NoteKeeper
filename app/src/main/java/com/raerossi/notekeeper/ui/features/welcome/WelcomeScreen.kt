package com.raerossi.notekeeper.ui.features.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.raerossi.notekeeper.ui.features.components.GradientButton
import com.raerossi.notekeeper.ui.features.components.PagerIndicator
import com.raerossi.notekeeper.ui.features.components.SetSystemColors
import com.raerossi.notekeeper.ui.features.components.TitleAndDescription
import com.raerossi.notekeeper.ui.features.components.VerticalSpacer
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import com.raerossi.notekeeper.ui.theme.neutralVariant90
import com.raerossi.notekeeper.ui.theme.primary20
import com.raerossi.notekeeper.ui.theme.primary70
import com.raerossi.notekeeper.ui.theme.primaryGradient
import com.raerossi.notekeeper.ui.theme.secondaryGradient

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
    onLogInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    SetSystemColors(colorStatusBar = Color(0xFFFFFFFF))
    val pages = listOf(OnBoardingPage.First, OnBoardingPage.Second, OnBoardingPage.Third)
    val pagerState = rememberPagerState()

    OnBoardingScreen(
        pages = pages,
        pagerState = pagerState,
        onLogInClick = {
            welcomeViewModel.updateOnBoardingState(completed = true)
            onLogInClick()
        },
        onSignUpClick = {
            welcomeViewModel.updateOnBoardingState(completed = true)
            onSignUpClick()
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    pages: List<OnBoardingPage>,
    pagerState: PagerState,
    onLogInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(8.5f),
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        PagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            numberOfPages = pages.size,
            selectedColor = MaterialTheme.colorScheme.primary70,
            inactiveColor = MaterialTheme.colorScheme.neutralVariant90,
            selectedPage = pagerState.currentPage
        )
        FinishButtons(
            modifier = Modifier.weight(2f),
            pagerState = pagerState,
            onLogInClick = { onLogInClick() },
            onSignUpClick = { onSignUpClick() }
        )
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(0.dp, 800.dp),
            painter = painterResource(id = onBoardingPage.imageRes),
            contentDescription = "pager image",
            contentScale = ContentScale.Crop
        )
        VerticalSpacer(20)
        TitleAndDescription(
            title = stringResource(id = onBoardingPage.titleRes),
            description = stringResource(id = onBoardingPage.descriptionRes),
            centerAlign = true,
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButtons(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onLogInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Column {
                GradientButton(
                    text = "Sign Up",
                    textColor = Color.White,
                    gradient = MaterialTheme.colorScheme.primaryGradient,
                    onClick = { onSignUpClick() }
                )
                VerticalSpacer(16)
                GradientButton(
                    text = "Log In",
                    textColor = MaterialTheme.colorScheme.primary20,
                    gradient = MaterialTheme.colorScheme.secondaryGradient,
                    onClick = { onLogInClick() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstOnboardingScreenPreview() {
    NoteKeeperTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            PagerScreen(onBoardingPage = OnBoardingPage.First)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondOnboardingScreenPreview() {
    NoteKeeperTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            PagerScreen(onBoardingPage = OnBoardingPage.Second)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdOnboardingScreenPreview() {
    NoteKeeperTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            PagerScreen(onBoardingPage = OnBoardingPage.Third)
        }
    }
}




