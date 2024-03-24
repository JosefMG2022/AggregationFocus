package com.example.aggregationfocus

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Accumulators
import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import org.bson.Document

@SuppressLint("AuthLeak")
fun getMongoClient(): MongoClient {
    return MongoClients.create("Your String value here")
}

fun fetchDataFromMongoDB(collection: MongoCollection<Document>, age: Int): Int {
    val pipeline = listOf(
        Aggregates.match(Filters.eq("age", age)),
        Aggregates.group(null, Accumulators.sum("count", 1))
    )

    val result = collection.aggregate(pipeline).firstOrNull()
    return result?.getInteger("count", 0) ?: 0
}
// Normal code before I changed it up!!
@Composable
fun MainMongoData() {
    var fetchedData by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(18) } // Example: Default age set to 20

    Column {
// Age Input
        TextField(
            value = age.toString(),
            onValueChange = { age = it.toIntOrNull() ?: age },
            label = { Text("Enter Age") },
            modifier = Modifier.fillMaxWidth()
        )

// Display your fetched data
        Text("You matched with: $fetchedData people")

// Button to trigger search
        Button(onClick = {
            val client = getMongoClient()
            val database = client.getDatabase("people")
            val collection = database.getCollection("person")

            val count = fetchDataFromMongoDB(collection, age)
            fetchedData = count.toString()

            client.close()
        }) {
            Text("Search")
        }
    }
}

/*
@Composable
fun MainMongoData(collection2: MongoCollection<Document>, age: Int) {
    var newPipeline = fetchDataFromMongoDB(collection2, age)

    var fetchedData by remember { mutableStateOf(newPipeline) }
    var age2 by remember { mutableStateOf(20) } // Example: Default age set to 20

    Column {
// Age Input
        TextField(
            value = age2.toString(),
            onValueChange = { age2 = it.toIntOrNull() ?: age },
            label = { Text("Enter Age") },
            modifier = Modifier.fillMaxWidth()
        )

// Display your fetched data
        Text("You matched with: $age2 people")

// Button to trigger search
        Button(onClick = {
            val client = getMongoClient()
            val database = client.getDatabase("people")
            val collection = database.getCollection("person")

            val count = fetchDataFromMongoDB(collection, age)
            fetchedData = count.toString().length

            client.close()
        }) {
            Text("Search")
        }
    }
}*/
