package com.example.uts_pam_120140045.model

import com.google.gson.annotations.SerializedName

data class ResponseApiUser(
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("per_page")
    val perPage: Int? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("data")
    val data: List<DataItemUser>? = null
)