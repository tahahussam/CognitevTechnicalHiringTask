package com.cogn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cogn.entity.User;
import com.cogn.service.UserService;
import com.mysql.jdbc.Statement;

public class JDBCUserRepo {

	private final static Logger logger = LoggerFactory.getLogger(UserService.class);

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://10.68.20.151:3306/test";
	private static final String DB_USER = "dev";
	private static final String DB_PASSWORD = "dev";

	public static void main(String[] args) {
		updateUser("123", "011");
	}
	
	public static User saveUser(User user) {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertUser = "INSERT INTO `test`.`user`(`first_name`, `last_name`, `country_code`,`phone_number`,`gender`, `birthdate`,"
				+ "`email`,	`avatar_name`,`avatar_data`)" + "VALUES" + "(?,?,?,?,?,?,?,?,?)";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getCountryCode());
			preparedStatement.setString(4, user.getPhoneNumber());
			preparedStatement.setString(5, user.getGender());
			preparedStatement.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));
			preparedStatement.setString(7, user.getEmail());
			preparedStatement.setBytes(8, user.getAvatar());
			preparedStatement.setString(9, user.getEmail());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setId(generatedKeys.getLong(1));
				}
			}
			logger.info("Record is inserted into User table! with userId = " + user.getId());
			return user;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);

		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	public static User findByPhoneNumber(String phoneNumber) {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String getUser = "select * from test.user where phone_number = ?";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(getUser);

			preparedStatement.setString(1, phoneNumber);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("country_code"),
						rs.getString("phone_number"), rs.getString("gender"), rs.getDate("birthdate"),
						rs.getString("email"), rs.getBytes("avatar_data"), rs.getString("email"));
			}
			return null;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);

		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	public static boolean updateUser(String encPassword, String phoneNumber) {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateUser = "UPDATE test.user SET password = ? " + " WHERE phone_number = ?";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(updateUser);

			preparedStatement.setString(1, encPassword);
			preparedStatement.setString(2, phoneNumber);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);

		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return false;
	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

}