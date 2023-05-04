package com.example.sowlutions.api

open class ApiMainHeaders : HashMap<String, String>()
class PublicHeaders : ApiMainHeaders()
class ApiMainHeadersProvider {

    companion object {
        private const val CONTENT_TYPE = "Content-Type"
        private const val AUTHORIZATION = "Authorization"
        private const val STORE_ID = "StoreID"
        private const val USER_ADDRESS_ID = "UserAddressID"

        fun getPublicHeaders(): PublicHeaders =
            PublicHeaders().apply {
                putAll(getDefaultHeaders())
            }

        //Default headers used on all calls
        private fun getDefaultHeaders() = mapOf(
            CONTENT_TYPE to "application/json",
            AUTHORIZATION to "f44a4aabfc5992514d262d7f517327e7",
            STORE_ID to "4",
            USER_ADDRESS_ID to "60877"
        )
    }
}

