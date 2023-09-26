package ru.pvkovalev.swipingnotes.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.pvkovalev.swipingnotes.presentation.fragments.add_fragment.AddFragment
import ru.pvkovalev.swipingnotes.presentation.fragments.edit_fragment.EditFragment
import ru.pvkovalev.swipingnotes.presentation.fragments.main_fragment.MainFragment

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