package com.aqsin.cryptotracker.alert

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.aqsin.cryptotracker.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.crypto_min_max.view.*

object CryptoMinMaxAlert {

    interface MinMaxAction {
        fun onPopupCancel() {}
        fun onPopupConfirm(data: MinMaxData)
    }

    data class MinMaxData(
        var title: String,
        var minValue : Float,
        var maxValue : Float,
    )

    class BottomSheetPopUp(context: Context): BottomSheetDialog(context, R.style.SheetDialog)


    /**
     * Show CryptoMinMax bottom dialog. This method shows confirmation dialog with two button
     * @param context : [Context]-> main context of appilcation or fragment
     * @param confirmDatas : [MinMaxData]->  is responsible for data for showing
     * @param eventHandler : [MinMaxAction] -> is responsible for callback when any action occurred on Dialog
     */
    fun showMinMaxAlert(context: Context, minMaxData: MinMaxData, eventHandler: MinMaxAction) {
        val bottomSheetDialog = BottomSheetPopUp(context)
        val view = LayoutInflater.from(context).inflate(R.layout.crypto_min_max, null)
        bottomSheetDialog.setContentView(view)

        view.tv_crypto_title.text = minMaxData.title.uppercase()
        view.ed_crypto_min.setText(minMaxData.minValue.toString())
        view.ed_crypto_max.setText(minMaxData.maxValue.toString())
        view.btn_crypto_cancel.setOnClickListener {
            eventHandler.onPopupCancel()
            bottomSheetDialog.dismiss()
        }
        view.btn_crypto_confirm.setOnClickListener {
            takeIf {
                !view.ed_crypto_min.text.toString().isNullOrBlank()
                        && !view.ed_crypto_max.text.toString().isNullOrBlank()
            }?.apply {
                val updatedData = minMaxData.copy(minValue = view.ed_crypto_min.text.toString().toFloat())
                updatedData.maxValue = view.ed_crypto_max.text.toString().toFloat()
                eventHandler.onPopupConfirm(updatedData)
                bottomSheetDialog.dismiss()
            }
        }
        bottomSheetDialog.show()
    }
}