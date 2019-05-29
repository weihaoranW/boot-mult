package com.wei.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author whr
 *
 */
public class XBind {
	public static void clone_map_2_map(Map<Object, Object> src,
									   Map<Object, Object> dst) {
		if (G.isNull_or(src, dst)) {
			return;
		}
		for (Object key : src.keySet()) {
			Object v = src.get(key);
			dst.put(key, v);
		}
	}

	public static void clone_map_2_map_str(Map<String, Object> src,
										   Map<String, Object> dst) {
		if (G.isNull_or(src, dst)) {
			return;
		}
		for (String key : src.keySet()) {
			Object v = src.get(key);
			dst.put(key, v);
		}
	}

	public static void clone_map_2_obj_str(Map<String, Object> src,
										   Object dst) {
		if (G.isEmpty(src)) {
			return;
		}
		// ----------------------------------------------------
		Map<Object, Object> x = new HashMap<>();
		for (String key : src.keySet()) {
			x.put(key, src.get(key));
		}
		clone_map_2_obj(x, dst);
	}

	public static void clone_map_2_obj(Map<Object, Object> src, Object dst) {
		if (G.isNull_or(src, dst)) {
			return;
		}
		// Field[] fields_dst = dst.getClass().getDeclaredFields();
		Field[] fields_dst = getFields(dst.getClass());
		// .getFields();
		G.debug("###XBind###fields_dst.size" + fields_dst.length);
		G.debug("###XBind###fields_dst " + fields_dst);
		for (Object key : src.keySet()) {
			Object v = src.get(key);
			for (Field p_dst : fields_dst) {
				if (p_dst.getName().equals(key)) {
					// p_dst.getType().equals(.getType())
					if (p_dst.isEnumConstant()) {
						continue;
					}
					if (!can_write(p_dst)) {
						continue;
					}
					try {
						p_dst.setAccessible(true);
						doSetValue(p_dst, dst, v);
						// p_dst.set(dst, v);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	static void doSetValue(Field p, Object var, Object value)
			throws IllegalArgumentException, IllegalAccessException {
		// int
		if (p.getType().equals(Integer.class)) {
			Integer i = Integer.parseInt(value.toString());
			p.set(var, i);
			return;
		}
		// long
		if (p.getType().equals(Long.class)) {
			Long l = Long.parseLong(value.toString());
			p.set(var, l);
			return;
		}
		if (p.getType().equals(Double.class)) {
			p.setDouble(var, Double.parseDouble(value.toString()));
			return;
		}
		if (p.getType().equals(Float.class)) {
			p.setFloat(var, Float.parseFloat(value.toString()));
			return;
		}
		if (p.getType().equals(Boolean.class)) {
			p.setBoolean(var, Boolean.parseBoolean(value.toString()));
			return;
		}
		if (p.getType().equals(Byte.class)) {
			p.setByte(var, Byte.parseByte(value.toString()));
			return;
		}
		p.set(var, value);
	}

	public static <T extends Object> List<T> getList(
			List<Map<Object, Object>> srcList, Class<T> obj)
			throws InstantiationException, IllegalAccessException {
		if (G.isEmpty(srcList)) {
			return null;
		}
		// Object x = type(T);
		ArrayList<T> lst = new ArrayList<T>();
		for (Map<Object, Object> each : srcList) {
			T data = obj.newInstance();
			XBind.clone_map_2_obj(each, data);
			lst.add(data);
		}
		return lst;
	}

	public static <T extends Object> List<T> getListObject(List<Object> srcList,
														   Class<T> obj)
			throws InstantiationException, IllegalAccessException {
		if (G.isEmpty(srcList)) {
			return null;
		}
		ArrayList<T> lst = new ArrayList<T>();
		for (Object every : srcList) {
			Map<Object, Object> each = (Map<Object, Object>) every;
			T data = obj.newInstance();
			XBind.clone_map_2_obj(each, data);
			lst.add(data);
		}
		return lst;
	}

	public static String getString(Object src) {
		if (G.isEmpty_or(src)) {
			return "";
		}
		// Field[] fields_dst = src.getClass().getDeclaredFields();
		Field[] fields_dst = getFields(src.getClass());// .getFields();
		String s = "";
		for (Field p : fields_dst) {
			String name = p.getName();
			// p_dst.getType().equals(.getType())
			p.setAccessible(true);
			try {
				Object v = p.get(src);
				s += ";" + name + ":" + v;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	/**
	 * 用法：用于从src中复制同名字段的值到dst中， 要求：声明为私有成员(private)，类为公共类（public）
	 * 如果字段的值为对象，则为阡表型复制，即对象不会生成 如：Class B的成员A为StringBuffer,则复制时，A中的StringBuffer
	 * 不会重新复制
	 *
	 * @param src
	 * @param dst
	 */
	public static void clone(Object src, Object dst) {
		if (G.isNull_or(src, dst)) {
			return;
		}
		// 获得该对象的公共成员变量
		// Field[] fields = src.getClass().getFields();
		Field[] fields = getFields(src.getClass());// .getFields();
		Field[] fields_dst = getFields(dst.getClass());// dst.getClass().getFields();
		// Field[] fields = src.getClass().getDeclaredFields();
		// Field[] fields_dst = dst.getClass().getDeclaredFields();
		// 遍历
		for (Field p : fields) {
			String src_name = p.getName();
			if (!G.isEmpty(src_name) && src_name.toUpperCase().equals("ID")) {
				continue;
			}
			// -------------------------------------------------------------------
			Object v = null;
			try {
				p.setAccessible(true);
				v = p.get(src);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			for (Field p_dst : fields_dst) {
				if (p_dst.getName().equals(src_name)
						&& p_dst.getType().equals(p.getType())) {
					if (!can_write(p_dst)) {
						break;
					}
					try {
						p_dst.setAccessible(true);
						p_dst.set(dst, v);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// ----------------------------------------------------
	public static void clone(Object src, Object dst, String... lstFD) {
		if (G.isNull_or(src, dst, lstFD)) {
			return;
		}
		// 获得该对象的公共成员变量
		// Field[] fields = src.getClass().getFields();
		Field[] fields = getFields(src.getClass());// .getFields();
		Field[] fields_dst = getFields(dst.getClass());// dst.getClass().getFields();
		// Field[] fields = src.getClass().getDeclaredFields();
		// Field[] fields_dst = dst.getClass().getDeclaredFields();
		// 遍历
		for (Field p : fields) {
			String src_name = p.getName();
			if (!G.isEmpty(src_name) && src_name.toUpperCase().equals("ID")) {
				continue;
			}
			//
			if (!G.in(src_name, lstFD)) {
				continue;
			}
			// -------------------------------------------------------------------
			Object v = null;
			try {
				p.setAccessible(true);
				v = p.get(src);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			for (Field p_dst : fields_dst) {
				if (!G.in(p_dst.getName(), lstFD)) {
					continue;
				}

				if (p_dst.getName().equals(src_name)
						&& p_dst.getType().equals(p.getType())) {
					if (!can_write(p_dst)) {
						break;
					}
					try {
						p_dst.setAccessible(true);
						p_dst.set(dst, v);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * @param src
	 */
	public static void assert_null_set_0(Object src) {
		if (src == null) {
			return;
		}
		// 获得该对象的公共成员变量
		// Field[] fields = src.getClass().getFields();
		Field[] fields = src.getClass().getDeclaredFields();
		// 遍历
		for (Field p : fields) {
			String src_name = p.getName();
			Object v = null;
			p.setAccessible(true);
			try {
				v = p.get(src);
				if (v != null) {
					continue;
				}
				if (p.getType().equals(String.class)) {
					if (v == null) {
						p.set(src, "");
					}
					continue;
				}
				if (p.getType().equals(BigDecimal.class)) {
					if (v == null) {
						p.set(src, new BigDecimal(0));
					}
					continue;
				}
				// long
				if (p.getType().equals(long.class)) {
					if (v == null) {
						p.set(src, 0L);
					}
					continue;
				}
				// integer
				if (p.getType().equals(int.class)) {
					if (v == null) {
						p.set(src, 0);
					}
					continue;
				}
				// float
				if (p.getType().equals(float.class)) {
					if (v == null) {
						p.set(src, 0f);
					}
					continue;
				}
				// float
				if (p.getType().equals(double.class)) {
					if (v == null) {
						p.set(src, 0);
					}
					continue;
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}
	}

	static Field[] getFields(@SuppressWarnings("rawtypes") Class cls) {
		if (!cls.equals(Object.class)) {
			Field[] fields = cls.getDeclaredFields();
			Field[] x = getFields(cls.getSuperclass());
			if (x == null) {
				return fields;
			}
			Field[] z = new Field[x.length + fields.length];
			int i = 0;
			for (Field each : fields) {
				z[i] = each;
				i++;
			}
			for (Field each : x) {
				z[i] = each;
				i++;
			}
			return z;
		}
		return null;
	}

	static boolean can_write(Field p) {
		if (p == null) {
			return false;
		}
		if (Modifier.isFinal(p.getModifiers())) {
			return false;
		}
		if (Modifier.isProtected(p.getModifiers())) {
			return false;
		}
		if (Modifier.isStatic(p.getModifiers())) {
			return false;
		}
		// System.out.println("isStatis(getModifiers) " +
		// (Modifier.isStatic(p.getModifiers())));
		// System.out.println("isProtected(getModifiers) " +
		// (Modifier.isProtected(p.getModifiers())));
		return true;
	}

	public static void set_init_value(Object src) {
		assert_null_set_0(src);
	}

	public static void decimal_2_zero(Object src, String... lst) {
		if (G.isNull_or(src)) {
			return;
		}
		// 获得该对象的公共成员变量
		// Field[] fields = src.getClass().getFields();
		Field[] fields = getFields(src.getClass());// .getFields();
		// Field[] fields = src.getClass().getDeclaredFields();
		// Field[] fields_dst = dst.getClass().getDeclaredFields();
		// 遍历
		for (Field p : fields) {
			String src_name = p.getName();
			if (!G.isEmpty(src_name) && src_name.toUpperCase().equals("ID")) {
				continue;
			}
			for (String each : lst) {
				if (!src_name.equals(each)) {
					continue;
				}
				if (p.getType().equals(BigDecimal.class)) {
					try {
						p.setAccessible(true);
						p.set(src, new BigDecimal(0));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						continue;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						continue;
					}
					continue;
				}
				// -------------------------------------------------------------------
			}
		}
	}

	public static void decimal_int_if_zero_2_null(Object src, String... lst) {
		if (G.isNull_or(src)) {
			return;
		}
		// 获得该对象的公共成员变量
		// Field[] fields = src.getClass().getFields();
		Field[] fields = getFields(src.getClass());// .getFields();
		// Field[] fields = src.getClass().getDeclaredFields();
		// Field[] fields_dst = dst.getClass().getDeclaredFields();
		// 遍历
		for (Field p : fields) {
			String src_name = p.getName();
			if (!G.isEmpty(src_name) && src_name.toUpperCase().equals("ID")) {
				continue;
			}
			for (String each : lst) {
				if (G.isEmpty(each)) {
					continue;
				}
				if (!src_name.equals(each)) {
					continue;
				}
				if (p.getType().equals(BigDecimal.class)) {
					try {
						p.setAccessible(true);
						BigDecimal v = (BigDecimal) p.get(src);
						if (G.zero(v)) {
							p.set(src, null);
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						continue;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						continue;
					}
					continue;
				}
				if (p.getType().equals(Integer.class)) {
					try {
						p.setAccessible(true);
						Integer v = (Integer) p.get(src);
						if (G.zero(v)) {
							p.set(src, null);
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						continue;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						continue;
					}
					continue;
				}
				if (p.getType().equals(Long.class)) {
					try {
						p.setAccessible(true);
						Long v = (Long) p.get(src);
						if (G.zero(v)) {
							p.set(src, null);
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						continue;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						continue;
					}
					// -------------------------------------------------------------------
				}
			}
		}
	}
}
