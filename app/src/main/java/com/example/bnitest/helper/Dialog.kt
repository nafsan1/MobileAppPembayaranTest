package com.example.bnitest.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.bnitest.R
import com.example.bnitest.databinding.DialogPaymentBinding
import com.example.core.data.History
import com.example.core.util.Utilities.formatRupiah
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

object Dialog {

    fun Context.dialogPayment(isAction: () -> Unit, history: History) {
        val bottomSheetDialog = BottomSheetDialog(
            this,
            R.style.AppBottomSheetDialogTheme
        )

        val binding = DialogPaymentBinding.inflate(LayoutInflater.from(this.applicationContext))


        bottomSheetDialog.setCancelable(true)
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetDialog.behavior.isDraggable = true

        binding.txtId.text = history.idTransaction
        binding.txtName.text = history.merchant
        binding.txtNominal.text = history.transaction.formatRupiah()
        binding.btnPayment.setOnClickListener {
            bottomSheetDialog.dismiss()
            isAction()
        }


        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.show()
    }
}