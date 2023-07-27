package ru.pvkovalev.swipingnotes.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.pvkovalev.swipingnotes.presentation.AddFragment
import ru.pvkovalev.swipingnotes.presentation.EditFragment
import ru.pvkovalev.swipingnotes.presentation.MainFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(
        MainFragment.newInstance(),
        AddFragment.newInstance(),
        EditFragment.newInstance()
    )

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}