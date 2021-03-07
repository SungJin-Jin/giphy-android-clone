package giphy.android.clone.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import giphy.android.clone.base.preseneter.BasePresenter


abstract class BaseFragment<T : BasePresenter>(
    private val layoutId: Int,
    private val titleResId: Int = 0,
    private val isDisplayMenu: Boolean = false
) : Fragment() {

    protected lateinit var presenter: T

    abstract fun initPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isDisplayMenu) {
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter()
    }

    override fun onDestroy() {
        if (::presenter.isInitialized) {
            presenter.onDestroy()
        }

        super.onDestroy()
    }
}