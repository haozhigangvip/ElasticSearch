package com.hzg.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hzg.entity.Company;
import com.hzg.entity.SalesMergeHistory;
import com.hzg.repository.CompanyDao;
import com.hzg.repository.ContactDao;
import com.hzg.repository.SalesMergeDao;
import com.hzg.vo.SalesStatistics;


@Service
@ContextConfiguration(locations = "classpath:ApplicationContext-dao.xml")
public class SalesServiceImpl implements SalesService{

	   	@Autowired
	   	private CompanyDao companyDao;

	    @Autowired
	    private ContactDao contactDao;
	    @Autowired
	    private SalesMergeDao salesDao;

	    @Override
	    public List<SalesStatistics> getContactSales(String  comID) {
	    	List<SalesStatistics> lst=null;
	    	if(comID!=null){
	    	lst=contactDao.getSalesList(comID);
	    	
	    	}
	    	return lst;
	    }
	    
		    @Override
		@Transactional(rollbackFor=Exception.class)
		@Rollback(value=false)
		public SalesMergeHistory updateSales(SalesMergeHistory sales){
			String comID=sales.getComID();
			SalesMergeHistory salesHistory=null;
			if(comID!=null){
				Company comp=companyDao.findCompanyByComID(comID);
				Timestamp createtime=new Timestamp(System.currentTimeMillis());

				if(comp!=null){
					//
					try{
						salesHistory=new SalesMergeHistory();
						String oldSalesName=comp.getCsalesman();
						String newSalesName=sales.getNewSalesName();
						//更新company表Salesman值
						comp.setCsalesman(newSalesName);
						companyDao.save(comp);
						//存入历史表
						salesHistory.setComID(comID);
						salesHistory.setOldSalesName(oldSalesName);
						salesHistory.setNewSalesName(newSalesName);
						salesHistory.setCreatime(createtime);
						salesDao.save(salesHistory);
					}catch(Exception e){
						salesHistory=null;
						throw new RuntimeException();

					}finally{
						return salesHistory;
					}
					
				}
			
			}
			
		return salesHistory;	
		}


}
