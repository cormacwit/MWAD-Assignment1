package org.setu.CA1.console.main

import TeacherMemStore
import mu.KotlinLogging
import org.setu.CA1.console.models.TeacherModel
import org.setu.CA1.console.views.TeacherView

private val logger = KotlinLogging.logger {}

val teachers = TeacherMemStore()
val teacherView = TeacherView()

fun main(args: Array<String>) {
    logger.info { "Launching School Console App" }
    println("School Kotlin App Version 1.0")

    var input: Int

    do {
        input = teacherView.menu()
        when(input) {
            1 -> addTeacher()
            2 -> updateTeacher()
            3 -> teacherView.listTeachers(teachers)
            4 -> searchTeacher()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down School Console App" }
}

fun addTeacher(){
    var aTeacher = TeacherModel()

    if (teacherView.addTeacherData(aTeacher))
        teachers.create(aTeacher)
    else
        logger.info("Staff Not Added")
}

fun updateTeacher() {

    teacherView.listTeachers(teachers)
    var searchId = teacherView.getId()
    val aTeacher = search(searchId)

    if(aTeacher != null) {
        if(teacherView.updateTeacherData(aTeacher)) {
            teachers.update(aTeacher)
            teacherView.showTeacher(aTeacher)
            logger.info("Staff Updated : [ $aTeacher ]")
        }
        else
            logger.info("Staff Not Updated")
    }
    else
        println("Staff Not Updated...")
}

fun searchTeacher() {
    val aTeacher = search(teacherView.getId())!!
    teacherView.showTeacher(aTeacher)
}


fun search(id: Long) : TeacherModel? {
    var foundTeacher = teachers.findOne(id)
    return foundTeacher
}

fun dummyData() {
    teachers.create(TeacherModel(name = "New York New York", position = "So Good They Named It Twice"))
    teachers.create(TeacherModel(name= "Ring of Kerry", position = "Some place in the Kingdom"))
    teachers.create(TeacherModel(name = "Waterford City", position = "You get great Blaas Here!!"))
}