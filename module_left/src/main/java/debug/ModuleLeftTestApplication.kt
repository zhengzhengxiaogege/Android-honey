package debug

import android.app.Application
import androidx.multidex.MultiDex
import com.github.xiaogegechen.module_left.Constants
import com.github.xiaogegechen.network.Network

class ModuleLeftTestApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Network.init(Constants.sMap)
        MultiDex.install(applicationContext)
    }
}
