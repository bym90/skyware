package com.scf.skyware.manage.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scf.skyware.main.domain.User;
import com.scf.skyware.manage.domain.AdminAuth;
import com.scf.skyware.manage.domain.Code;

@Repository
public class ManageDAOImpl implements ManageDAO
{
	private SqlSessionTemplate sqlSession;

	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession)
	{
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Code> getCodeList(Code c)
	{
		return sqlSession.selectList("Code_getCodeList", c);
	}

	@Override
	public int regNamingCode(Code c)
	{
		return sqlSession.insert("Code_regNamingCode", c);
	}

	@Override
	public String getMaxNamingCodeSeq(String CodeDiv)
	{
		String maxSeq = "01";

		try
		{
			int name_seq = sqlSession.selectOne("Code_getMaxNamingCodeSeq", CodeDiv);
			if (name_seq > 0)
			{
				maxSeq = String.valueOf(name_seq);
				if (maxSeq.length() == 1)
				{
					maxSeq = "0" + maxSeq;
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return maxSeq;
	}

	@Override
	public int getMaxNamingCodeSort(String CodeDiv)
	{
		int mSort = 1;

		try
		{
			mSort = sqlSession.selectOne("Code_getMaxNamingCodeSort", CodeDiv);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return mSort;
	}
	
	@Override
	public int uptNamingCode(Code c)
	{
		return sqlSession.update("Code_uptNamingCode", c);
	}
	
	@Override
	public int delNamingCode(Code c)
	{
		return sqlSession.delete("Code_delNamingCode", c);
	}

	@Override
	public List<User> getUserList(User u)
	{
		return sqlSession.selectList("Main_getUserList", u);
	}

	@Override
	public int getUserListCount(User u)
	{
		return sqlSession.selectOne("Main_getUserListCount", u);
	}

	@Override
	public int getPageInfo()
	{
		return (int) sqlSession.selectOne("Main_getUserPageInfo");
	}

	@Override
	public int regBaseCode(Code c)
	{
		return sqlSession.insert("Code_regBaseCode", c);
	}

	@Override
	public int codeDuplCheck(Code c)
	{
		return sqlSession.selectOne("Code_codeDuplCheck", c);
	}

	@Override
	public List<AdminAuth> getAdminList(AdminAuth admin)
	{
		return sqlSession.selectList("Auth_getAdminList", admin);
	}

	@Override
	public int getAdminListCount(AdminAuth admin)
	{
		return sqlSession.selectOne("Auth_getAdminListCount", admin);
	}

	@Override
	public int uptAdmin(AdminAuth admin)
	{
		return sqlSession.update("Auth_uptAdmin", admin);
	}

	@Override
	public int delAdmin(AdminAuth admin)
	{
		return sqlSession.delete("Auth_delAdmin", admin);
	}

	@Override
	public List<AdminAuth> getAdminAddSch(AdminAuth admin)
	{
		return sqlSession.selectList("Auth_getAdminAddSch", admin);
	}

	@Override
	public int regAdmin(AdminAuth admin)
	{
		return sqlSession.insert("Auth_regAdmin", admin);
	}

	@Override
	public AdminAuth getAdminChk(AdminAuth admin)
	{
		return sqlSession.selectOne("Auth_getAdminChk", admin);
	}
}
