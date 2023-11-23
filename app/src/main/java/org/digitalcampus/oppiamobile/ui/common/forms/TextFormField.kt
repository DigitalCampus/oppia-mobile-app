package org.digitalcampus.oppiamobile.ui.common.forms

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import org.digitalcampus.oppiamobile.databinding.FormFieldTextBinding
import org.digitalcampus.oppiamobile.domain.model.FormFieldType
import org.digitalcampus.oppiamobile.utils.markRequiredInRed

class TextFormField(
    context: Context,
    field: org.digitalcampus.oppiamobile.domain.model.FormField? = null,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FormField(context, field, attrs, defStyleAttr) {

    private var binding: FormFieldTextBinding

    init {
        binding = FormFieldTextBinding.inflate(LayoutInflater.from(context))
        binding.layout.isErrorEnabled = true
        binding.layout.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        if (field != null) {
            binding.layout.hint = field.label
            binding.layout.helperText = field.helperText

            if (field.required) {
                binding.layout.markRequiredInRed()
            }

            binding.field.addTextChangedListener {
                    text ->
                field.value = text.toString()
            }

            when (field.type) {
                FormFieldType.TEXT -> binding.field.inputType = android.text.InputType.TYPE_CLASS_TEXT
                FormFieldType.PASSWORD -> {
                    binding.field.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                    binding.layout.endIconMode = END_ICON_PASSWORD_TOGGLE
                }
                FormFieldType.EMAIL -> binding.field.inputType = android.text.InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
                FormFieldType.NUMBER -> binding.field.inputType = android.text.InputType.TYPE_CLASS_NUMBER
                FormFieldType.PHONE -> { binding.field.inputType = android.text.InputType.TYPE_CLASS_PHONE }
            }
        }
        addView(binding.root)
    }

    override fun setError(error: String) {
        binding.layout.error = error
    }
}
