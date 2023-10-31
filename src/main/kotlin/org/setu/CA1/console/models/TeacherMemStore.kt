import mu.KotlinLogging
import org.setu.CA1.console.models.TeacherModel

private val logger = KotlinLogging.logger {}
private var lastId = 0L

private fun getId(): Long {
    return lastId++
}

class TeacherMemStore : TeacherMemStore {

    val teachers = ArrayList<TeacherModel>()

    override fun findAll(): List<TeacherModel> {
        return teachers
    }

    override fun findOne(id: Long) : TeacherModel? {
        var foundTeacher: TeacherModel? = teachers.find { p -> p.id == id }
        return foundTeacher
    }

    override fun create(teacher: TeacherModel) {
        teacher.id = getId()
        teachers.add(teacher)
        logAll()
    }

    override fun update(teacher: TeacherModel) {
        var foundTeacher = findOne(teacher.id!!)
        if (foundTeacher != null) {
            foundTeacher.title = teacher.title
            foundTeacher.description = teacher.description
        }
    }

    internal fun logAll() {
        teachers.forEach { logger.info("${it}") }
    }
}
