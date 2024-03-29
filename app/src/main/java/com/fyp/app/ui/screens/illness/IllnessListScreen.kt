package com.fyp.app.ui.screens.illness

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.destinations.HomeScreenDestination
import com.destinations.UserDetailsScreenDestination
import com.fyp.app.data.repository.Repository
import com.fyp.app.ui.components.Header
import com.fyp.app.ui.components.ListBoxIllness
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun IllnessListScreen(navigator: DestinationsNavigator) {
    val illness = remember { Repository.illness }
    Column {
        Header(
            onClickLogo = {
                navigator.navigate(HomeScreenDestination())
            },
            onClickAccount = {
                navigator.navigate(UserDetailsScreenDestination())
            }
        )
        ListBoxIllness(content = illness)
    }
}