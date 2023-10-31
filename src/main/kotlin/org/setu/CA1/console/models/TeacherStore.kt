package org.setu.CA1.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.setu.CA1.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "staff.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<TeacherModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class TeacherStore {

    var teachers = mutableListOf<TeacherModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

     fun findAll(): MutableList<TeacherModel> {
        return teachers
    }

     fun findOne(id: Long) : TeacherModel? {
        var foundTeacher: TeacherModel? = teachers.find { p -> p.id == id }
        return foundTeacher
    }

     fun create(teacher: TeacherModel) {
        teacher.id = generateRandomId()
        teachers.add(teacher)
        serialize()
    }

     fun update(teacher: TeacherModel) {
        var foundTeacher = findOne(teacher.id!!)
        if (foundTeacher != null) {
            foundTeacher.name = teacher.name
            foundTeacher.position = teacher.position
        }
        serialize()
    }

    internal fun logAll() {
        teachers.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(teachers, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        teachers = Gson().fromJson(jsonString, listType)
    }
}