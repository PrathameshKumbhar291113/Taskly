package com.prathameshkumbhar.taskly.utils

import com.prathameshkumbhar.taskly.R
import kotlin.random.Random

fun randomColors(): Int{

    var colorList = ArrayList<Int>()
    colorList.add(R.color.primary_pink)
    colorList.add(R.color.primary_blue)

    val seed = System.currentTimeMillis().toInt()
    val randomColor = Random(seed).nextInt(colorList.size)
    return colorList[randomColor]

}