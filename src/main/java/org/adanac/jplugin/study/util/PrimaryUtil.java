package org.adanac.jplugin.study.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrimaryUtil {
	public static boolean support(Class<?> clz) {
		return getTransformer(clz) != null;
	}

	public static Transformer getTransformer(Class<?> clz) {
		if (clz.isEnum()) {
			return transformerMap.get(Enum.class);
		} else {
			return transformerMap.get(clz);
		}
	}

	private static Map<Class<?>, Transformer> transformerMap = new HashMap<Class<?>, Transformer>();

	static {
		transformerMap.put(Integer.class, new IntegerTrans());
		transformerMap.put(int.class, new IntegerTrans());
		transformerMap.put(Long.class, new LongTrans());
		transformerMap.put(long.class, new LongTrans());
		transformerMap.put(Double.class, new DoubleTrans());
		transformerMap.put(double.class, new DoubleTrans());
		transformerMap.put(Float.class, new FloatTrans());
		transformerMap.put(float.class, new FloatTrans());
		transformerMap.put(Date.class, new DateTrans());
		transformerMap.put(String.class, new StringTrans());
		transformerMap.put(Enum.class, new EnumTrans());
	}

	public static class Transformer {
		public String convertToString(Object obj) {
			return null;
		}

		public Object fromString(Class<?> t, String s) {
			return null;
		}
	}

	public static class StringTrans extends Transformer {
		public String convertToString(Object obj) {
			return (String) obj;
		}

		public Object fromString(Class<?> t, String s) {
			return s;
		}
	}

	public static class IntegerTrans extends Transformer {
		@Override
		public String convertToString(Object obj) {
			return obj.toString();
		}

		@Override
		public Object fromString(Class<?> t, String s) {
			return Integer.parseInt(s);
		}
	}

	public static class LongTrans extends Transformer {
		@Override
		public String convertToString(Object obj) {
			return obj.toString();
		}

		@Override
		public Object fromString(Class<?> t, String s) {
			return Long.parseLong(s);
		}
	}

	public static class DoubleTrans extends Transformer {
		@Override
		public String convertToString(Object obj) {
			return obj.toString();
		}

		@Override
		public Object fromString(Class<?> t, String s) {
			return Double.parseDouble(s);
		}
	}

	public static class FloatTrans extends Transformer {
		@Override
		public String convertToString(Object obj) {
			return obj.toString();
		}

		@Override
		public Object fromString(Class<?> t, String s) {
			return Float.parseFloat(s);
		}
	}

	public static class DateTrans extends Transformer {
		@Override
		public String convertToString(Object obj) {
			return String.valueOf(((Date) obj).getTime());
		}

		@Override
		public Object fromString(Class<?> t, String s) {
			return new Date(Long.parseLong(s));
		}
	}

	public static class BooleanTrans extends Transformer {
		@Override
		public String convertToString(Object obj) {
			if ((Boolean) obj) {
				return "true";
			} else {
				return "false";
			}
		}

		@Override
		public Object fromString(Class<?> t, String s) {
			if ("true".equals(s)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static class EnumTrans extends Transformer {
		@Override
		public String convertToString(Object obj) {
			return obj.toString();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Object fromString(@SuppressWarnings("rawtypes") Class t, String s) {
			return Enum.valueOf(t, s);
		}
	}
}