package com.abdul.geometrycalc.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abdul.geometrycalc.R
import com.abdul.geometrycalc.databinding.FragmentSecondBinding
import com.abdul.geometrycalc.db.DataBase
import com.abdul.geometrycalc.model.FigureFormulas
import com.abdul.geometrycalc.model.callbacks.RecyclerViewItemClickCallback
import com.abdul.geometrycalc.screens.adapters.SecondAdapter


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private val callback = object : RecyclerViewItemClickCallback {
        override fun onItemClick(item: Any) {
            if (item is FigureFormulas) {
                findNavController().navigate(
                    R.id.expressionFragment,
                    ExpressionFragmentArgs(item.id).toBundle()
                )
            }
        }
    }

    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//       val formula = DataBase.getInstance(requireContext()).figureDao().getFigureFormula(args.idFigure)

        binding.rvSecond.adapter = SecondAdapter(callback)

        val liveData = DataBase.getInstance(requireContext()).figureDao().getFigureFormula(args.idFigure)

        liveData.observe(
            viewLifecycleOwner,
            {
                (binding.rvSecond.adapter as SecondAdapter).setFormulList(it)

            }
        )


    }
}