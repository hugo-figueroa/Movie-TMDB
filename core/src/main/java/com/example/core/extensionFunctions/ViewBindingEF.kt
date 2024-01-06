package com.example.core.extensionFunctions

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * AppCompatActivity.viewBinding - generate the ViewBinding from Activity
 * example:
 *
 * class MainActivity : AppCompatActivity() {
 *      private val binding by viewBinding(ActivityMainBinding::inflate)
 * }
 *
 * @author (c) 2024, Hugo Figueroa
 */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline factory: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        factory(layoutInflater)
    }

/**
 * Fragment.viewBinding - generate the ViewBinding from Fragments
 * example:
 *
 * class MainFragment : Fragment(R.layout.fragment_layout) {
 *      private val binding by viewBinding(FragmentLayoutBinding::bind)
 * }
 *
 * Note: Important to add the Layout as parameter and use the binding in the onViewCreated
 *
 * @author (c) 2024, Hugo Figueroa
 */
inline fun <T : ViewBinding> Fragment.viewBinding(crossinline factory: (View?) -> T): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {
        private var binding: T? = null

        @MainThread
        override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
            binding ?: factory(view).also {
                // if binding is accessed after Lifecycle is DESTROYED, create new instance, but don't cache it
                if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                    viewLifecycleOwner.lifecycle.addObserver(this)
                    binding = it
                }
            }

        override fun onDestroy(owner: LifecycleOwner) {
            viewLifecycleOwner.lifecycle.removeObserver(this)
            // Fragment.viewLifecycleOwner call LifecycleObserver.onDestroy() before Fragment.onDestroyView().
            // That's why we need to postpone reset of the viewBinding
            Handler(Looper.getMainLooper()).post {
                binding = null
            }
        }
    }

/**
 * DialogFragment.viewBinding - generate the ViewBinding from DialogFragments
 *
 * Example with LifeCycle and onViewCreated method
 * class MainDialogFragment : DialogFragment(R.layout.dialog_layout) {
 *      private val binding by viewBinding(DialogLayoutBinding::bind)
 * }
 *
 * Note: Important to add the Layout as parameter and use the binding in the onViewCreated
 *
 * * Example without LifeCycle and onCreateDialog method
 * class MainDialogFragment : DialogFragment() {
 *      private val binding by viewBinding(DialogLayoutBinding::inflate)
 *
 *      override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
 *          return AlertDialog.Builder(requireContext()).setView(binding.root).create()
 *      }
 * }
 *
 * @author (c) 2024, Hugo Figueroa
 */
inline fun <T : ViewBinding> DialogFragment.viewBinding(crossinline factory: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        factory(layoutInflater)
    }

/**
 * ViewGroup.viewBinding - generate the ViewBinding from Custom views, Holders or simple views
 * example:
 *
 * Holder(parent.viewBinding(ListItemBinding::inflate))
 *
 *
 * @author (c) 2024, Hugo Figueroa
 */
inline fun <T : ViewBinding> ViewGroup.viewBinding(factory: (LayoutInflater, ViewGroup, Boolean) -> T) =
    factory(LayoutInflater.from(context), this, false)

/**
 * ViewGroup.viewBinding - generate the ViewBinding from Custom views, Holders or simple views
 * example:
 *
 * class CustomLayout @JvmOverloads constructor(...): FrameLayout(...) {
 *      private val binding: ViewCustomLayoutBinding by lazy {
 *          viewBinding(ViewCustomLayoutBinding::inflate, true)
 *      }
 *
 *      init {
 *          binding.textView.text = "Bind!!!"
 *      }
 * }
 *
 * @author (c) 2024, Hugo Figueroa
 */
inline fun <T : ViewBinding> ViewGroup.viewBinding(
    factory: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToRoot: Boolean = false
) = factory(LayoutInflater.from(context), this, attachToRoot)
