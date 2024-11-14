package com.visionforgestudio.taskflow.jcpackages.jcsoundplayer

import android.content.Context
import android.media.MediaPlayer

object JcSoundPlayer {
	private var mediaPlayer: MediaPlayer? = null
	
	fun playSound(context: Context, soundResId: Int, looping: Boolean = false) {
		// Release any existing player
		mediaPlayer?.release()
		
		// Get the resource ID of the sound file
		
		
		if (soundResId == 0) {
			// File not found, handle error appropriately
			println("Sound file not found")
			return
		}
		
		mediaPlayer = MediaPlayer.create(context, soundResId)
		
		mediaPlayer?.apply {
			isLooping = looping
			start()
			setOnCompletionListener {
				release()
				mediaPlayer = null
			}
		}
	}
	
	fun stopSound() {
		mediaPlayer?.stop()
		mediaPlayer?.release()
		mediaPlayer = null
	}
}