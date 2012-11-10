package com.coffee.domain;

/**
 * 系统所有的实体类需实现这个接口
 * 
 * 
 */
public interface IBaseBean {
	/**
	 * 根据字段名以反射的方式设定域对象对应字段属性的值
	 * 
	 * @param field
	 *            字段名
	 * @param value
	 *            字段值
	 */
	public void setValue(String fieldName, Object value);

	/**
	 * 获取字段值
	 * 
	 * @param fieldName
	 */
	public String getValue(String fieldName);
}
