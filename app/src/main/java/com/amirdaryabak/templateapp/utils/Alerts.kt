package com.amirdaryabak.templateapp.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import es.dmoral.toasty.Toasty
import java.util.*

fun showLostConnectionDialog(context: Context, text: String = "اتصال شما به اینترنت برقرار نمیباشد")/*: Dialog*/ {
    /*val dialog = Dialog(context, R.style.AppTheme)
    try {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_lost_connection)
        dialog.textView3.text = text
        dialog.closeDialog.setOnClickListener {
            dialog.dismiss()
        }
        val window = dialog.window
        if (window != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setDimAmount(0.32F)
        }
    } catch (e: Exception) {

    }
    return dialog*/
}

fun toasty(context: Context, message: String, type: Int = 1, length: Int = Toast.LENGTH_SHORT) {
    /**
     * 1 normal
     * 2 success
     * 3 error
     * 4 info
     * 5 warning
     */
    when (type) {
        1 -> Toasty.normal(context, message, length).show()
        2 -> Toasty.success(context, message, length).show()
        3 -> Toasty.error(context, message, length).show()
        4 -> Toasty.info(context, message, length).show()
        5 -> Toasty.warning(context, message, length).show()
    }
}

fun timery(call: ()-> Unit,delay: Long){
    Timer().schedule(object: TimerTask(){
        override fun run() {
            Handler(Looper.getMainLooper()).post{
                call.invoke()
            }
        }
    },delay)
}