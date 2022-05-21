package com.abdul.geometrycalc.screens.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.abdul.geometrycalc.R
import com.abdul.geometrycalc.db.DataBase
import com.abdul.geometrycalc.model.FigureFormulas
import com.agog.mathdisplay.MTMathView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.view.LayoutInflater
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.widget.*


class ExpressionFragment : Fragment() {
    private val TAG = javaClass.simpleName

    val args: ExpressionFragmentArgs by navArgs()

    lateinit var formula: FigureFormulas

    lateinit var parentView: NestedScrollView
    lateinit var constraintLayout: ConstraintLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentView = NestedScrollView(requireContext())

        parentView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        parentView.isFillViewport = true


        constraintLayout = ConstraintLayout(requireContext())
        constraintLayout.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        constraintLayout.updateLayoutParams<ConstraintLayout.LayoutParams> {
            marginStart = resources.getDimensionPixelSize(R.dimen.dp_12)
            marginEnd = resources.getDimensionPixelSize(R.dimen.dp_12)
            topMargin = resources.getDimensionPixelSize(R.dimen.dp_20)
            bottomMargin = resources.getDimensionPixelSize(R.dimen.dp_20)
        }

        parentView.addView(constraintLayout)

        return parentView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        formula = DataBase
            .getInstance(requireContext())
            .figureDao()
            .getFormulaDetails(args.idFormula)


        val imgView = ImageView(requireContext())
        imgView.id = View.generateViewId()
        imgView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            resources.getDimensionPixelSize(R.dimen.dp_150)
        )
        imgView.setImageResource(formula.img)
        imgView.adjustViewBounds = true



        // latex view
        val latex = MTMathView(requireContext())
        latex.id = View.generateViewId()
        latex.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        latex.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = imgView.id
        }

        latex.textAlignment = MTMathView.MTTextAlignment.KMTTextAlignmentCenter
        latex.fontSize = resources.getDimensionPixelSize(R.dimen.dp_26).toFloat()
        latex.latex = formula.laTex
        latex.setPadding(0, resources.getDimensionPixelSize(R.dimen.dp_12), 0, 0)





        // fields root
        val fields = LinearLayout(requireContext())
        fields.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        fields.orientation = LinearLayout.VERTICAL
        fields.id = View.generateViewId()
        fields.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = latex.id
        }

        // field
        formula.fields.forEachIndexed { i, f ->
            val field = createFieldView(i)
            fields.addView(field)
        }

        val latex2 = MTMathView(requireContext())
        latex2.id = View.generateViewId()
        latex2.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

//        latex2.updateLayoutParams<ConstraintLayout.LayoutParams> {
//            topToBottom = fields.id
//        }

//        latex2.latex = formula.laTex

        latex2.textAlignment = MTMathView.MTTextAlignment.KMTTextAlignmentCenter
        latex2.fontSize = resources.getDimensionPixelSize(R.dimen.dp_26).toFloat()
//        latex2.latex = formula.laTex
        latex2.setPadding(0, resources.getDimensionPixelSize(R.dimen.dp_12), 0, 0)



        val horizontalScrollView = HorizontalScrollView(requireContext())
        horizontalScrollView.id = View.generateViewId()
        horizontalScrollView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        horizontalScrollView.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = fields.id
        }

        horizontalScrollView.isFillViewport = true

        //horizontalScrollView.addView(latex2)

        val linearLayout = LinearLayout(requireContext())
        linearLayout.id = View.generateViewId()
        linearLayout.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        linearLayout.gravity = Gravity.CENTER



        // calc
        val calc = MaterialButton(requireContext())
        calc.id = View.generateViewId()
        calc.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        calc.updateLayoutParams<ConstraintLayout.LayoutParams> {
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        }
        calc.text = "Рассчитать"
        calc.setOnClickListener {
            var flag = true
            formula.fields.forEachIndexed { index, field ->
                val et = (fields.getChildAt(index) as TextInputLayout).editText!!
                field.value = et.text.toString()
                if (et.text.isNullOrEmpty()) {
                    flag = false
                }
            }
            if (flag) {
                formula.calc()
                val dialig = BottomSheetDialog.newInstance(formula)
                dialig.show(parentFragmentManager, "")
            }
        }

        //calc2
        val calc2 = MaterialButton(requireContext())
        calc2.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        calc2.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = horizontalScrollView.id
            bottomToTop = calc.id
            verticalBias = 1f
        }
        calc2.text = "Вычислить"
        calc2.setOnClickListener {
            var flag = true
            formula.fields.forEachIndexed { index, field ->
                val et = (fields.getChildAt(index) as TextInputLayout).editText!!
                field.value = et.text.toString()
                if (et.text.isNullOrEmpty()) {
                    flag = false
                }
            }
            if (flag) {
                latex.latex = formula.laTex
                formula.tmpLatex = formula.extLaTex

                formula.fields.forEach {
                    formula.tmpLatex = formula.tmpLatex.replace(it.str, it.value)
                }

                latex2.latex = formula.tmpLatex + "=${formula.calc()}"
            }
        }


        //-------------------------------------------------------------


        // ad views
        linearLayout.addView(latex2)
        horizontalScrollView.addView(linearLayout)
        constraintLayout.addView(imgView)
        constraintLayout.addView(latex)
        constraintLayout.addView(fields)
        constraintLayout.addView(horizontalScrollView)
        constraintLayout.addView(calc2)
        constraintLayout.addView(calc)


    }

    private fun calculate(fields: LinearLayout) {
        var flag = true
        formula.fields.forEachIndexed { index, field ->
            val et = (fields.getChildAt(index) as TextInputLayout).editText!!
            field.value = et.text.toString()
            if (et.text.isNullOrEmpty()) {
                flag = false
            }
        }
        if (flag) {


            val linerLayout = LinearLayout(requireContext())
            linerLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            linerLayout.gravity = Gravity.CENTER
            linerLayout.orientation = LinearLayout.VERTICAL
            linerLayout.updateLayoutParams<LinearLayout.LayoutParams> {
                marginStart = resources.getDimensionPixelSize(R.dimen.dp_12)
                marginEnd = resources.getDimensionPixelSize(R.dimen.dp_12)
                topMargin = resources.getDimensionPixelSize(R.dimen.dp_20)
                bottomMargin = resources.getDimensionPixelSize(R.dimen.dp_20)
            }
            //constraintLayout2.minHeight = resources.getDimensionPixelSize(R.dimen.dp_300)




            val imgView = ImageView(requireContext())
            imgView.id = View.generateViewId()
            imgView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                resources.getDimensionPixelSize(R.dimen.dp_150)
            )
            imgView.setImageResource(formula.img)
            imgView.adjustViewBounds = true
            linerLayout.addView(imgView)



            val latex = MTMathView(requireContext())
            latex.id = View.generateViewId()
            latex.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )



            latex.textAlignment = MTMathView.MTTextAlignment.KMTTextAlignmentCenter
            latex.fontSize = resources.getDimensionPixelSize(R.dimen.dp_26).toFloat()
            latex.latex = formula.laTex

            latex.setPadding(0, resources.getDimensionPixelSize(R.dimen.dp_12), 0, 0)

            linerLayout.addView(latex)

            val textView = TextView(requireContext())
            textView.id = View.generateViewId()
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26f)

            textView.gravity = Gravity.CENTER

            textView.setTextColor(resources.getColor(R.color.black))

            val formulArgs =
                DataBase.getInstance(requireContext()).figureDao().getFormulArgumrnt(args.idFormula)

            formula.fields.forEachIndexed { index, f ->
                textView.setText(textView.text.toString() + "\n${formulArgs.get(index).hint} = ${f.value}")
            }
            linerLayout.addView(textView)


            val textView2 = TextView(requireContext())
            textView2.id = View.generateViewId()
            textView2.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26f)
            textView2.gravity = Gravity.CENTER
            textView.setText("Результат:")
            linerLayout.addView(textView2)

            val settingsDialog: AlertDialog =
                AlertDialog.Builder(activity)
                    .setView(linerLayout)
                    .setPositiveButton(
                        "ok",
                        DialogInterface.OnClickListener { dialogInterface, i -> // Do sth here
                            dialogInterface.dismiss()
                        })
                    .setNegativeButton("cancel",
                        DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
                    .create()

            settingsDialog.show()





                        val constraintLayout = ConstraintLayout(requireContext())
            constraintLayout.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            val img = ImageView(requireContext())
            img.id = View.generateViewId()
            img.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                resources.getDimensionPixelSize(R.dimen.dp_150)
            )
            imgView.setImageResource(formula.img)
            imgView.adjustViewBounds = true
            constraintLayout.addView(imgView)


            val latexInfo = MTMathView(requireContext())
            latexInfo.id = View.generateViewId()
            latexInfo.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            latexInfo.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = imgView.id
            }
            latexInfo.textAlignment = MTMathView.MTTextAlignment.KMTTextAlignmentCenter
            latexInfo.fontSize = resources.getDimensionPixelSize(R.dimen.dp_26).toFloat()
            latexInfo.latex = formula.laTex
            latexInfo.setPadding(0, resources.getDimensionPixelSize(R.dimen.dp_12), 0, 0)


            val textViewInfo = TextView(requireContext())
            textViewInfo.id = View.generateViewId()
            textView.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            textViewInfo.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = latexInfo.id
            }
            textView.gravity = Gravity.CENTER
            textView.textSize = resources.getDimensionPixelSize(R.dimen.dp_8).toFloat()
            textView.text = "Ррезультат"
            textView.setPadding(0,resources.getDimensionPixelSize(R.dimen.dp_26),0,0)

            //-------------------------- InputText --------------------------------

            val latexInfo2 = MTMathView(requireContext())
            latexInfo2.id = View.generateViewId()
            latexInfo2.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            latexInfo2.textAlignment = MTMathView.MTTextAlignment.KMTTextAlignmentCenter
            latexInfo2.fontSize = resources.getDimension(R.dimen.dp_12)
            latexInfo2.setPadding(0,resources.getDimensionPixelSize(R.dimen.dp_26),0,0)


















        }
    }



    private fun createFieldView(index: Int): TextInputLayout {

        val textInputLayout = TextInputLayout(
            ContextThemeWrapper(
                requireContext(),
                R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
            )
        )
        textInputLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT

        )
        textInputLayout.updateLayoutParams<LinearLayout.LayoutParams> {
            topMargin = resources.getDimensionPixelSize(R.dimen.dp_12)

        }
        textInputLayout.boxBackgroundColor = ContextCompat.getColor(
            context!!,
            android.R.color.white
        )
        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
        val formulArgs =
            DataBase.getInstance(requireContext()).figureDao().getFormulArgumrnt(args.idFormula)
        try {
            formula.fields.get(index).hint = formulArgs.get(index).hint
            formula.fields.get(index).symbol = formulArgs.get(index).symbol
            formula.fields.get(index).color = formulArgs.get(index).color
            textInputLayout.hint = formulArgs.get(index).hint
        } catch (e: IndexOutOfBoundsException) {
        }

        textInputLayout.boxStrokeColor = Color.parseColor(formulArgs.get(index).color)
        textInputLayout.defaultHintTextColor = ColorStateList.valueOf(
Color.parseColor(formulArgs.get(index).color))

        val et = TextInputEditText(textInputLayout.context)
        et.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        et.inputType = InputType.TYPE_CLASS_NUMBER

        textInputLayout.addView(et)

        return textInputLayout
    }

}
