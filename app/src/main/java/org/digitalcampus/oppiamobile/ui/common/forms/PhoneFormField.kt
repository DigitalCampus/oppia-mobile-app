package org.digitalcampus.oppiamobile.ui.common.forms

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import com.hbb20.CountryPickerView
import com.hbb20.countrypicker.models.CPCountry
import org.digitalcampus.oppiamobile.databinding.FormFieldPhoneBinding

class PhoneFormField(
    context: Context,
    field: org.digitalcampus.oppiamobile.domain.model.FormField? = null,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FormField(context, field, attrs, defStyleAttr) {

    private var binding: FormFieldPhoneBinding
    private var textFormField: TextFormField

    init {
        binding = FormFieldPhoneBinding.inflate(LayoutInflater.from(context))

        configCountryPicker(binding.countryPicker)

        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        gravity = Gravity.CENTER_VERTICAL

        textFormField = TextFormField(context, field, attrs, defStyleAttr)
        textFormField.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        addView(binding.root)
        addView(textFormField)
    }

    private fun configCountryPicker(countryPicker: CountryPickerView) {
        // Set dropdown text
        countryPicker.cpViewHelper.cpViewConfig.viewTextGenerator = {
                cpCountry: CPCountry ->
            "${cpCountry.alpha2} +${cpCountry.phoneCode}"
        }

        // Set phone code to country list items
        countryPicker.cpViewHelper.cpRowConfig.highlightedTextGenerator = {
                cpCountry: CPCountry ->
            "+${cpCountry.phoneCode}"
        }

        countryPicker.cpViewHelper.refreshView()
    }

    override fun setError(error: String) {
        textFormField.setError(error)
    }
}
