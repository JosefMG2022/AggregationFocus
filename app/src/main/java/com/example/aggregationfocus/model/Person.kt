package com.example.aggregationfocus.model

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Person : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var name: String = ""
    var age: Int? = 18

    var timestamp: RealmInstant = RealmInstant.now()
}

