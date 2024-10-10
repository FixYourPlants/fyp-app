import com.fyp.app.data.api.AlertServiceImp
import com.fyp.app.data.api.DiaryServiceImp
import com.fyp.app.data.api.OpinionServiceImp
import com.fyp.app.data.api.PageServiceImp
import com.fyp.app.data.api.PlantServiceImp
import com.fyp.app.data.api.SicknessServiceImp
import com.fyp.app.data.api.TokenServiceImp
import com.fyp.app.data.api.UserServiceImp
import com.fyp.app.utils.UserPreferencesImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object TokenManager {
    private val scheduler = Executors.newSingleThreadScheduledExecutor()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val services = mutableListOf(
        AlertServiceImp,
        DiaryServiceImp,
        OpinionServiceImp,
        PageServiceImp,
        PlantServiceImp,
        SicknessServiceImp,
        TokenServiceImp,
        UserServiceImp
    )

    fun startTokenRefreshTask(interval: Long, timeUnit: TimeUnit) {
        scheduler.scheduleWithFixedDelay({
            scope.launch {
                try {
                    if (UserPreferencesImp.refreshToken())
                        resetAllServices()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }, 0, interval, timeUnit)
    }

    fun resetAllServices() {
        services.forEach { service ->
            service.resetToken()
        }
    }



    fun stopTokenRefreshTask() {
        scheduler.shutdown()
        scope.cancel()
    }
}



