package com.groupe1.app_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.groupe1.app_android.data.repository.ListingRepositoryImpl
import com.groupe1.app_android.domain.usecase.listings.GetAllListingUseCase
import com.groupe1.app_android.domain.usecase.listings.ListingUseCases
import com.groupe1.app_android.ui.navigation.AppNav
import com.groupe1.app_android.ui.theme.ProjetandroidTheme
import com.groupe1.app_android.viewModels.ListingsViewModel

class MainActivity : ComponentActivity() {
    // Listings
    private val listingRepository = ListingRepositoryImpl()
    private val listingUseCases = ListingUseCases(
        GetAllListingUseCase(listingRepository)
    )
    private val listingsViewModel = ListingsViewModel(listingUseCases)

    // Chat
    private val chatRepository = com.groupe1.app_android.data.repository.ChatRepositoryImpl()
    private val chatUseCases = com.groupe1.app_android.domain.usecase.chat.ChatUseCases(
        com.groupe1.app_android.domain.usecase.chat.GetConversationsUseCase(chatRepository),
        com.groupe1.app_android.domain.usecase.chat.GetMessagesUseCase(chatRepository),
        com.groupe1.app_android.domain.usecase.chat.SendMessageUseCase(chatRepository),
        com.groupe1.app_android.domain.usecase.chat.CreateConversationUseCase(chatRepository),
        com.groupe1.app_android.domain.usecase.chat.ReactToMessageUseCase(chatRepository)
    )
    private val inboxViewModel = com.groupe1.app_android.viewModels.InboxViewModel(chatUseCases)
    private val chatViewModel = com.groupe1.app_android.viewModels.ChatViewModel(chatUseCases)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetandroidTheme(dynamicColor = false) {
                Surface {
                    val nav = rememberNavController()
                    AppNav(
                        nav,
                        listingsViewModel = listingsViewModel,
                        inboxViewModel = inboxViewModel,
                        chatViewModel = chatViewModel
                    )
                }
            }
        }
    }
}