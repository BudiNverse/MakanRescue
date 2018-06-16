package models

enum class RequestError(val errorCode: Int,
                        val type: ErrorType,
                        val message: String) {

    // 100 - 200
    // <editor-fold desc="/authentication">
    AUTH_LOGIN_MISSING_EMAIL(
            100,
            ErrorType.MISSING_FORM_VALUE,
            "Missing Email"
    ),
    AUTH_LOGIN_MISSING_PASSWORD(
            101,
            ErrorType.MISSING_FORM_VALUE,
            "Missing Password"
    ),
    AUTH_LOGIN_BAD_EMAIL(
            102,
            ErrorType.BAD_FORM_VALUE,
            "Invalid Email"
    ),
    AUTH_INVALID_CREDENTIALS(
            103,
            ErrorType.INVALID_USER,
            "Invalid credentials"
    )
}

enum class ErrorType {
    BAD_FORM_VALUE,
    MISSING_FORM_VALUE,
    INVALID_USER
}