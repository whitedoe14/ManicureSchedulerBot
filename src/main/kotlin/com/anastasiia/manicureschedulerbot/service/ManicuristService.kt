package com.anastasiia.manicureschedulerbot.service

import com.anastasiia.manicureschedulerbot.cache.page.ManicuristPageCache
import com.anastasiia.manicureschedulerbot.config.const.PagesConst.ENTITIES_NUMBER_PER_PAGE
import com.anastasiia.manicureschedulerbot.database.entity.ManicuristEntity
import com.anastasiia.manicureschedulerbot.database.repository.ManicuristRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import kotlin.math.ceil

@Service
class ManicuristService(
    private val manicuristRepository: ManicuristRepository,
    private val manicuristPageCache: ManicuristPageCache,
) {
    fun getCurrentPageOfManicurists(userId: Long): List<ManicuristEntity> {
        val currentPage = manicuristPageCache.getCurrentPage(userId)
        val sort = Sort.by("fullName")
        val pageableReq = PageRequest.of(currentPage, ENTITIES_NUMBER_PER_PAGE, sort)
        val result = manicuristRepository.findAll(pageableReq)
        return if (result.hasContent()) result.content else emptyList()
    }

    fun getPrevPageOfManicurists(userId: Long): List<ManicuristEntity> {
        manicuristPageCache.setPrevPage(userId)
        return getCurrentPageOfManicurists(userId)
    }

    fun getNextPageOfManicurists(userId: Long): List<ManicuristEntity> {
        manicuristPageCache.setNextPage(userId)
        return getCurrentPageOfManicurists(userId)
    }

    fun getTotalNumberOfManicuristPages(): Int {
        val manicuristNumber = manicuristRepository.count()
        val pageNumber = manicuristNumber / ENTITIES_NUMBER_PER_PAGE.toDouble()
        val roundedPageNumber = ceil(pageNumber).toInt()
        return roundedPageNumber
    }
}
