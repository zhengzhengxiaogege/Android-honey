package com.github.xiaogegechen.module_left.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.github.xiaogegechen.common.arouter.ARouterMap.Companion.MODULE_LEFT_FRAGMENT_D_LEFT
import com.github.xiaogegechen.common.base.BaseFragment
import com.github.xiaogegechen.common.util.ImageParam
import com.github.xiaogegechen.common.util.ImageUtil
import com.github.xiaogegechen.module_left.R
import com.github.xiaogegechen.module_left.presenter.FragmentLeftPresenterImpl
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.module_left_head_layout.*

@Route(path = MODULE_LEFT_FRAGMENT_D_LEFT)
class FragmentLeft:BaseFragment(), IFragmentLeftView {

    private var mBgImage: ImageView? = null
    private var mHeadImage: ImageView? = null
    private var mFragmentLeftPresenter: FragmentLeftPresenterImpl? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val navigationViewHead = view?.findViewById<NavigationView>(R.id.navigation)?.getHeaderView(0)
        mBgImage = navigationViewHead?.findViewById(R.id.bgImage)
        mHeadImage = navigationViewHead?.findViewById(R.id.headImage)

        mFragmentLeftPresenter?.queryBgImage()
        mFragmentLeftPresenter?.queryHeadImage()

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        mFragmentLeftPresenter?.detach()
    }

    override fun initData() {
        mFragmentLeftPresenter = FragmentLeftPresenterImpl()
        mFragmentLeftPresenter?.attach(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.module_left_fragment_left
    }

    override fun showBgImage(url: String) {
        ImageUtil.displayImage(
            ImageParam.Builder()
                .context(obtainContext())
                .error(ColorDrawable(obtainResources()?.getColor(R.color.design_color_accent)!!))
                .placeholder(ColorDrawable(obtainResources()?.getColor(R.color.design_color_accent)!!))
                .imageView(mBgImage)
                .url(url)
                .build())
    }

    override fun showHeadImage(url: String) {
        ImageUtil.displayImage(
            ImageParam.Builder()
                .context(obtainContext())
                .error(ColorDrawable(obtainResources()?.getColor(R.color.design_color_accent)!!))
                .placeholder(ColorDrawable(obtainResources()?.getColor(R.color.design_color_accent)!!))
                .imageView(mHeadImage)
                .url(url)
                .build())
    }

    override fun showProgress() {
    }

    override fun showErrorPage() {
    }

    override fun showToast(message: String) {
        Toast.makeText(obtainContext(), message, Toast.LENGTH_SHORT).show()
    }
}