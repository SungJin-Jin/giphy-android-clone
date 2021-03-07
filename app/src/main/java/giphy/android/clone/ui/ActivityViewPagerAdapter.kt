package giphy.android.clone.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ActivityViewPagerAdapter(fragmentManager: FragmentActivity) : FragmentStateAdapter(fragmentManager) {

    private val fragments: MutableList<Fragment> = mutableListOf()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun add(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }
}