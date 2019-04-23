package com.kamilh.gotcharacters.data

data class PaginationResponse<T>(
    private val responseList: List<T>,
    private val request: PaginationRequest<T>
) {
    val list = request.list.union(responseList).toList()
    val canLoadMore = responseList.size == request.pageSize
    val previousPage = PaginationRequest(
        page = if (request.page > 0) request.page - 1 else 0,
        pageSize = request.pageSize,
        list = list
    )
    val nextPage = PaginationRequest(
        page = if (canLoadMore) request.page + 1 else request.page,
        pageSize = request.pageSize,
        list = list
    )
}

data class PaginationRequest<T>(
    val page: Int,
    val pageSize: Int = 20,
    val list: List<T>
)
