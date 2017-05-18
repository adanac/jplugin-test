package org.adanac.jplugin.study.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.adanac.jplugin.study.util.PrimaryUtil.Transformer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static String object2Json(Object object) {
		StringWriter writer = new StringWriter();
		try {
			if (object != null)
				mapper.writeValue(writer, object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}

	public static <T> T json2Object(String json, Class<T> klass) {
		T object = null;
		try {
			if (json != null && json.length() > 0)
				object = mapper.readValue(json, klass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return object;
	}

	public static Map json2Map(String json) {
		Map m = null;
		try {
			if (json != null && json.length() > 0)
				m = mapper.readValue(json, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return m;
	}

	public static List<Map> json2ListMap(String json) {
		List m = null;
		try {
			if (json != null && json.length() > 0)
				m = mapper.readValue(json, List.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return m;
	}

	public static <T> List<T> json2ListBean(String json, Class<T> beanClass) {
		List<T> m = null;
		try {
			if (json != null && json.length() > 0) {
				JavaType javaType = getCollectionType(ArrayList.class, beanClass);
				m = mapper.readValue(json, javaType);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return m;
	}

	public static <K, V> Map json2MapBean(String json, Class<K> keyClass, Class<V> beanClass) {
		Map m = null;
		try {
			if (json != null && json.length() > 0) {
				JavaType javaType = getCollectionType(HashMap.class, keyClass, beanClass);
				m = mapper.readValue(json, javaType);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return m;
	}

	private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	public static <T> Object json2ObjectEx(String val, Class<T> clz) {
		if (val == null || val.equals("")) {
			return null;
		}
		Transformer trans = PrimaryUtil.getTransformer(clz);
		if (trans != null) {
			return trans.fromString(clz, val);
		} else {
			return json2Object(val, clz);
		}
	}

	public static String object2JsonEx(Object obj) {
		if (obj == null) {
			return "";
		}
		Transformer trans = PrimaryUtil.getTransformer(obj.getClass());
		if (trans != null) {
			return trans.convertToString(obj);
		} else {
			return object2Json(obj);
		}
	}
}
