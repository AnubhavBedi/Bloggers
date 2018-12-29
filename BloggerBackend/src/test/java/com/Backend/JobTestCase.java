package com.Backend;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.Backend.Confg.DBconfg;
import com.Backend.Dao.JobDao;

public class JobTestCase {
	
	static JobDao jobdao;
	
	@BeforeClass
	public static void init(){
	AnnotationConfigApplicationContext context=new 	AnnotationConfigApplicationContext();
	context.register(DBconfg.class);	
	context.refresh();
	
	jobdao=context.getBean("jobDao",JobDao.class);
	}
	
	@Test
	@Ignore
	public void addJob(){
		try{
		 Job job =new Job();
		 job.setCompany("HCL");
		 job.setJobDesc("to develop software");
		 job.setJobDesignation("Software Devleoper");
		 job.setLocation("Noida");
		 job.setSalary(40000);
		 
		 String dateToApply="10-12-2018";
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		Date d=sdf.parse(dateToApply);
		 job.setLastDateApply(d);
		 assertEquals("Job Added Succesfully",true,jobdao.addJob(job));
		 System.out.println("added succesfully");
	}
		catch(Exception e)
		{
			e.printStackTrace();
			}
		}
	
	public void deleteJob(){
		
		
	}
}
