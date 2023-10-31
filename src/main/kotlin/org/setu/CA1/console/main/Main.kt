package org.setu.CA1.console.main

import TeacherMemStore
import mu.KotlinLogging
import org.setu.CA1.console.controllers.TeacherController
import org.setu.CA1.console.models.TeacherModel
import org.setu.CA1.console.views.TeacherView

private val logger = KotlinLogging.logger {}

val teachers = TeacherMemStore()
val teacherView = TeacherView()

fun main(args: Array<String>) {
    val controller = TeacherController()}
