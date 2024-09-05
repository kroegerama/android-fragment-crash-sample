package com.kroegerama.crash.sample

import android.os.Bundle
import android.view.View
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Fade
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ExampleFragment : Fragment(R.layout.example_fragment) {

    private val args by navArgs<ExampleFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Fade().setDuration(600)
        exitTransition = Fade().setDuration(600)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val depth = args.depth
        view.findViewById<MaterialTextView>(R.id.tvText).text = buildSpannedString {
            bold {
                appendLine("Depth $depth")
            }
            if (depth >= 5) {
                append("Now repeatedly press the back button or use the back Gesture.")
            } else {
                append("Add some more Fragments to the stack.")
            }
        }

        view.findViewById<MaterialButton>(R.id.btnNext).setOnClickListener {
            findNavController().navigate(
                ExampleFragmentDirections.actionFragExampleSelf(
                    depth = args.depth + 1
                )
            )
        }
    }

}
