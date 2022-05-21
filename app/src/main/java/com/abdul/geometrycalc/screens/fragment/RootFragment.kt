package com.abdul.geometrycalc.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.abdul.geometrycalc.R
import com.abdul.geometrycalc.databinding.FragmentRootBinding
import com.abdul.geometrycalc.db.Dao
import com.abdul.geometrycalc.db.DataBase
import com.abdul.geometrycalc.model.FigureModel
import com.abdul.geometrycalc.model.callbacks.RecyclerViewItemClickCallback
import com.abdul.geometrycalc.screens.adapters.RootAdapter

class RootFragment : Fragment() {

    private lateinit var binding: FragmentRootBinding

    private val callback = object : RecyclerViewItemClickCallback {
        override fun onItemClick(item: Any) {
            if (item is FigureModel) {
                findNavController().navigate(
                    R.id.secondFragment,
                    SecondFragmentArgs(item.id).toBundle()
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRootBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMain.adapter = RootAdapter(callback)

        val liveData = DataBase.getInstance(requireContext()).figureDao().getAllFigure()

        liveData.observe(
            viewLifecycleOwner,
            {
                (binding.rvMain.adapter as RootAdapter).setFigureList(it)

            }
        )

    }
}