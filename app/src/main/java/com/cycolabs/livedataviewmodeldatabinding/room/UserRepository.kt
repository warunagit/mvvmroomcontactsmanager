package com.cycolabs.livedataviewmodeldatabinding.room

class UserRepository(private val dao: UserDAO) {
    //Repo provides clean api for access the rest of the app
    //manages connections, fetching and caching data

    val users = dao.getAllUsersInDB()
    //room exectes all queries on a seperate thread.
    //observable notify when data have changed.

    suspend fun insert(user: User):Long{
        return dao.insertUser(user)
    }

    suspend fun delete(user: User){
        return dao.deleteUser(user)
    }

    suspend fun update(user: User){
        return dao.updateUser(user)
    }

    suspend fun deleteAll(){
        return dao.deleteAll()
    }
}