package ir.ayantech.pishkhancore.helper

fun <T> List<T>.safeGet(position: Int) =
    if (position < size) this[position]
    else null
