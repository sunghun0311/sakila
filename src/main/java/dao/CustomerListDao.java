package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.CustomerList;

public class CustomerListDao {
	
	public List<CustomerList> selectCustomerListByPage(int beginRow, int rowPerPage) {
		List<CustomerList> list = new ArrayList<CustomerList>();
		
		// DB 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// static으로 선언해서 ~
		// MariaDB 드라이버 로딩 메서드 호출
		conn = DBUtil.getConnection();
		String sql = "SELECT ID customerId, name name, address address, 'zip code' postalCode, phone phone, city city, country country, notes active, SID storeId FROM customer_list ORDER BY ID limit ?,?";
	   
		try {
			// SQL 쿼리 저장, 페이징 작업
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			// 데이터 변환
			while(rs.next()) {
				CustomerList c = new CustomerList(); // 다형성 
				c.setCustomerId(rs.getInt("customerId"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setPostalCode(rs.getString("postalCode"));
				c.setPhone(rs.getString("phone"));
				c.setCity(rs.getString("city"));
				c.setCountry(rs.getString("country"));
				c.setActive(rs.getString("active"));
				c.setStoreId(rs.getString("storeId"));
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB자원반환
				rs.close();
				stmt.close(); 
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// CustomerList 총 개수 구하기
	public int selectCustomerTotalRow() throws Exception {
		int row = 0; // 함수 결과값(정수) 반환해줄 변수 선언 후 초기화
		
		// DB 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// static으로 선언해서 ~
		conn = DBUtil.getConnection();
		String sql = "SELECT COUNT(*) cnt FROM customer_list";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("cnt");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//DB자원반납
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
}