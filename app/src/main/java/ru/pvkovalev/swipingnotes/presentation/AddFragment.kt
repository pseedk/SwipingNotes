package ru.pvkovalev.swipingnotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.FragmentAddBinding
import ru.pvkovalev.swipingnotes.utils.APP_ACTIVITY
import ru.pvkovalev.swipingnotes.utils.hideKeyboard
import ru.pvkovalev.swipingnotes.utils.resizeSoftInputMode
import ru.pvkovalev.swipingnotes.utils.showKeyboard

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteContent: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteContent = binding.noteContentAddEditText
        noteContent.requestFocus()
        showKeyboard(context, noteContent)
        onBackPressed()

        binding.apply {
            buttonOkAddLayout.setOnClickListener {
                hideKeyboard(context, noteContent)
                findNavController().navigate(R.id.action_addFragment_to_mainFragment)

            }
            buttonNotOkAddLayout.setOnClickListener {
                hideKeyboard(context, noteContent)
                findNavController().navigate(R.id.action_addFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = AddFragment()

    }

    private fun onBackPressed() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
               findNavController().navigate(R.id.action_addFragment_to_mainFragment)
            }
        }
        APP_ACTIVITY.onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

}