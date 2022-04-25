package dao;

import java.sql.*;
import java.util.*;
import vo.*;
import util.DBUtil; 

public class SalesByStoreDao {
	public List<SalesByStore> selectSalesByStoreByPage(int beginRow, int rowPerPage) {
		List<SalesByStore> list = new ArrayList<SalesByStore>();
		Connection conn= null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		// DB연결
		conn = DBUtil.getConnection(); //static 매소드 
		
		String sql = "select store, manager, total_sales totalSales from sales_by_store order by store";
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()) {
					SalesByStore s = new SalesByStore();
					s.setStore(rs.getString("store"));
					s.setManager(rs.getString("manager"));
					s.setTotalSales(rs.getDouble("totalSales"));
					System.out.println(s.toString());
					list.add(s);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return list;		
	}
}
