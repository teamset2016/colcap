package com.teamset.colcap.domain.identifier.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SerialNumberDBAccess {
	private final Connection connection;
	private final String typeName;
	
	public SerialNumberDBAccess(Connection connection, String typeName) {
		this.connection = connection;
		this.typeName = typeName;
	}

	public String getCurrSerialNumber()
	throws SQLException {
		PreparedStatement selectQuery = connection.prepareStatement("SELECT CURR_SERIALNUMBER FROM SERIALNUMBER WHERE TYPE = ?");
		selectQuery.setString(1, typeName);
		ResultSet snResult = selectQuery.executeQuery();
		if (snResult.next()) {
			return snResult.getString("CURR_SERIALNUMBER");
		}
		return null;
	}
	
	public void save(String currSerialNumber)
	throws SQLException {
		PreparedStatement saveQuerry = connection.prepareStatement("INSERT INTO SERIALNUMBER (TYPE, CURR_SERIALNUMBER) VALUES (?, ?)");
		saveQuerry.setString(1, typeName);
		saveQuerry.setString(2, currSerialNumber);
		saveQuerry.executeUpdate();
	}
	
	public void update(String currSerialNumber)
	throws SQLException {
		PreparedStatement updateQuery = connection.prepareStatement("UPDATE SERIALNUMBER SET CURR_SERIALNUMBER = ? WHERE TYPE = ?");
		updateQuery.setString(1, currSerialNumber);
		updateQuery.setString(2, typeName);
		updateQuery.executeUpdate();
	}
	
	public void commit()
	throws SQLException {
		connection.commit();
	}
}
