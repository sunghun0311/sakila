package dao;
import java.util.*;
import util.DBUtil;
import java.sql.*;
import vo.*;

public class StaffListDao {
	public List<StaffList> selectStaffListByPage(int beginRow, int rowPerPage){
		List<StaffList> list = new ArrayList<StaffList>();
		
		Connection conn= null;
		PreparedStatement stmt = null;
		ResultSet rs=null;

		conn = DBUtil.getConnection(); //static 매소드 
		
		String sql = "select id staffId, name, address, zip_code zipCode, phone, city, country, sid storeId from staff_list order by id";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();			
			while(rs.next()) {
				StaffList s = new StaffList();
				s.setId(rs.getInt("staffId"));
				s.setName(rs.getString("name"));
				s.setAddress(rs.getString("address"));
				s.setZipCode(rs.getString("address"));
				s.setPhone(rs.getString("phone"));
				s.setCity(rs.getString("city"));
				s.setCountry(rs.getString("country"));
				s.setSid(rs.getInt("storeId"));
				System.out.println(s.toString());
				list.add(s); // list에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
