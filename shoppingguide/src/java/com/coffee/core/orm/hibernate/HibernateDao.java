/**
 * Copyright (c) 2005-2009 springside.org.cn Licensed under the Apache License, Version 2.0 (the "License"); $Id:
 * HibernateDao.java 763 2009-12-27 18:36:21Z calvinxiu $
 */
package com.coffee.core.orm.hibernate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.search.FullTextQuery;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import com.coffee.core.orm.Page;
import com.coffee.core.orm.PropertyFilter;
import com.coffee.core.orm.PropertyFilter.MatchType;
import com.coffee.util.ReflectionUtils;
import com.google.common.collect.Lists;

/**
 * 封装SpringSide扩展功能的Hibernat DAO泛型基类. 扩展功能包括分页查询,按属性过滤条件列表查询. 可在Service层直接使用,也可以扩展泛型DAO子类使用,见两个构造函数的注释.
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 */
public class HibernateDao<T, PK extends Serializable> extends SimpleHibernateDao<T, PK> {
	/**
	 * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends HibernateDao<User, Long>{ }
	 */
	public HibernateDao() {
		super();
	}

	/**
	 * 用于省略Dao层, Service层直接使用通用HibernateDao的构造函数. 在构造函数中定义对象类型Class. eg. HibernateDao<User, Long> userDao = new
	 * HibernateDao<User, Long>(sessionFactory, User.class);
	 */
	public HibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}

	// -- 分页查询函数 --//
	/**
	 * 分页获取全部对象.
	 */
	public Page<T> getAll(final Page<T> page) {
		return findPage(page);
	}

	/**
	 * 
	 * 按HQL分页查询.
	 * @param page
	 *            分页参数.不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数,按顺序绑定.
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final String hql, final Object... values) {
		Assert.notNull(page, "page不能为空");

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setItemTotal(totalCount);
		}

		setPageParameter(q, page);
		List result = q.list();
		page.setItems(result);
		return page;
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            命名参数,按名称绑定.
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final String hql, final Map<String, ?> values) {
		Assert.notNull(page, "page不能为空");

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			//long totalCount = countHqlResultDistinct(hql, values);
			page.setItemTotal(totalCount);
		}

		setPageParameter(q, page);

		List result = q.list();
		page.setItems(result);
		return page;
	}
	/**
	 * 
	 * @param page
	 * 			分页对象
	 * @param fields
	 * 			检索的lucene索引字段名
	 * @param searchWords
	 * 			检索词
	 * 
	 * 
	 * @param lucenVersion
	 * 			lucene版本
	 * @param analyze
	 * 			使用的分词器
	 * @return
	 * @throws ParseException
	 */
	public Page<T> fullTextPageQuary(Page<T> page,String[] fields,String[] searchWords,BooleanClause.Occur[] flags,Version lucenVersion,Analyzer analyze,boolean highlighter) throws ParseException{
		Assert.notEmpty(fields, "检索字段不能为空");
		Assert.notEmpty(searchWords, "检索词不能为空");
		if (fields.length!=searchWords.length) {
			Assert.isTrue(true,"检索字段与检索词个数不匹配");
		}
		logger.debug("开始分页查询");
		org.apache.lucene.search.Query q=MultiFieldQueryParser.parse(lucenVersion,searchWords , fields,flags, analyze);
		FullTextQuery query=getFullTextSession().createFullTextQuery(q,entityClass);
		logger.debug("全文检索参数为"+query.getQueryString());
		if (page.isAutoCount()) {
			long totalCount = query.getResultSize();
			page.setItemTotal(totalCount);
		}
		setPageParameter(query, page);
		List<T> list=query.list();
		if (highlighter) {
			list=hightLight(q,list, lucenVersion, null, analyze, fields);
		}
		page.setItems(list);
		return page;
	}
	
	
		/**
	 	 * @param org.apache.lucene.search.Query luceneQuery
	     * @param searchResults 搜索结果集
	     * @param excludeFields 要排除高亮的字段
	     * @param fieldNames 需要高亮的字段
	     * @return 高亮后的searchResults
	     */
	private  List<T> hightLight(org.apache.lucene.search.Query luceneQuery, List<T> searchResults,Version lucenVersion, List<String> excludeFields,Analyzer analyzer, String[] fieldNames) {
	        // 设置高亮
	        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");
	        QueryScorer queryScorer = new QueryScorer(luceneQuery);
	        Highlighter highlighter = new Highlighter(formatter, queryScorer);
	        List<T> results=new ArrayList<T>();
	        for (T e : searchResults) {
	        	//事务未关闭前对原来对象操作会导致更新数据库，所以复制对象进行加亮操作
	        	T tmp=(T)BeanUtils.instantiateClass(ReflectionUtils.getSuperClassGenricType(getClass()));
	        	BeanUtils.copyProperties(e, tmp);
	            for (String fieldName : fieldNames) {
	                if(null != excludeFields && excludeFields.contains(fieldName)){
	                	results.add(tmp);
	                    continue;
	                }
	                //getField(ReflectionUtils.findField(searchResultClass, fieldName), e);
	                Object fieldValue = org.springframework.util.ReflectionUtils.invokeMethod(BeanUtils.getPropertyDescriptor(entityClass, fieldName).getReadMethod(),tmp); 
	                String hightLightFieldValue = null;
	                if(fieldValue instanceof String){
	                    try {
	                        hightLightFieldValue = highlighter.getBestFragment( analyzer, fieldName , org.apache.commons.lang.ObjectUtils.toString(fieldValue, null));
	                    } catch (IOException e1) {
	                        e1.printStackTrace();
	                    } catch (InvalidTokenOffsetsException e1) {
	                        e1.printStackTrace();
	                    }
	                    //setField(ReflectionUtils.findField(searchResultClass, fieldName), e, hightLightFieldValue);
	                    org.springframework.util.ReflectionUtils.invokeMethod(BeanUtils.getPropertyDescriptor(entityClass, fieldName).getWriteMethod(),tmp, hightLightFieldValue); 
	                }
	            }
	            results.add(tmp);
	        }
	        return results;
	    }

	/**
	 * 
	 * @param page
	 * @param hql
	 * @param values
	 * @return
	 * @date 2012-10-29
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPageDistinct(final Page<T> page, final String hql, final Map<String, ?> values) {
		Assert.notNull(page, "page不能为空");

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			//long totalCount = countHqlResult(hql, values);
			long totalCount = countHqlResultDistinct(hql, values);
			page.setItemTotal(totalCount);
		}

		setPageParameter(q, page);

		List result = q.list();
		page.setItems(result);
		return page;
	}

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param criterions
	 *            数量可变的Criterion.
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final Criterion... criterions) {
		Assert.notNull(page, "page不能为空");

		Criteria c = createCriteria(criterions);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setItemTotal(totalCount);
		}

		setPageParameter(c, page);
		List result = c.list();
		page.setItems(result);
		return page;
	}

	/**
	 * 设置分页参数到Query对象,辅助函数.
	 */
	protected Query setPageParameter(final Query q, final Page<T> page) {
		// hibernate的firstResult的序号从0开始
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
	}

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameter(final Criteria c, final Page<T> page) {
		// hibernate的firstResult的序号从0开始
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数. 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Object... values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数. 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Map<String, ?> values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}
	
	/**
	 * 去掉重复组图，使用范围有限，必须保证检索语句中组图的别名是 T3
	 * @param hql
	 * @param values
	 * @return
	 * @date 2012-10-29
	 */
	protected long countHqlResultDistinct(final String hql, final Map<String, ?> values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		
		String countHql = "select count(distinct T3.id) " + fromHql;

		try {
			Long count = findDistinct(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 */
	@SuppressWarnings("unchecked")
	protected long countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		// 执行Count查询
		//int totalCount = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();
//		Long totalCountObject = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
//		long totalCount = (totalCountObject != null) ? totalCountObject : 0;
		Object totalCountObject =  c.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = (totalCountObject != null) ? (Integer)totalCountObject : 0;

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}

	// -- 属性过滤条件(PropertyFilter)查询函数 --//

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType
	 *            匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
	 */
	public List<T> findBy(final String propertyName, final Object value, final MatchType matchType) {
		Criterion criterion = buildPropertyFilterCriterion(propertyName, value, matchType);
		return find(criterion);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public List<T> find(List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return find(criterions);
	}

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	public Page<T> findPage(final Page<T> page, final List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return findPage(page, criterions);
	}

	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	protected Criterion[] buildPropertyFilterCriterions(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = Lists.newArrayList();
		for (PropertyFilter filter : filters) {
			if (!filter.isMultiProperty()) { // 只有一个属性需要比较的情况.
				Criterion criterion = buildPropertyFilterCriterion(filter.getPropertyName(), filter.getPropertyValue(),
						filter.getMatchType());
				criterionList.add(criterion);
			} else {// 包含多个属性需要比较的情况,进行or处理.
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildPropertyFilterCriterion(param, filter.getPropertyValue(), filter
							.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	protected Criterion buildPropertyFilterCriterion(final String propertyName, final Object propertyValue,
			final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = null;
		try {

			// 根据MatchType构造criterion
			if (MatchType.EQ.equals(matchType)) {
				criterion = Restrictions.eq(propertyName, propertyValue);
			} else if (MatchType.LIKE.equals(matchType)) {
				criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			} else if (MatchType.LE.equals(matchType)) {
				criterion = Restrictions.le(propertyName, propertyValue);
			} else if (MatchType.LT.equals(matchType)) {
				criterion = Restrictions.lt(propertyName, propertyValue);
			} else if (MatchType.GE.equals(matchType)) {
				criterion = Restrictions.ge(propertyName, propertyValue);
			} else if (MatchType.GT.equals(matchType)) {
				criterion = Restrictions.gt(propertyName, propertyValue);
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
		return criterion;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一. 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) return true;
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}
}
