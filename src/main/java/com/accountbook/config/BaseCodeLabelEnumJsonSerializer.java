package com.accountbook.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.accountbook.mvc.domain.BaseCodeLabelEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BaseCodeLabelEnumJsonSerializer extends JsonSerializer<BaseCodeLabelEnum>{
	@Override
	public void serialize(BaseCodeLabelEnum value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException{
		Map<String, Object> map = new HashMap<>();
		map.put("code", value.code());
		map.put("label", value.label());
		gen.writeObject(map);
	}

}
