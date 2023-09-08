package com.jang.crawling_project

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jang.crawling_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_tel, R.id.navigation_bookmark
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // BottomNavigationView의 아이템 선택 리스너 설정
        navView.setOnNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.navigation_tel -> {
                    // 전화번호 탭 클릭 시 TelFragment로 교체
                    navController.navigate(R.id.navigation_tel)
                    true
                }
                R.id.navigation_bookmark -> {
                    // 즐겨찾기 탭 클릭 시 BookmarkFragment로 교체
                    navController.navigate(R.id.navigation_bookmark)
                    true
                }
                else -> false
            }

            // 이전에 선택된 탭 아이콘 초기화
            val menu = navView.menu
            for (i in 0 until menu.size()) {
                val item = menu.getItem(i)
                item.icon = getUnselectedIcon(item.itemId)
            }

            // 선택된 탭 아이콘 변경
            menuItem.icon = getSelectedIcon(menuItem.itemId)

            // 이벤트 처리를 계속 진행
            true
        }
    }

    // 선택되지 않은 탭의 아이콘 리소스를 반환하는 함수
    private fun getUnselectedIcon(itemId: Int): Drawable? {
        return when (itemId) {
            R.id.navigation_tel -> getDrawable(R.drawable.ic_phone_outline_24dp)
            R.id.navigation_bookmark -> getDrawable(R.drawable.ic_star_black_outline_24dp)
            // 다른 탭에 대한 경우도 추가할 수 있습니다.
            else -> null
        }
    }

    // 선택된 탭의 아이콘 리소스를 반환하는 함수
    private fun getSelectedIcon(itemId: Int): Drawable? {
        return when (itemId) {
            R.id.navigation_tel -> {
                getDrawable(R.drawable.ic_phone_filled_24dp)
            }
            R.id.navigation_bookmark -> {
                getDrawable(R.drawable.ic_star_black_filled_24dp)
            }
            // 다른 탭에 대한 경우도 추가할 수 있습니다.
            else -> null
        }
    }
}