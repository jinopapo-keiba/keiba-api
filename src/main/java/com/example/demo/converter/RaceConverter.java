package com.example.demo.converter;

import com.example.demo.entity.Race;
import com.example.demo.valueobject.Clockwise;
import com.example.demo.valueobject.Grad;
import com.example.demo.valueobject.RaceType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.text.DateFormat;

@Component
@AllArgsConstructor
public class RaceConverter extends JsonDeserializer<Race> {
    private DateFormat dateFormat;

    @SneakyThrows
    @Override
    public Race deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonNode treeNode = jsonParser.readValueAsTree();
        return Race.builder()
                .raceName(treeNode.get("raceName").textValue())
                .raceType(RaceType.toEnum(treeNode.get("raceType").textValue()))
                .raceLenght(treeNode.get("raceLenght").intValue())
                .clockwise(Clockwise.toEnum(treeNode.get("clockwise").textValue()))
                .grad(Grad.toEnum(treeNode.get("grad").textValue()))
                .raceDate(dateFormat.parse(treeNode.get("raceDate").textValue()))
                .build();
    }
}
