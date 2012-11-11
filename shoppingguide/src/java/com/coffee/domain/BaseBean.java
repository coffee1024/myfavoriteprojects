package com.coffee.domain;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.*;

import com.coffee.domain.BaseBean;

public class BaseBean implements IBaseBean{

	protected String recordId;

	private static final Logger LOG = LoggerFactory.getLogger(BaseBean.class);

	public Field getField(Class<?> cls, String name) throws NoSuchFieldException {
		if (cls == null) return null;
		Field field = null;
		if (cls.isInstance(new Object()))
			throw new NoSuchFieldException("FiledName is " + name);
		else
			try {
				field = cls.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				field = getField(cls.getSuperclass(), name);
			}
		return field;
	}

	public void setValue(String fieldName, Object value) {
		try {
			// getDeclaredField不包括继承的字段,只搜寻*Declared*;而getField只搜寻所有public
			Field field = this.getField(this.getClass(), fieldName);
			if (field == null) throw new NullPointerException("获取字段" + fieldName + "为空！");

			Class<?> type = field.getType();

			if (Integer.class == type) {
				int valueObj = Integer.valueOf(value.toString());
				field.setAccessible(true);
				field.set(this, valueObj);
				field.setAccessible(false);
			} else if (String.class == type) {
				String valueObj = value.toString();
				field.setAccessible(true);
				field.set(this, valueObj);
				field.setAccessible(false);
			} else if (Long.class == type) {
				long valueObj = Long.parseLong(value.toString());
				field.setAccessible(true);
				field.set(this, valueObj);
				field.setAccessible(false);
			} else if (Boolean.class == type) {
				boolean valueObj = Boolean.getBoolean(value.toString());
				field.setAccessible(true);
				field.set(this, valueObj);
				field.setAccessible(false);
			} else if (Float.class == type) {
				float valueObj = Float.parseFloat(value.toString());
				field.setAccessible(true);
				field.set(this, valueObj);
				field.setAccessible(false);
			} else if ((java.util.Date.class == type) || (Date.class == type)) {
				//目前只支持"yyyy.MM.dd"和"yyyy.MM.dd hh:mm:ss"
				String str = value.toString();
				Date valueObj = null;
				DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm", Locale.ENGLISH);
				try {
					valueObj = java.sql.Date.valueOf(str);
				} catch (Exception e1) {
					try {
						valueObj = dateFormat.parse(str);
					} catch (Exception e2) {
					}
				}
				field.setAccessible(true);
				field.set(this, valueObj);
				field.setAccessible(false);
			} else {
				Object valueObj = type.cast(value);
				field.setAccessible(true);
				field.set(this, valueObj);
				field.setAccessible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(this.getClass() + ": set column[" + fieldName + "] err," + e.toString());
		}

	}

	public String getValue(String fieldName) {
		try {
			Field field = this.getField(this.getClass(), fieldName);
			if (field == null) throw new NullPointerException("获取字段" + fieldName + "为空！");
			field.setAccessible(true);
			Object obj = field.get(this);
			field.setAccessible(false);
			return obj.toString();
		} catch (Exception e) {
			LOG.error(this.getClass() + ": get column[" + fieldName + "] err," + e.toString());
		}
		return null;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
}
