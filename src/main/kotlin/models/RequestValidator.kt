package models

abstract class RequestValidator(var maxScore: Int) {
    val errors = arrayListOf<RequestError>()
    abstract fun validateRequest(): ArrayList<RequestError>
}
