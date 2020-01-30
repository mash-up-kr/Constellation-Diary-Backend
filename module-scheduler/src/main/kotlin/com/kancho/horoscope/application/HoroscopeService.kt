package com.kancho.horoscope.application

import com.kancho.horoscope.Horoscope
import com.kancho.horoscope.HoroscopeRepository
import com.kancho.horoscope.domain.constellation_crawling.ConstellationCrawling
import com.kancho.horoscope.domain.constellation_crawling.ConstellationCrawlingRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class HoroscopeService(private val horoscopeCrawling: HoroscopeCrawling,
                       private val horoscopeDesignation: HoroscopeDesignation,
                       private val constellationCrawlingRepository: ConstellationCrawlingRepository,
                       private val horoscopeRepository: HoroscopeRepository) {
    @Transactional
    fun designateHoroscope() {
        val crawlingList: List<ConstellationCrawling> = constellationCrawlingRepository.findAll()

        val horoscopes: MutableList<Horoscope> = mutableListOf()

        for (c in crawlingList) {
            val content = horoscopeCrawling.crawling(c.crawlingUrl)
            val horoscope = horoscopeDesignation.designate(c.constellation, content)
            horoscopes.add(horoscope)
        }

        horoscopeRepository.saveAll(horoscopes)
    }
}