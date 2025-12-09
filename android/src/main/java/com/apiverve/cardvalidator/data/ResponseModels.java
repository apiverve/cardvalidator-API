// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     CardValidatorData data = Converter.fromJsonString(jsonString);

package com.apiverve.cardvalidator.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static CardValidatorData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(CardValidatorData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(CardValidatorData.class);
        writer = mapper.writerFor(CardValidatorData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// CardValidatorData.java

package com.apiverve.cardvalidator.data;

import com.fasterxml.jackson.annotation.*;

public class CardValidatorData {
    private Card card;
    private String cardNumber;
    private boolean isValid;

    @JsonProperty("card")
    public Card getCard() { return card; }
    @JsonProperty("card")
    public void setCard(Card value) { this.card = value; }

    @JsonProperty("cardNumber")
    public String getCardNumber() { return cardNumber; }
    @JsonProperty("cardNumber")
    public void setCardNumber(String value) { this.cardNumber = value; }

    @JsonProperty("isValid")
    public boolean getIsValid() { return isValid; }
    @JsonProperty("isValid")
    public void setIsValid(boolean value) { this.isValid = value; }
}

// Card.java

package com.apiverve.cardvalidator.data;

import com.fasterxml.jackson.annotation.*;

public class Card {
    private String niceType;
    private String type;
    private long[] patterns;
    private long[] gaps;
    private long[] lengths;
    private Code code;
    private long matchStrength;

    @JsonProperty("niceType")
    public String getNiceType() { return niceType; }
    @JsonProperty("niceType")
    public void setNiceType(String value) { this.niceType = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("patterns")
    public long[] getPatterns() { return patterns; }
    @JsonProperty("patterns")
    public void setPatterns(long[] value) { this.patterns = value; }

    @JsonProperty("gaps")
    public long[] getGaps() { return gaps; }
    @JsonProperty("gaps")
    public void setGaps(long[] value) { this.gaps = value; }

    @JsonProperty("lengths")
    public long[] getLengths() { return lengths; }
    @JsonProperty("lengths")
    public void setLengths(long[] value) { this.lengths = value; }

    @JsonProperty("code")
    public Code getCode() { return code; }
    @JsonProperty("code")
    public void setCode(Code value) { this.code = value; }

    @JsonProperty("matchStrength")
    public long getMatchStrength() { return matchStrength; }
    @JsonProperty("matchStrength")
    public void setMatchStrength(long value) { this.matchStrength = value; }
}

// Code.java

package com.apiverve.cardvalidator.data;

import com.fasterxml.jackson.annotation.*;

public class Code {
    private String name;
    private long size;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("size")
    public long getSize() { return size; }
    @JsonProperty("size")
    public void setSize(long value) { this.size = value; }
}