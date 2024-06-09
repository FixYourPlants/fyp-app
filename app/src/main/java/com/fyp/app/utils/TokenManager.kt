import com.fyp.app.data.api.RefreshableService
import com.fyp.app.utils.UserPreferencesImp
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object TokenManager {
    private val scheduler = Executors.newSingleThreadScheduledExecutor()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

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

    private fun resetAllServices() {
        val services = findRefreshableServices()
        services.forEach { service ->
            service::class.members.find { it.name == "resetToken" }?.call(service)
        }
    }

    private fun findRefreshableServices(): List<Any> {
        val refreshableServices = mutableListOf<Any>()

        // Proporciona un paquete base válido
        val reflections = org.reflections.Reflections("com.fyp.app.api")
        val annotated = reflections.getTypesAnnotatedWith(RefreshableService::class.java)
        for (clazz in annotated) {
            try {
                val obj = clazz.kotlin.objectInstance
                if (obj != null) {
                    refreshableServices.add(obj)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return refreshableServices
    }

    fun stopTokenRefreshTask() {
        scheduler.shutdown()
        scope.cancel()
    }
}



