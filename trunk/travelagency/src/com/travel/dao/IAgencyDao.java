package com.travel.dao;

import java.util.List;

import com.travel.entity.Route;
import com.travel.entity.TravelAgency;
import com.travel.entity.AdminUser;

/**
 *@author 刘光强
 *@date：2012-2-21 下午12:55:54
 *@version 1.0
 **/
public interface IAgencyDao {
	public TravelAgency getAgencyByName(String agencyName);
	public TravelAgency getAgencyById(Integer gid);
	public List<TravelAgency> getAllAgencys();
	public boolean addAgency(TravelAgency agency);
	public boolean updateAgency(TravelAgency agency);
	public boolean deleteAgency(TravelAgency agency);
	public int getCount();
	public List<TravelAgency> getAgencysByPage(int pageNo,int count);
}
