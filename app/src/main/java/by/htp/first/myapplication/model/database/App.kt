package by.htp.first.myapplication.model.database

import android.app.Application
import by.htp.first.myapplication.model.repository.impl.DatabaseRepositoryImpl

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseRepositoryImpl.initDatabase(this)
    }
}