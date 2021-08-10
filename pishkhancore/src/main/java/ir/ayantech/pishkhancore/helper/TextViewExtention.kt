package ir.ayantech.pishkhancore.helper

import android.os.Build
import android.text.Html
import android.widget.TextView

fun TextView.setHtmlText(html: String?) {
    text = when {
        html?.startsWith("<html") != true -> html
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(
            html,
            Html.FROM_HTML_MODE_COMPACT
        )
        else -> Html.fromHtml(html)
    }
}