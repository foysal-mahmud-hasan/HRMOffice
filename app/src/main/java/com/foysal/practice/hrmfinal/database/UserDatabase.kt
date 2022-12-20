package com.foysal.practice.hrmfinal.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database( entities = [UserData::class, ExceptionData::class],
 version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract val exceptionDao : ExceptionDao

    abstract val userDao : UserDao

    companion object{

        @Volatile
        private var INSTANCE:UserDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope) : UserDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    )
                        .addCallback(UserDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }

    }
    class UserDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    withContext(Dispatchers.IO) {
                        val userDao = database.userDao
                        userDao.insert(UserData("1", "1234", "Admin"))
                        userDao.insert(UserData("2", "1235", "Employee"))
                        userDao.insert(UserData("3", "1236", "Employee"))
                    }
                }

            }

        }

    }
}


