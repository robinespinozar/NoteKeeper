package com.raerossi.notekeeper.ui.features.welcome

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.raerossi.notekeeper.R

sealed class OnBoardingPage(
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int
) {
    object First: OnBoardingPage(
        imageRes = R.drawable.first_onboarding_image,
        titleRes = R.string.first_onboarding_title ,
        descriptionRes = R.string.first_onboarding_description
    )
    object Second : OnBoardingPage(
        imageRes = R.drawable.second_onboarding_image,
        titleRes = R.string.second_onboarding_title,
        descriptionRes = R.string.second_onboarding_description
    )
    object Third : OnBoardingPage(
        imageRes = R.drawable.third_onboarding_image,
        titleRes = R.string.third_onboarding_title,
        descriptionRes = R.string.third_onboarding_description
    )
}