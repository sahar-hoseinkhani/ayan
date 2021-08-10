package ir.ayantech.pishkhancore.helper

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.Glide
import ir.ayantech.whygoogle.helper.trying
import java.io.ByteArrayInputStream

private fun ImageView.setImageBase64(base64: String?) {
    if (base64 == null)
        return
    this.setImageBitmap(
        BitmapFactory.decodeStream(
            ByteArrayInputStream(
                Base64.decode(
                    base64, Base64.DEFAULT
                )
            )
        )
    )
}

private fun ImageView.loadUrl(url: String) {
    Glide.with(this.context).load(url).into(this)
//    val myOptions = RequestOptions()
//        .diskCacheStrategy(DiskCacheStrategy.NONE)
//        .timeout(20000)
////            .dontAnimate()
//        .skipMemoryCache(true)
//    val builder = Glide.with(context).load(Uri.parse(url)).apply(myOptions)
////    if (requestListener != null) builder.addListener(requestListener)
//    builder.into(this)
}

fun ImageView.loadFromString(source: String?) {
    if (source == null) {
        this.setImageResource(0)
        return
    }
    trying {
        if (source.lowercase().startsWith("http"))
            this.loadUrl(source)
        else
            this.setImageBase64(source.replace("data:image/jpg;base64,", ""))
    }
}
