package com.rest.messagebody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.rest.model.MyDate;

@Provider
@Consumes(MediaType.APPLICATION_XML)
public class DateMessageBodyReader implements MessageBodyReader<MyDate>
{

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type.getName().equalsIgnoreCase(MyDate.class.getName());
	}

	@Override
	public MyDate readFrom(Class<MyDate> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException 
	{
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MyDate.class);
			return (MyDate) jaxbContext.createUnmarshaller().unmarshal(entityStream);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			 throw new ProcessingException("Error deserializing user.", e);
			
		}
		
		//return null;
	}

	

}
