package com.wesleyware.department;

import com.wesleyware.daowiz.*;
import com.wesleyware.company.*;
import java.sql.*;
import java.util.List;

public class Test
{
	public static void main(String[] args)
	{
		// all the operations work the same as they did in company
		// there are some other operations available that are realted to FK
		try
		{
			// Get a connection.
			// This will be hidden in a static method somewhere.
			Class.forName("org.postgresql.Driver");
			Connection conn =
				DriverManager.getConnection("jdbc:postgresql://wes-redhat.mshome.net:5432/example", "postgres", "");
			try
			{
				Department dept = new Department();

				dept.setDepartmentId("DEPTMT1");

				List companyChoices = dept.companyIdChoices(conn);

				if (companyChoices.size() > 0)
				{
					Company company = (Company) companyChoices.get(0);
					if (!dept.setCompanyId(company.getCompanyId()))
						System.out.println("TEST1: COULDN'T SET COMPANY");
				}
				else
					System.out.println("TEST1: COULDN'T SET COMPANY");

				if (!dept.setName("Department 1"))
					System.out.println("TEST1: COULDN'T SET NAME");

				if (!dept.setDescription("This is a description of deptmt1"))
					System.out.println("TEST1: COULDN'T SET DESCRIPTION");

				if (!dept.create(conn))
					System.out.println("TEST1: COULDN'T CREATE DEPT");

				if (!dept.load(conn))
					System.out.println("TEST1: COULDN'T LOAD DEPT");
				else
				{
					System.out.println("--------------------------------");
					dept.debug(System.out);
					System.out.println("--------------------------------");
				}

				Company company = dept.companyIdReference(conn);

				if (company != null)
				{
					System.out.println("--------------------------------");
					company.debug(System.out);
					System.out.println("--------------------------------");
				}

				if (dept.delete(conn) == 0)
					System.out.println("TEST1: COULDN'T DELETE DEPT");
			}
			finally
			{
				try
				{
					conn.close();
				}
				catch (Exception ex)
				{}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
