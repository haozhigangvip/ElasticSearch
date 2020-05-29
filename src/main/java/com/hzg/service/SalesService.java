package com.hzg.service;

import java.util.List;


import com.hzg.entity.Company;
import com.hzg.entity.SalesMergeHistory;
import com.hzg.vo.SalesStatistics;

public interface SalesService {

	public List<SalesStatistics> getContactSales(String comID);

	public SalesMergeHistory updateSales(SalesMergeHistory sales);

	

}
