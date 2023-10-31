package org.setu.CA1.console.views

import org.setu.CA1.console.models.TeacherModel

class TeacherView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Staff")
        println(" 2. Update Staff")
        println(" 3. List All Staff")
        println(" 4. Search Staff")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readln()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listTeachers(teachers: TeacherMemStore) {
        println("List All Staff")
        println()
        teachers.logAll()
        println()
    }

    fun showTeacher(teacher : TeacherModel) {
        if(teacher != null)
            println("Staff Details [ $teacher ]")
        else
            println("Staff Not Found...")
    }

    fun addTeacherData(teacher : TeacherModel) : Boolean {

        println()
        print("Enter a Title : ")
        teacher.title = readln()!!
        print("Enter a Description : ")
        teacher.description = readln()!!

        return teacher.title.isNotEmpty() && teacher.description.isNotEmpty()
    }

    fun updateTeacherData(teacher : TeacherModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (teacher != null) {
            print("Enter a new Title for [ " + teacher.title + " ] : ")
            tempTitle = readln()!!
            print("Enter a new Description for [ " + teacher.description + " ] : ")
            tempDescription = readln()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                teacher.title = tempTitle
                teacher.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readln()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}