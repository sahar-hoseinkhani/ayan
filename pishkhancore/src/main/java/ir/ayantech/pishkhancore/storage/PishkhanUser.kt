import android.annotation.SuppressLint
import android.content.Context
import ir.ayantech.whygoogle.helper.PreferencesManager

@SuppressLint("StaticFieldLeak")
object PishkhanUser {
    private lateinit var context: Context

    const val PISHKHAN_USER_SESSION = "pishkhanUserSession"

    var session: String
        get() = PreferencesManager.getInstance(context).read(fieldName = PISHKHAN_USER_SESSION)
        set(value) = PreferencesManager.getInstance(context)
            .save(fieldName = PISHKHAN_USER_SESSION, value = value)


    fun init(context: Context) {
        PishkhanUser.context = context
    }
}