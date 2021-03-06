package com.rest.provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import com.rest.model.MyDate;

@Provider
public class DataParamCoverterProvider implements ParamConverterProvider
{

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) 
	{
		if(rawType.getName().equals(MyDate.class.getName())) 
		{
			return new ParamConverter<T> ()
			{

				@Override
				public T fromString(String value) 
				{
					Calendar returnDate = Calendar.getInstance();
					if(value.equalsIgnoreCase("Tommorow")) 
					{
						returnDate.add(Calendar.DATE, 1);
					}
					else if(value.equalsIgnoreCase("Yesterday")) 
					{
						returnDate.add(Calendar.DATE, -1);
					}
					MyDate myDate = new MyDate();
					myDate.setDay(returnDate.get(Calendar.DATE));
					myDate.setMonth(returnDate.get(Calendar.MONTH)+1);
					myDate.setYear(returnDate.get(Calendar.YEAR));
					return rawType.cast(myDate);
				}

				@Override
				public String toString(T value) 
				{
					if(value == null) 
					{
						return null;
					}
					return value.toString();
				}

			};
		}
		return null;
	}

}
