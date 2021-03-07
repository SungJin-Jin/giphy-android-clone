package giphy.android.clone.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import giphy.android.clone.R
import giphy.android.clone.ui.favorite.FavoriteFragment
import giphy.android.clone.ui.trending.TrendingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        viewPager.adapter = createViewPagerAdapter()
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()
    }

    private fun createViewPagerAdapter(): ActivityViewPagerAdapter {
        return ActivityViewPagerAdapter(this)
                .apply {
                    add(TrendingFragment())
                    add(FavoriteFragment())
                }
    }

    companion object {
        private val TAB_TITLES = listOf(R.string.label_trending, R.string.label_favorites)
    }
}