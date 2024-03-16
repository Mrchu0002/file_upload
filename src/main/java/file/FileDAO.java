package file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FileDAO {

	private Connection conn;

	public int hit(String fileRealName) {
		String SQL = "update file set downloadCount = downloadCount + 1 " + "where fileRealName = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fileRealName);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<FileDTO> getList() {
		String SQL = "select fileName, fileRealName, downloadCount from file";
		ArrayList<FileDTO> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FileDTO file = new FileDTO(rs.getString(1), rs.getString(2), rs.getInt(3));
				list.add(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

		

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
		String SQL = "insert into file value(?,?,0)";
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
