package com.Backend;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.Backend.Confg.DBconfg;


public class AppTest 
{
	

	@BeforeClass
	public static void init(){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.register(DBconfg.class);
		context.refresh();
	}

   
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
