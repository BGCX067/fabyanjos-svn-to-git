package com.alfa.tracking.spring.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		if(value !=null){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			String formattedDate = formatter.format(value);
	
			gen.writeString(formattedDate);
		}else{
			gen.writeString("");
		}

	}
}