//package com.example.newtest.data.datasourse.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import androidx.room.Room
//import com.example.newtest.data.model.responce.NewsEntity
//
//@Database(entities = [NewsEntity::class], version = 1,exportSchema = false)
//abstract class NewsDb : RoomDatabase() {
//    abstract fun getNewsDao(): NewsDao
//
//    companion object {
//        private const val DB_NAME = "NewsDatabase"
//        private var resultDataBase: NewsDb? = null
//        fun getAppDatabase(context: Context): NewsDb? {
//            if (resultDataBase == null) {
//                resultDataBase =
//                    Room.databaseBuilder(context.applicationContext, NewsDb::class.java, DB_NAME)
//                        .allowMainThreadQueries()
//                        .build()
//            }
//            return resultDataBase
//        }
//
//        fun destroyInstance() {
//            resultDataBase = null
//        }
//    }
//}