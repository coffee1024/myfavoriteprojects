package com.travel.action.lucene;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.hibernate.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.utils.ConfigUtil;

/**
 *@author  刘光强
 *@date    2012-4-19 下午1:00:09
 *@version 1.0
 **/
class CacheList{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	//缓存开始的页数
    private int pageIndex=1;
    //缓存的页数
    private int cacheSize=10;
    //每页记录数
    private int pageSize=10;
    private int recoredCount;
    private List<List<String>> CachePage = new ArrayList<List<String>>();
    private boolean isFirst = true;
    public CacheList(){
   	 try {
			pageSize=Integer.parseInt(ConfigUtil.get("pagenum"));
		} catch (Exception e) {
			logger.error("查询时转换每页数量异常，使用默认配置"+pageSize);
		}
   	 
    }

    
	public int getRecoredCount() {
		return recoredCount;
	}

	public void setRecoredCount(int recoredCount) {
		this.recoredCount = recoredCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	
	public int getPageSize() {
		return cacheSize;
	}

	public void setPageSize(int pageSize) {
		this.cacheSize = pageSize;
	}

	public List<List<String>> getCachePage() {
		return CachePage;
	}

	public void setCachePage(List<List<String>> cachePage) {
		CachePage = cachePage;
	}

	//判断是否在缓存中
    public boolean InCache(int pindex)
    {
        if (pindex < (pageIndex + cacheSize) && pindex >= pageIndex && !isFirst)
        {
            return true;
        }
        //如果不在缓存中将第一次搜索的标示更新
        isFirst = true;
        return false;
    }

    //清空缓存
    public void flushCache(){	
    	CachePage.clear();
    }
   //增加缓存
    public void AddCache(List<String> list)
    {
        CachePage.add(list);
    }
   //读取缓存
   //pindex 为页数
    public List<List<String>> ReadCache(int pindex,int pageSize)
    {
        int index = (pindex-1)%cacheSize * pageSize;
        int end = index + pageSize;
        if (CachePage.size() < end)
        {
            end = CachePage.size();
        }
        if (end >= index)
        {
            return CachePage.subList(index, end);
        }
        else
        {
            return new ArrayList<List<String>>();
        }               
    }
}
