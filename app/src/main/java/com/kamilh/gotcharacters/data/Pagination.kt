package com.kamilh.gotcharacters.data

data class PaginationResponse<T>(
    val list: List<T>,
    private val request: PaginationRequest
) {
    val canLoadMore = list.size == request.pageSize
    val previousPage = PaginationRequest(if (request.page > 0) request.page - 1 else 0, request.pageSize)
    val nextPage = PaginationRequest(if (canLoadMore) request.page + 1 else request.page, request.pageSize)
}

data class PaginationRequest(
    val page: Int,
    val pageSize: Int = 20
)
