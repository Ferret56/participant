package sef.module13.activity;

import sef.module18.activity.EmployeeImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

	@SuppressWarnings("unused")
	private Connection conn;

	public AccountDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public List<Account> findAccount(String firstName, String lastName)
			throws AccountDAOException {

		List<Account> results = new ArrayList<Account>();
		try{
			PreparedStatement prst = conn.prepareStatement("select * from ACCOUNT where upper(FIRST_NAME) like ? and upper(LAST_NAME) like ? " +
					"order by ID ASC");
			prst.setString(1, "%" + firstName.toUpperCase() + "%");
			prst.setString(2, "%" + lastName.toUpperCase() + "%");

			ResultSet rs = prst.executeQuery();
			while (rs.next()) {
				results.add(new AccountImpl(rs.getInt("ID"),
						rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"),
						rs.getString("E_MAIL")));
			}
			rs.close();
			prst.close();
		} catch (SQLException e){
			throw  new AccountDAOException(AccountDAOException.ERROR_FIND_NAME,e);
		}
		return results;
	}

	public Account findAccount(int id) throws AccountDAOException {
		Account account = null;

		try{
			PreparedStatement prst = conn.prepareStatement("select* from ACCOUNT where ID = ?");
			prst.setInt(1,id);

			ResultSet rs = prst.executeQuery();
			if (rs.next()) {
				account = new AccountImpl(rs.getInt("ID"),
						rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"),
						rs.getString("E_MAIL"));
			}
			rs.close();
			prst.close();
		} catch (SQLException e){
			throw new AccountDAOException(AccountDAOException.ERROR_FIND_ID, e);
		}
		return account;
	}


	public boolean insertAccount(String firstName, String lastName, String email)
			throws AccountDAOException {
			try {
				PreparedStatement prst = conn
						.prepareStatement("insert into ACCOUNT (ID,FIRST_NAME, LAST_NAME, E_MAIL) VALUES (ACCOUNT_SEQ.nextVal,?,?,?)");
				prst.setString(1, firstName);
				prst.setString(2, lastName);
				prst.setString(3, email);

				int rows = prst.executeUpdate();
				conn.commit();
				return true;
			}catch(SQLException e){
				throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT,e);
			}
	}
}
