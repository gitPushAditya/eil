package com.visionforgestudio.taskflow.jcpackages.jclottie

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState

@Composable
fun JcLottieIcon(
	iconId: Int,
	iterations: Int = LottieConstants.IterateForever,
	isPlaying: Boolean = true,
	speed: Float = 1f,
	restartOnPlay: Boolean = false,
	modifier: Modifier = Modifier,
	size: Dp = 200.dp
){
	val preloaderLottieComposition by rememberLottieComposition(
		LottieCompositionSpec.RawRes(iconId)
	)
	
	val preloaderProgress by animateLottieCompositionAsState(
	preloaderLottieComposition,
		iterations = iterations,
		isPlaying = isPlaying,
		speed = speed,
		restartOnPlay = restartOnPlay
	)
	
	LottieAnimation(
		composition = preloaderLottieComposition,
		progress = { preloaderProgress },
		modifier = Modifier.size(
			size
		).then(modifier)
	)
}