import mu.KotlinLogging
import org.setu.CA1.console.models.TeacherModel

private val logger = KotlinLogging.logger {}
private var lastId = 0L

private fun getId(): Long {
    return lastId++
}

class TeacherMemStore{

    val teachers = ArrayList<TeacherModel>()

     fun findAll(): List<TeacherModel> {
        return teachers
    }

     fun findOne(id: Long) : TeacherModel? {
        var foundTeacher: TeacherModel? = teachers.find { p -> p.id == id }
        return foundTeacher
    }

     fun create(teacher: TeacherModel) {
        teacher.id = getId()
        teachers.add(teacher)
        logAll()
    }

     fun update(teacher: TeacherModel) {
        var foundTeacher = findOne(teacher.id!!)
        if (foundTeacher != null) {
            foundTeacher.name = teacher.name
            foundTeacher.position = teacher.position
        }
    }

    internal fun logAll() {
        teachers.forEach { logger.info("${it}") }
    }
}
