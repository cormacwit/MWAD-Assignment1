package org.setu.CA1.console.controllers

import mu.KotlinLogging
import org.setu.CA1.console.models.TeacherJSONStore
import org.setu.CA1.console.models.TeacherModel
import org.setu.CA1.console.views.TeacherView

class TeacherController {

    val teachers = TeacherJSONStore()
    val teacherView = TeacherView()
    val logger = KotlinLogging.logger {}
    init {
        logger.info { "Launching School Console App" }
        println("School Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down School Console App" }
    }

    fun menu() :Int { return teacherView.menu() }

    fun add(){
        var aTeacher = TeacherModel()

        if (teacherView.addTeacherData(aTeacher))
            teachers.create(aTeacher)
        else
            logger.info("Employee Not Added")
    }

    fun list() {
        teacherView.listTeachers(teachers)
    }

    fun update() {

       list()
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

    fun search() {
        list()
        val aTeacher = search(teacherView.getId())!!
        teacherView.showTeacher(aTeacher)
    }


    fun search(id: Long) : TeacherModel? {
        var foundTeacher = teachers.findOne(id)
        return foundTeacher
    }


    fun delete() {
        teacherView.listTeachers(teachers)
        var searchId = teacherView.getId()
        val aTeacher = search(searchId)

        if(aTeacher != null) {
            teachers.delete(aTeacher)
            println("Staff Deleted...")
            teacherView.listTeachers(teachers)
        }
        else
            println("Staff Not Deleted...")
    }


}