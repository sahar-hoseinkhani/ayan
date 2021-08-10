package ir.ayantech.pishkhancore.helper

import android.view.ViewGroup
import androidx.transition.*

fun ViewGroup.delayedTransition(transition: Transition? = null) {
    if (transition == null) {
        TransitionManager.beginDelayedTransition(
            this,
            TransitionSet().apply {
                ordering = TransitionSet.ORDERING_SEQUENTIAL
                addTransition(ChangeBounds())
                addTransition(Fade(Fade.IN))
            }
        )
    } else
        TransitionManager.beginDelayedTransition(this, transition)
}