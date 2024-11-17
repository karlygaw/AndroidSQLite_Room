package kz.narxoz.android1

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.airbnb.lottie.compose.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon

@Composable
fun DetailScreen(
    navController: NavController,
    title: String,
    description: String,
    date: String,
    image: Int? = null,
    reviews: List<String>
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.TopStart),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Назад")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            image?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "Изображение",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            } ?: run {
                Spacer(modifier = Modifier.height(200.dp))
                Text(text = "Изображение отсутствует", style = MaterialTheme.typography.bodyMedium)
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(text = "Отзывы студентов", style = MaterialTheme.typography.titleMedium)

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(reviews) { review ->
                    AnimatedVisibility(
                        visible = true,
                        enter = slideInVertically(initialOffsetY = { -40 }),
                        exit = slideOutVertically(targetOffsetY = { -40 })
                    ) {
                        ReviewItem(review)
                    }
                }
            }
        }
    }

    val (isLoading, setIsLoading) = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(1500)
        setIsLoading(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (!isLoading) {
            // Main content of the DetailScreen
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                // Your existing detail screen layout
            }
        }

        // Loading overlay
        LoadingOverlay(isLoading = isLoading)
    }
}
@Composable
fun ReviewItem(review: String) {
    // State to control animation visibility
    var isLiked by remember { mutableStateOf(false) }
    var showFireworks by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = review,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(8.dp))

            Box(modifier = Modifier.size(70.dp)) {  // Size of the Box for layout purposes
                // Like icon (smaller)
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Like",
                    modifier = Modifier
                        .size(30.dp) // Decreased size of the like icon
                        .align(Alignment.Center)
                        .clickable {
                            isLiked = !isLiked
                            showFireworks = true // Trigger fireworks animation
                        },
                    tint = if (isLiked) Color.Red else Color.Gray // Change color when liked
                )

                // Fireworks animation (overlayed on top of the like icon)
                if (showFireworks) {
                    FireworksAnimation { showFireworks = false }
                }
            }
        }
    }
}

@Composable
fun FireworksAnimation(onAnimationEnd: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("like.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1 // Play the animation once
    )

    // Use LaunchedEffect to trigger the end of animation
    LaunchedEffect(progress) {
        if (progress == 1f) { // Check if animation has finished
            onAnimationEnd() // Call when animation ends
        }
    }

    // Increase only the size of the fireworks animation
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.size(300.dp) // Increased size of the fireworks animation
    )
}
