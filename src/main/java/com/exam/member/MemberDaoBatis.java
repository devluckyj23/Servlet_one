package com.exam.member;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.exam.comm.MyBatisUtils;

public class MemberDaoBatis implements MemberDao{
	public MemberDaoBatis(){}
	private static MemberDao memberDao = new MemberDaoBatis();
	public static MemberDao getInstance() {
		return memberDao;
	}
	
	private SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();

	
	@Override
	public List<MemberVo> selectMemberList() {
		
		List<MemberVo> list = null; //new ArrayList<MemberVo>(); 라고 줘도 된다~

		try (SqlSession session = sqlSessionFactory.openSession()) {
			//실행할 SQL문과 동일한 이름의 메서드를 사용하여 SQL문 실행
			//SELECT결과가 1행인 경우 selectOne, 2행이상인 경우 selectList 메서드 사용
			//첫번째 인자로 실행할 SQL문의 고유한 이름을 지정
			//두번째 인자로 SQL문 실행시 필요한 데이터를 담은 객체를 전달
			list = session.selectList("com.exam.member.MemberDao.selectMemberList");
			}
		return list;
	}

	@Override
	public int insertMember(MemberVo vo) {
		int num=0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.insert("com.exam.member.MemberDao.insertMember",vo);
			session.commit(); //INSERT,UPDATE,DELETE 후에는 COMMIT필요
		}
		return num;
	}
	
	//삭제버튼을 클릭하면, 삭제가 되도록 MemberDaoBatis 클래스와 MemberMapper.xml파일을 변경하세요.
	@Override
	public int deleteMember(String memId) {
		int num=0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.delete("com.exam.member.MemberDao.deleteMember",memId);
			session.commit(); //INSERT,UPDATE,DELETE 후에는 COMMIT필요
		}
		return num;
	}

	@Override
	public MemberVo selectMember(String memId) {
		MemberVo vo = null;

		try (SqlSession session = sqlSessionFactory.openSession()) {
		
			vo = session.selectOne("com.exam.member.MemberDao.selectMember",memId);
			}
		return vo;
	}

	@Override
	public int updateMember(MemberVo vo) {
		//데이터베이스에 회원정보가 변경되도록 구현
		int num=0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.update("com.exam.member.MemberDao.updateMember",vo);
			session.commit(); //INSERT,UPDATE,DELETE 후에는 COMMIT필요
		}
		
		return num;
	}

	@Override
	public MemberVo selectLogin(MemberVo mvo) {
		//vo 에 들어있는 아이디, 비밀번호가 일치하는 회원정보를 데이터베이스에서 select하여 반환하도록 구현
		MemberVo vo = null;

		try (SqlSession session = sqlSessionFactory.openSession()) {
		
			vo = session.selectOne("com.exam.member.MemberDao.selectLogin",mvo);
			}
		return vo;
	}
	
	
	
}
