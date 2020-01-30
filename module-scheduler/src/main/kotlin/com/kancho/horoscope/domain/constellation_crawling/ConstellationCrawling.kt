package com.kancho.horoscope.domain.constellation_crawling

import com.kancho.constellation.Constellation
import javax.persistence.*

@Entity
@Table(name = "constellation_crawling")
class ConstellationCrawling private constructor(id: Long? = null, name: String,
                                                date: String, constellation: Constellation, crawlingUrl: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        private set

    var name: String = name
        private set

    var date: String = date
        private set

    @Enumerated(EnumType.STRING)
    var constellation: Constellation = constellation

    var crawlingUrl: String = crawlingUrl

    constructor(name: String, date: String, constellation: Constellation, crawlingUrl: String)
            : this(null, name, date, constellation, crawlingUrl)
}