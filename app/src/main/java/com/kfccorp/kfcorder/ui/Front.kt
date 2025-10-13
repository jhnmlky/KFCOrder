package com.kfccorp.kfcorder.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import androidx.navigation.fragment.findNavController
import com.kfccorp.kfcorder.databinding.FrontBinding

@SuppressLint("ClickableViewAccessibility")
class Front : Fragment() {

    private lateinit var binding: FrontBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrontBinding.inflate(inflater, container, false)

        binding.root.setOnTouchListener { _, event ->                                                   // HANDLE TOUCH EVENTS ANYWHERE ON ROOT
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    findNavController().navigate(FrontDirections.actionFrontToMenu())
                }
            }
            true
        }

        return binding.root
    }
}