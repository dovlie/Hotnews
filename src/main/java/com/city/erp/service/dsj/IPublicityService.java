package com.city.erp.service.dsj;

import java.util.List;
import com.city.erp.model.dsj.PublicityModel;

public interface IPublicityService {

	public void add(PublicityModel pm) throws Exception;

	public void modify(PublicityModel pm) throws Exception;

	public void delete(PublicityModel pm) throws Exception;

	public PublicityModel getPublicity(String puid) throws Exception;

	public List<PublicityModel> getListByAll() throws Exception;

	public List<PublicityModel> getListByAllWithPage(int rows, int page) throws Exception;

	public int getCountByAll() throws Exception;

	public int getPageCountByAll(int rows) throws Exception;
}
