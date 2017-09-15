package com.scf.skyware.test.dao;

import java.util.List;

import com.scf.skyware.test.domain.Test;

public interface TestDAO
{
	public List<Test> getTestListAll(Test t);
}
