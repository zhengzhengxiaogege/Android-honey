package com.github.xiaogegechen.module_b

import android.content.Context
import com.github.xiaogegechen.common.base.IApp

class ModuleBApplication: IApp {
    override fun initNetwork(): MutableMap<String, String>? {
        return Constants.sMap
    }

    override fun initContext(context: Context?) {
    }
}