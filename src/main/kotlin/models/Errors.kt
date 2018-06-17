package models

enum class RequestError(val errorCode: Int,
                        val type: ErrorType,
                        val message: String) {

    // 100 - 200
    // <editor-fold desc="/authentication">
    AUTH_MISSING_EMAIL(
            100,
            ErrorType.MISSING_FORM_VALUE,
            "Missing Email"
    ),
    AUTH_MISSING_PASSWORD(
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
    ),
    AUTH_REGISTER_INVALID_EMAIL(
            104,
            ErrorType.BAD_FORM_VALUE,
            "Email already used"
    ),
    AUTH_PASSWORD_NOT_THE_SAME(
            105,
            ErrorType.BAD_FORM_VALUE,
            "Your passwords does not match"
    ),
    AUTH_INVALID_ACCOUNT_REGISTRATION(
            106,
            ErrorType.INVALID_USER,
            "Error creating user"
    )
}

enum class ErrorType {
    BAD_FORM_VALUE,
    MISSING_FORM_VALUE,
    INVALID_USER
}