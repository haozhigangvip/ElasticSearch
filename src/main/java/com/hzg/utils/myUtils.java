package com.hzg.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hzg.entity.Company;
import com.hzg.entity.Contact;
import com.hzg.vo.contactResult;

public class myUtils {
public static  int chkCompanyList(List<Company> list,Company CheckCompany){
		for (Company company : list) {
		System.out.println(company.getComID());

		if(company.getComID().toString().trim().equals(CheckCompany.getComID().toString().trim())){

			return 1;
		}
	}
	return 0;
}
public static  int chkContactList(List<Contact> list,Contact CheckContact){
	for (Contact cont : list) {
		System.out.println(cont.getContID());

	if(cont.getContID().toString().trim().equals(CheckContact.getContID().toString().trim())){

		return 1;
	}
}
return 0;
}

public static  List<Company>  chkCompanyList(List<Company> sourcelist,List<Company> targetlist){
	List<Company> ls =new ArrayList<Company>();
	
	int tag;
	
	for (Company source_company : sourcelist) {
		tag=1;
		for(Company target_company : targetlist){
				if(source_company.getComID().trim().equals(target_company.getComID().trim())==true){
				tag=0;
			

			}
		}
		if (tag==1){
			ls.add(source_company);
		}
	}
	return ls;
}

public static  List<Contact>  chkContactList(List<Contact> sourcelist,List<Contact> targetlist){
	List<Contact> ls =new ArrayList<Contact>();
	
	int tag;
	
	for (Contact source_Contact : sourcelist) {
		tag=1;
		for(Contact target_Contact : targetlist){
				if(source_Contact.getContID().trim().equals(target_Contact.getContID().trim())==true){
				tag=0;
			

			}
		}
		if (tag==1){
			ls.add(source_Contact);
		}
	}
	return ls;
}

public static Contact  arrayTOContact(Object[] obj){
	System.out.println(obj);
	System.out.println(obj[0]);
	Contact ct=new Contact();
	ct.setAutoID((Integer)obj[0]);
	ct.setContID((String)obj[1]);
	ct.setName((String)obj[2]);
	ct.setComID((String)obj[3]);
	ct.setCompanyname((String)obj[4]);
	ct.setDelTag((Integer)obj[5]);
	return ct;
}
public static List<String> setSalesList(HttpSession session ,HttpServletResponse response) throws IOException{
	
	List<Company> oldlist=(List<Company>)session.getAttribute("SourceList");
	List<Company> newlist=(List<Company>)session.getAttribute("TargetList");
	Company com=null;
	List<String> ls=new ArrayList<>();
	
	if(newlist!=null&& newlist.size()>0){
		com=newlist.get(0);
	}
	
	
	
	if(oldlist!=null){
	for (Company comp : oldlist) {
		String salesman=comp.getCsalesman();
		if(salesman!=null && (ls==null ||  ls.indexOf(salesman.trim())<=0)){
			ls.add(salesman.trim());
		}
	}}
	
	if(com!=null &&com.getCsalesman()!=null &&(ls==null || ls.indexOf(com.getCsalesman().trim())<=0)){
		
		ls.add(com.getCsalesman().trim());
	}
	session.setAttribute("SalesList", ls);	
	return ls;

}

}
