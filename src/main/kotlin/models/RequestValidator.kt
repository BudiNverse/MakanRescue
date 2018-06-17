package models

abstract class RequestValidator(val maxScore: Int) {
    var scoreFlag: Int = maxScore
    val errors = arrayListOf<RequestError>()
    abstract fun validateRequest(): ArrayList<RequestError>
}
