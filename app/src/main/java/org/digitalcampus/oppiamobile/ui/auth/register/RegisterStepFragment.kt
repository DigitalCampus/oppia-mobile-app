package org.digitalcampus.oppiamobile.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.digitalcampus.oppiamobile.databinding.FragmentRegisterStepBinding
import org.digitalcampus.oppiamobile.domain.model.FormFieldType
import org.digitalcampus.oppiamobile.ui.common.AppFragment
import org.digitalcampus.oppiamobile.ui.common.forms.FormField
import org.digitalcampus.oppiamobile.ui.common.forms.PhoneFormField
import org.digitalcampus.oppiamobile.ui.common.forms.TextFormField

class RegisterStepFragment : AppFragment() {

    private val viewModel: RegisterViewModel by activityViewModels()

    private lateinit var binding: FragmentRegisterStepBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRegisterStepBinding.inflate(inflater, container, false)
        updateUI(0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
                for (error in state.errors) {
                    val v = view.findViewById<FormField>(error.key.hashCode())
                    v.setError(error.value)
                }
            }
        }
    }

    fun updateUI(step: Int) {
        binding.stepDescription.text = viewModel.getStepDescription(step)

        binding.root.children.filterIsInstance<FormField>().forEach {
            it.visibility = View.GONE
        }

        val fields = viewModel.getFieldsByStepNumber(step)

        for (field in fields) {
            val view = binding.root.children.find { it.id == field.name.hashCode() }
            if (view != null) {
                view.visibility = View.VISIBLE
            } else {
                val formField = when (field.type) {
                    FormFieldType.TEXT,
                    FormFieldType.PASSWORD,
                    FormFieldType.EMAIL,
                    FormFieldType.NUMBER,
                    -> TextFormField(requireContext(), field)
                    FormFieldType.PHONE -> PhoneFormField(requireContext(), field)
                }

                binding.root.addView(formField)
            }
        }
    }
}
