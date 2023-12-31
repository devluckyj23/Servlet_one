package com.exam.comm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {

	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			//마이바티스 전체 설정파일 위치(클래스패스 기준)
			String resource = "batis/mybatis-config.xml";	
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//설정파일의 내용대로 SqlSessionFactory(마이바티스본체)를 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory(){
		return sqlSessionFactory;
	}
	
}



