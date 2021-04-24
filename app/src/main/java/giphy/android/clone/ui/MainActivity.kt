package giphy.android.clone.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import giphy.android.clone.R
import giphy.android.clone.databinding.MainActivityBinding
import giphy.android.clone.ui.favorite.FavoriteFragment
import giphy.android.clone.ui.trending.TrendingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        with(binding) {
            viewPager.adapter = createViewPagerAdapter()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(TAB_TITLES[position])
            }.attach()
        }
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