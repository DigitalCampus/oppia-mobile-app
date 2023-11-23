package org.digitalcampus.oppiamobile.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.aceinteract.android.stepper.StepperNavListener
import kotlinx.coroutines.launch
import org.digitalcampus.oppiamobile.R
import org.digitalcampus.oppiamobile.databinding.FragmentRegisterBinding
import org.digitalcampus.oppiamobile.ui.auth.AuthActivity
import org.digitalcampus.oppiamobile.ui.common.AppFragment

class RegisterFragment : AppFragment(), StepperNavListener
//    , RegisterTask.RegisterListener
{
    companion object {
        const val REGISTRATION_CONFIG = "registration_config.json"
    }

    private val viewModel: RegisterViewModel by activityViewModels()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.stepper.stepperNavListener = this

        val assetManager = requireContext().assets
        val inputStream = assetManager.open(REGISTRATION_CONFIG)

        viewModel.parseJson(inputStream.bufferedReader().use { it.readText() })
        for (step in viewModel.registerSteps) {
            binding.stepper.menu.add(View.generateViewId(), View.generateViewId(), step.order, step.title)
        }

        binding.previous.setOnClickListener { binding.stepper.goToPreviousStep() }
        binding.next.setOnClickListener {
            val valid = viewModel.validateStep(binding.stepper.currentStep)
            if (valid) {
                binding.stepper.goToNextStep()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->

                if (state.registerSuccess) {
                    viewModel.uiState.value.user?.let { user ->
                        (activity as AuthActivity).onSuccessUserAccess(user, true)
                    }
                    toast("Register success")
                }
            }
        }
    }

    override fun onCompleted() {
        viewModel.registrationCompleted()
    }

    override fun onStepChanged(step: Int) {
        binding.previous.isVisible = step != 0

        val navHostFragment = childFragmentManager.findFragmentById(R.id.frame_stepper) as NavHostFragment
        val currentFragment = navHostFragment.childFragmentManager.primaryNavigationFragment

        if (currentFragment is RegisterStepFragment) {
            currentFragment.updateUI(step)
        }
    }

//    private var fields: HashMap<String, ValidableField> = HashMap<String, ValidableField>()
//    private var fieldsManager: CustomFieldsUIManager? = null
//    private var stepsManager: SteppedFormUIManager? = null
//    private var binding: FragmentRegisterBinding? = null
//
//    @Inject
//    var customFieldsRepo: CustomFieldsRepository? = null
//
//    @Inject
//    var apiEndpoint: ApiEndpoint? = null
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentRegisterBinding.inflate(inflater, container, false)
//        binding.registerFormEmailField.setCustomValidator { field ->
//            val email: String = field.getCleanedValue()
//            if (!TextUtilsJava.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                binding.registerFormEmailField.setErrorEnabled(true)
//                binding.registerFormEmailField.setError(getString(R.string.error_register_email))
//                return@setCustomValidator false
//            }
//            true
//        }
//        binding.ccp.registerCarrierNumberEditText(binding.registerFormPhonenoEdittext)
//        val phoneInput: View = binding.registerFormPhonenoField.getChildAt(0)
//        binding.registerFormPhonenoField.removeView(phoneInput)
//        phoneInput.layoutParams = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        binding.fieldPhonenoContainer.addView(phoneInput)
//        binding.registerFormPhonenoField.setCustomValidator { field ->
//            val phoneNo: String = field.getCleanedValue()
//            if (phoneNo.length > 0 && !binding.ccp.isValidFullNumber()) {
//                binding.registerFormPhonenoField.setErrorEnabled(true)
//                binding.registerFormPhonenoField.setError(getString(R.string.error_register_no_phoneno))
//                binding.registerFormPhonenoField.requestFocus()
//                return@setCustomValidator false
//            }
//            true
//        }
//        binding.registerFormPasswordField.setCustomValidator { field ->
//            val password: String = binding.registerFormPasswordField.getCleanedValue()
//            val passwordAgain: String = binding.registerFormPasswordAgainField.getCleanedValue()
//            checkPasswordCriteria(password, passwordAgain)
//        }
//        binding.registerFormUsernameField.setCustomValidator { field ->
//            val validValue =
//                !TextUtilsJava.isEmpty(field.getCleanedValue()) && field.getCleanedValue()
//                    .length() >= App.USERNAME_MIN_CHARACTERS
//            if (!validValue) {
//                binding.registerFormUsernameField.setError(
//                    getString(
//                        R.string.error_register_username_length,
//                        App.USERNAME_MIN_CHARACTERS
//                    )
//                )
//            }
//            validValue
//        }
//        fields = HashMap<String, ValidableField>()
//        fields["username"] = binding.registerFormUsernameField
//        fields["email"] = binding.registerFormEmailField
//        fields["password"] = binding.registerFormPasswordField
//        fields["passwordagain"] = binding.registerFormPasswordAgainField
//        fields["first_name"] = binding.registerFormFirstnameField
//        fields["last_name"] = binding.registerFormLastnameField
//        fields["jobtitle"] = binding.registerFormJobtitleField
//        fields["organisation"] = binding.registerFormOrganisationField
//        fields["phoneno"] = binding.registerFormPhonenoField
//        binding.registerFormUsernameField.setHelperText(
//            getString(
//                R.string.error_register_username_length,
//                App.USERNAME_MIN_CHARACTERS
//            )
//        )
//        binding.registerFormPasswordField.setHelperText(
//            getString(
//                R.string.error_register_password,
//                App.PASSWORD_MIN_LENGTH
//            )
//        )
//        return binding.getRoot()
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        getAppComponent().inject(this)
//        val requiredFields: List<String> = customFieldsRepo.getRequiredFields(getContext())
//        for (f in requiredFields) {
//            val field: ValidableField? = fields[f]
//            if (field != null) {
//                field.setRequired(true)
//            }
//        }
//        val profileCustomFields: List<CustomField> = customFieldsRepo.getAll(getContext())
//        val registerSteps: List<CustomField.RegisterFormStep> =
//            customFieldsRepo.getRegisterSteps(getContext())
//        fieldsManager = CustomFieldsUIManager(this.getActivity(), fields, profileCustomFields)
//        fieldsManager.populateAndInitializeFields(binding.customFieldsContainer)
//        if (registerSteps == null || registerSteps.isEmpty()) {
//            binding.frameStepperIndicator.setVisibility(View.GONE)
//            binding.loginContainer.setVisibility(View.VISIBLE)
//        } else {
//            stepsManager =
//                SteppedFormUIManager(binding.stepperIndicator, registerSteps, fieldsManager)
//            binding.frameStepperIndicator.setVisibility(View.VISIBLE)
//            binding.loginContainer.setVisibility(View.GONE)
//            binding.registerBtn.setVisibility(View.GONE)
//            binding.nextBtn.setVisibility(View.VISIBLE)
//            binding.prevBtn.setVisibility(View.INVISIBLE)
//            stepsManager.initialize(
//                binding.customFieldsContainer,
//                binding.steppedFieldsContainer,
//                binding.stepDescription
//            )
//        }
//        binding.registerBtn.setOnClickListener { v -> onRegisterClick() }
//        binding.nextBtn.setOnClickListener { v -> nextStep() }
//        binding.prevBtn.setOnClickListener { v -> prevStep() }
//        binding.actionLoginBtn.setOnClickListener { v ->
//            val activity: WelcomeActivity? =
//                super@RegisterFragment.getActivity() as WelcomeActivity?
//            if (activity != null) {
//                activity.switchTab(WelcomeActivity.TAB_LOGIN)
//            }
//        }
//        for (field in fields.values) {
//            field.initialize()
//        }
//    }
//
//    private fun nextStep() {
//        UIUtils.hideSoftKeyboard(getActivity())
//        if (stepsManager.nextStep()) {
//            binding.prevBtn.setVisibility(View.VISIBLE)
//        }
//        if (stepsManager.isLastStep()) {
//            binding.nextBtn.setVisibility(View.GONE)
//            binding.registerBtn.setVisibility(View.VISIBLE)
//        }
//    }
//
//    private fun prevStep() {
//        UIUtils.hideSoftKeyboard(getActivity())
//        if (stepsManager.prevStep()) {
//            binding.prevBtn.setVisibility(View.INVISIBLE)
//        }
//        binding.registerBtn.setVisibility(View.GONE)
//        binding.nextBtn.setVisibility(View.VISIBLE)
//    }
//
//    fun onRegisterClick() {
//        var valid = true
//        if (stepsManager == null) {
//            // Only validate fields if we are not in a stepped form
//            // (if the form was stepped, we already validated each conditional step)
//            for (field in fields.values) {
//                valid = field.validate() && valid
//            }
//            valid = fieldsManager.validateFields() && valid
//        } else {
//            valid = stepsManager.validate()
//        }
//        if (valid) {
//            val u = User()
//            u.setUsername(binding.registerFormUsernameField.getCleanedValue())
//            u.setPassword(binding.registerFormPasswordField.getCleanedValue())
//            u.setPasswordAgain(binding.registerFormPasswordAgainField.getCleanedValue())
//            u.setFirstname(binding.registerFormFirstnameField.getCleanedValue())
//            u.setLastname(binding.registerFormLastnameField.getCleanedValue())
//            u.setEmail(binding.registerFormEmailField.getCleanedValue())
//            u.setJobTitle(binding.registerFormJobtitleField.getCleanedValue())
//            u.setOrganisation(binding.registerFormOrganisationField.getCleanedValue())
//            u.setPhoneNo(binding.ccp.getFormattedFullNumber())
//            u.setUserCustomFields(fieldsManager.getCustomFieldValues())
//            executeRegisterTask(u)
//        }
//    }
//
//    private fun checkPasswordCriteria(password: String, passwordAgain: String): Boolean {
//        if (password.length < App.PASSWORD_MIN_LENGTH) {
//            binding.registerFormPasswordField.setErrorEnabled(true)
//            binding.registerFormPasswordField.setError(
//                getString(
//                    R.string.error_register_password,
//                    App.PASSWORD_MIN_LENGTH
//                )
//            )
//            return false
//        } else if (password != passwordAgain) {
//            binding.registerFormPasswordField.setErrorEnabled(true)
//            binding.registerFormPasswordField.setError(getString(R.string.error_register_password_no_match))
//            return false
//        }
//        return true
//    }
//
//    fun onSubmitComplete(registeredUser: User?) {
//        hideProgressDialog()
//
//        // registration gamification
//        val gamificationEngine = GamificationEngine(super.getActivity())
//        gamificationEngine.processEventRegister()
//
//        //Save the search tracker
//        Tracker(super.getActivity()).saveRegisterTracker()
//        (getActivity() as WelcomeActivity).onSuccessUserAccess(registeredUser, false)
//    }
//
//    fun onSubmitError(error: String?) {
//        hideProgressDialog()
//        val ctx: Context = super.getActivity()
//        if (ctx != null) {
//            UIUtils.showAlert(getActivity(), R.string.error, error)
//        }
//    }
//
//    fun onConnectionError(error: String?, u: User) {
//        hideProgressDialog()
//        val ctx: Context = super.getActivity() ?: return
//        if (BuildConfig.OFFLINE_REGISTER_ENABLED) {
//            val builder = AlertDialog.Builder(ctx)
//            builder.setCancelable(false)
//            builder.setTitle(error)
//            builder.setMessage(R.string.offline_register_confirm)
//            builder.setPositiveButton(R.string.register_offline) { dialog, which ->
//                u.setOfflineRegister(true)
//                executeRegisterTask(u)
//            }
//            builder.setNegativeButton(R.string.cancel, null)
//            builder.show()
//        } else {
//            UIUtils.showAlert(ctx, R.string.error, error)
//        }
//    }
//
//    private fun executeRegisterTask(user: User) {
//        showProgressDialog(getString(R.string.register_process))
//        val rt = RegisterTask(super.getActivity(), apiEndpoint)
//        rt.setRegisterListener(this)
//        rt.execute(user)
//    }
}
