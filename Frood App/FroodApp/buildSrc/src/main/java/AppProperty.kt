object AppProperty {

    //region Property Types
    const val TYPE_TEXT: String = "String"
    const val TYPE_NUMBER: String = "int"
    const val TYPE_RES_STRING: String = "string"
    //endregion Property Types

    //region Property Names
    const val APP_NAME: String = "app_name"
    const val PREF_NAME: String = "PREF_NAME"
    const val DB_NAME: String = "DB_NAME"
    const val DB_VERSION: String = "DB_VERSION"
    const val SERVER_URL: String = "SERVER_URL"
    //endregion Property Names

    object Field {
        const val APP_NAME: String = "Frood"

        object Debug {
            const val APP_ID_SUFFIX: String = ".debug"
            const val APP_NAME: String = "${Field.APP_NAME} Debug"
            const val APP_VERSION_NAME_SUFFIX: String = "-debug"
            const val PREF_NAME: String = "\"frood_shared_pref-debug\""
            const val DB_NAME: String = "\"frood_database-debug\""
            const val DB_VERSION: String = "2"
            const val SERVER_URL: String = "\"https://frood.com\""
        }

        object Release {
            const val APP_NAME: String = Field.APP_NAME
            const val PREF_NAME: String = "\"frood_shared_pref\""
            const val DB_NAME: String = "\"frood_database\""
            const val DB_VERSION: String = "1"
            const val SERVER_URL: String = "\"https://frood.com\""
        }
    }

}