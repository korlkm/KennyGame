package com.myg.kennygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import com.myg.kennygame.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler : Handler = Handler()
    var runnable : Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. 레이아웃(root뷰) 표시
        setContentView(binding.root);



        score = 0

        imageArray = arrayListOf(binding.imageView, binding.imageView2, binding.imageView3, binding.imageView4, binding.imageView5, binding.imageView6, binding.imageView7, binding.imageView8, binding.imageView9)

        hideImages()

        object : CountDownTimer(30000, 1000) {
            override fun onFinish() {
                binding.timeText.text = "Time over"
                handler.removeCallbacks(runnable)
                for (image in imageArray)
                    image.visibility = View.INVISIBLE
            }
            override fun onTick(p0: Long) {
                binding.timeText.text = "Time: " + p0 / 1000
            }
        }.start()
    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val index = random.nextInt(8 - 0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)

            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score++

        binding.scoreText.text = "Score: " + score
    }
}