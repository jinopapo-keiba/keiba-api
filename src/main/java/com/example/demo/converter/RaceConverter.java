package com.example.demo.converter;

import com.example.demo.entity.Race;
import com.example.demo.valueobject.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.text.DateFormat;

@Component
@AllArgsConstructor
public class RaceConverter extends JsonDeserializer<Race> {
    private DateFormat dateFormat;
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Race deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonNode treeNode = jsonParser.readValueAsTree();
        return Race.builder()
                .raceName(treeNode.get("raceName").textValue())
                .raceType(RaceType.toEnum(treeNode.get("raceType").textValue()))
                .raceLength(treeNode.get("raceLength").intValue())
                .clockwise(Clockwise.toEnum(treeNode.get("clockwise").textValue()))
                .grade(treeNode.get("grade") != null ? Grade.toEnum(treeNode.get("grade").textValue()) : Grade.NONE)
                .raceWeather(RaceWeather.toEnum(treeNode.get("raceWeather").textValue()))
                .raceCondition(RaceCondition.toEnum(treeNode.get("raceCondition").textValue()))
                .raceDate(dateFormat.parse(treeNode.get("raceDate").textValue()))
//                .raceHorses((List<RaceHorse>) objectMapper.readValue(treeNode.get("raceHorses").textValue(),
//                        GenericTypeResolver.resolveTypeArgument(RaceHorse.class,List.class)))
                .build();
    }
}
