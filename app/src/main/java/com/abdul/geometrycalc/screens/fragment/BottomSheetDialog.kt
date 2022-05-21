package com.abdul.geometrycalc.screens.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import com.abdul.geometrycalc.R
import com.abdul.geometrycalc.model.FigureFormulas
import com.agog.mathdisplay.MTMathView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.text.Html
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButton

import android.os.Build
import android.view.ContextThemeWrapper
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat.setBackgroundTintList


class BottomSheetDialog : BottomSheetDialogFragment() {

    lateinit var formula: FigureFormulas

    companion object {
        fun newInstance(f: FigureFormulas): BottomSheetDialog {
            val d = BottomSheetDialog()
            d.formula = f
            return d
        }
    }

    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            // to do nothing
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("ResourceAsColor", "ResourceType", "UseCompatLoadingForDrawables")
    override fun setupDialog(dialog: Dialog, style: Int) {


        val constraintLayout = ConstraintLayout(requireContext())
        constraintLayout.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )


        val view = View(requireContext())
        view.id = View.generateViewId()
        view.layoutParams = ConstraintLayout.LayoutParams(
            resources.getDimensionPixelSize(R.dimen.dp_45),
            resources.getDimensionPixelSize(R.dimen.dp_4)
        )
        view.setBackgroundResource(R.drawable.bottom_sheet_divider)
//        view.setBackgroundColor(resources.getColor(R.color.material_dynamic_neutral50))
        view.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topMargin = resources.getDimensionPixelSize(R.dimen.dp_12)
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        }
        constraintLayout.addView(view)


        val img = ImageView(requireContext())
        img.id = View.generateViewId()
        img.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            resources.getDimensionPixelSize(R.dimen.dp_150)
        )
        img.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = view.id
        }
        img.setPadding(0, resources.getDimensionPixelSize(R.dimen.dp_12), 0, 0)
        img.setImageResource(formula.img)
        img.adjustViewBounds = true
        constraintLayout.addView(img)


        val latexInfo = MTMathView(requireContext())
        latexInfo.id = View.generateViewId()
        latexInfo.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        latexInfo.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = img.id
        }
        latexInfo.textAlignment = MTMathView.MTTextAlignment.KMTTextAlignmentCenter
        latexInfo.fontSize = resources.getDimensionPixelSize(R.dimen.dp_26).toFloat()
        latexInfo.latex = formula.laTex
        latexInfo.setPadding(0, resources.getDimensionPixelSize(R.dimen.dp_12), 0, 0)
        constraintLayout.addView(latexInfo)


        val textViewArg = TextView(requireContext())
        textViewArg.id = View.generateViewId()
        textViewArg.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        textViewArg.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = latexInfo.id
        }
        //-------------
        var args = ""
        formula.fields.forEach {
//            textViewArg.text = textViewArg.text.toString() + "\n" + it.symbol + "-" + it.hint + " = " + it.value
            args = args + "<br>" +
                    "<font color='${it.color}'>${it.symbol}</font> " + "-" + it.hint + " = " + it.value
        }
        textViewArg.setText(Html.fromHtml(args), TextView.BufferType.SPANNABLE)

        textViewArg.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        textViewArg.gravity = Gravity.CENTER
        textViewArg.textSize = resources.getDimensionPixelSize(R.dimen.dp_8).toFloat()
        textViewArg.setPadding(
            0,
            resources.getDimensionPixelSize(R.dimen.dp_6),
            0,
            0
        )
        constraintLayout.addView(textViewArg)


        val textViewInfo = TextView(requireContext())
        textViewInfo.id = View.generateViewId()
        textViewInfo.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        textViewInfo.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = textViewArg.id
        }
        textViewInfo.gravity = Gravity.CENTER
        textViewInfo.textSize = resources.getDimensionPixelSize(R.dimen.dp_8).toFloat()
        textViewInfo.text = "Результат:"
        textViewInfo.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        textViewInfo.setPadding(0, resources.getDimensionPixelSize(R.dimen.dp_26), 0, 0)
        constraintLayout.addView(textViewInfo)


        val latexInfo2 = MTMathView(requireContext())
        latexInfo2.id = View.generateViewId()
        latexInfo2.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
//        latexInfo2.updateLayoutParams<ConstraintLayout.LayoutParams> {
//            topToBottom = textViewInfo.id
//        }
        latexInfo2.fontSize = resources.getDimensionPixelSize(R.dimen.dp_26).toFloat()
        latexInfo2.textAlignment = MTMathView.MTTextAlignment.KMTTextAlignmentCenter
        latexInfo2.setPadding(
            0,
            resources.getDimensionPixelSize(R.dimen.dp_26),
            0,
            resources.getDimensionPixelSize(R.dimen.dp_26)
        )
        formula.tmpLatex = formula.laTex
        formula.fields.forEach {
            formula.tmpLatex = formula.tmpLatex.replace(it.str, it.value)
        }
        latexInfo2.latex = formula.tmpLatex + "=${formula.calc()}"
//        constraintLayout.addView(latexInfo2)


        val linearLayout = LinearLayout(requireContext())
        linearLayout.id = View.generateViewId()
        linearLayout.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        linearLayout.gravity = Gravity.CENTER
        linearLayout.addView(latexInfo2)


        val horizontalScrollView = HorizontalScrollView(requireContext())
        horizontalScrollView.id = View.generateViewId()
        horizontalScrollView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        horizontalScrollView.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = textViewInfo.id
        }
        horizontalScrollView.isFillViewport = true
        horizontalScrollView.addView(linearLayout)
        constraintLayout.addView(horizontalScrollView)

        val share = MaterialButton(
            context!!,
            null,
            R.attr.materialButtonOutlinedStyle
        )
        share.id = View.generateViewId()
        share.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT

        )
        share.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = horizontalScrollView.id
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            bottomMargin = resources.getDimensionPixelSize(R.dimen.dp_12)
            marginEnd = resources.getDimensionPixelSize(R.dimen.dp_12)
        }
        share.text = "Share"
        share.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_share_24, 0, 0, 0)
        share.strokeColor =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.purple_500))
        share.supportCompoundDrawablesTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.purple_500))
        share.iconTint = ContextCompat.getColorStateList(requireContext(), R.color.purple_500)
        constraintLayout.addView(share)
        share.setOnClickListener {
            var text = ""
            formula.tmpLatex = formula.laTex
            formula.fields.forEach {
                formula.tmpLatex = formula.tmpLatex.replace(it.str, it.value)
                text =
                    text + "\n" + formula.shareFormula + "\n\n" + it.symbol + "-" + it.hint + " = " + it.value.toString() + "\n\n" + "Результат:\n" + formula.tmpLatex + "=${formula.calc()}"
            }
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share via..."))
        }



        dialog.setContentView(constraintLayout)

        var mBottomSheetBehavior = BottomSheetBehavior.from(constraintLayout.parent as View)
        mBottomSheetBehavior.addBottomSheetCallback(mBottomSheetBehaviorCallback)


    }

}