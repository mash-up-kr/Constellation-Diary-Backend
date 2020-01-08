package com.kancho.byeolbyeol.common.entity_converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Converter
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        return Date.from(entityValue.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
        return Instant.ofEpochMilli(databaseValue.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
