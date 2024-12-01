package com.anastasiia.manicureschedulerbot.application.service

import com.anastasiia.manicureschedulerbot.infrastructure.cache.page.ManicurePageCache
import com.anastasiia.manicureschedulerbot.infrastructure.config.constant.PagesConst.ENTITIES_NUMBER_PER_PAGE
import com.anastasiia.manicureschedulerbot.infrastructure.database.entity.ManicureEntity
import com.anastasiia.manicureschedulerbot.infrastructure.database.repository.ManicureRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import kotlin.math.ceil

@Service
class ManicureService(
    private val manicureRepository: ManicureRepository,
    private val manicurePageCache: ManicurePageCache,
) {
    fun getCurrentPageOfManicures(userId: Long): List<ManicureEntity> {
        val currentPage = manicurePageCache.getCurrentPage(userId)
        val sort = Sort.by("name")
        val pageableReq = PageRequest.of(currentPage, ENTITIES_NUMBER_PER_PAGE, sort)
        val result = manicureRepository.findAll(pageableReq)
        return if (result.hasContent()) result.content else emptyList()
    }

    fun getPrevPageOfManicures(userId: Long): List<ManicureEntity> {
        manicurePageCache.setPrevPage(userId)
        return getCurrentPageOfManicures(userId)
    }

    fun getNextPageOfManicures(userId: Long): List<ManicureEntity> {
        manicurePageCache.setNextPage(userId)
        return getCurrentPageOfManicures(userId)
    }

    fun getTotalNumberOfManicurePages(): Int {
        val manicureNumber = manicureRepository.count()
        val pageNumber = manicureNumber / ENTITIES_NUMBER_PER_PAGE.toDouble()
        val roundedPageNumber = ceil(pageNumber).toInt()
        return roundedPageNumber
    }
}
