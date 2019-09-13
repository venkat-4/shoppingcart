package com.cts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.model.User;
import com.cts.util.RWExcelFileAuth;

@Repository
public class AuthRepository {

	@Autowired
	RWExcelFileAuth rWExcelFileAuth;
	
    public String login(User user){
    	
    	String  res = rWExcelFileAuth.readExcel(user, "./src/main/resources/excel/user.xlsx");
    	return res.equals("User Looged in sucessfully")?res:"invalid user details";
			
	}

	public String createUser(User user) {
		
		
		return rWExcelFileAuth.writeExcel(user, "./src/main/resources/excel/user.xlsx");
		
	}
}
