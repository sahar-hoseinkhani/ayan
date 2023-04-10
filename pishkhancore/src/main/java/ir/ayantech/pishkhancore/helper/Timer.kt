package ir.ayantech.pishkhancore.helper

import android.os.CountDownTimer
import android.widget.TextView
import java.util.concurrent.TimeUnit

fun startTimer(
    millisInFuture: Long,
    countDownInterval: Long = 1000,
    onTick: (millisUntilFinished: Long) -> Unit,
    onFinish: () -> Unit
): CountDownTimer? {
    val timer = object : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onTick(millisUntilFinished: Long) {
            onTick.invoke(millisUntilFinished)
        }

        override fun onFinish() {
            onFinish.invoke()
        }

    }.start()
    return timer
}

fun TextView.startTimer(
    millisInFuture: Long,
    countDownInterval: Long = 1000,
    onTick: ((millisUntilFinished: Long, time: String) -> Unit)? = null,
    onFinish: (() -> Unit)? = null
): CountDownTimer? {
    val timer = object : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onTick(millisUntilFinished: Long) {
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
            val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
            val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
            val time = "$days:$hours:$minutes:$seconds"
            this@startTimer.text = time
            onTick?.invoke(millisUntilFinished, time)
        }

        override fun onFinish() {
            onFinish?.invoke()
        }

    }.start()
    return timer
}