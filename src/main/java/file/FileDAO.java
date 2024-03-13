package file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FileDAO {

	private Connection conn;

	public FileDAO() {

		try {
			String dbURL = "jdbc:mysql://localhost:3306/file";
			String dbID = "mrchu";
			String dbPassword = "8512asdf";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int upload(String fileName, String fileRealName) {
		String SQL = "insert into file value(?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
		/*
		 * 정수리턴값을 사용하는 경우
		 * 
		 * * 2가지 *
		 * 1 : 참
		 * 0 : 거짓
		 * 
		 * * 3가지 *
		 * 1 : 검색후
		 * 0 : 검색
		 * -1: 검색전
		 */
	}

}
