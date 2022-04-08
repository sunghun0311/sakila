package dao;
import java.util.*;
import java.sql.*;
import util.DBUtil;

public class StatsDataDao {

public List<Map<String, Object>> amountByCoustomer() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    conn = DBUtil.getConnection();
    String sql = "SELECT p.customer_id customerId," // 180이상
          + "      concat(c.first_name,' ', c.last_name) name,"
          + "      sum(p.amount) total"
          + "      FROM payment p INNER JOIN customer c"
          + "      ON p.customer_id = c.customer_id"
          + "      GROUP BY p.customer_id"
          + "      HAVING sum(p.amount) > =180" // 180 까지
          + "      ORDER BY SUM(p.amount) DESC "; // LIMIT 0,10 ->10등까지
    try {
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       while(rs.next()) {
          Map<String, Object> m = new HashMap<>();
          m.put("customerId",rs.getInt("customerId"));
          m.put("name",rs.getString("name"));
          m.put("total",rs.getInt("total"));
          list.add(m);
       }
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          rs.close();
          stmt.close();
          conn.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return list;
 }

public List<Map<String, Object>> filmCountByRentalRate() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    conn = DBUtil.getConnection();
    String sql = "SELECT rental_rate rentalRate, "
    		+ "COUNT(*) rentalRateCount"
    		+ "FROM film"
    		+ "GROUP BY rental_rate"
    		+ "ORDER BY COUNT(*) DESC"; 
    try {
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       while(rs.next()) {
          Map<String, Object> m = new HashMap<>();
          m.put("rentalRate",rs.getDouble("rentalRate"));
          m.put("rentalRateCount",rs.getInt("rentalRateCount"));
          list.add(m);
       }
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          rs.close();
          stmt.close();
          conn.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return list;
 }

public List<Map<String, Object>> filmCountByRating() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    conn = DBUtil.getConnection();
    String sql = "SELECT rating rating, "
    		+ "COUNT(*) ratingCount"
    		+ "FROM film"
    		+ "GROUP BY rating"
    		+ "ORDER BY COUNT(*) DESC";
    try {
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       while(rs.next()) {
          Map<String, Object> m = new HashMap<>();
          m.put("rating",rs.getInt("rating"));
          m.put("ratingCount",rs.getInt("ratingCount"));
          list.add(m);
       }
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          rs.close();
          stmt.close();
          conn.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return list;
 }
public List<Map<String, Object>> languageFilmCount() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    conn = DBUtil.getConnection();
    String sql = "SELECT l.name name,"
    		+ "			 COUNT(*) languageCount"
    		+ "FROM film f INNER JOIN language l"
    		+ "ON f.language_id = i.language_id"
    		+ "GROUP BY l.name";
    try {
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       while(rs.next()) {
          Map<String, Object> m = new HashMap<>();
          m.put("name",rs.getString("name"));
          m.put("languageCount",rs.getInt("languageCount"));
          list.add(m);
       }
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          rs.close();
          stmt.close();
          conn.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return list;
 }
public List<Map<String, Object>> lengthFilmCount() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    conn = DBUtil.getConnection();
    String sql = "SELECT t.length2 length2,"
    		+ "				COUNT(*) lengthCount"
    		+ "FROM (SELECT title, LENGTH,"
    		+ "			CASE WHEN LENGTH<=60 THEN 'less60'"
    		+ "				 WHEN LENGTH BETWEEN 61 AND 120 THEN 'between61and120'"
    		+ "				 WHEN LENGTH BERWWEN 121 AND 180 THEN 'between121and180'"
    		+ "			 ELSE 'more180'"
    		+ "			END LENGTH2"
    		+ "		FROM film) t"
    		+ "GROUP BY t.length2";
    try {
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       while(rs.next()) {
          Map<String, Object> m = new HashMap<>();
          m.put("length2",rs.getInt("length2"));
          m.put("lengthCount",rs.getInt("lengthCount"));
          list.add(m);
       }
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          rs.close();
          stmt.close();
          conn.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return list;
 }
public List<Map<String, Object>> amount() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    conn = DBUtil.getConnection();
    String sql = "SELECT SUM(amount) sum"
    		+ "FROM payment";
    try {
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       while(rs.next()) {
          Map<String, Object> m = new HashMap<>();
          m.put("sum",rs.getInt("sum"));
          list.add(m);
       }
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          rs.close();
          stmt.close();
          conn.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return list;
 }
public List<Map<String, Object>> salesByStaff() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    conn = DBUtil.getConnection();
    String sql = "SELECT staff_id staffId, SUM(amount) sum"
    		+ "FROM payment"
    		+ "GROUP BY staff_id";
    try {
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       while(rs.next()) {
          Map<String, Object> m = new HashMap<>();
          m.put("staffId",rs.getInt("staffId"));
          m.put("sum",rs.getInt("sum"));
          list.add(m);
       }
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          rs.close();
          stmt.close();
          conn.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return list;
 }
public List<Map<String, Object>> annualSalesBystaff() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    conn = DBUtil.getConnection();
    String sql = "SELECT staff_id staffId, YEAR(payment_date) year, SUM(amount) sum"
    		+ "FROM payment\r\n"
    		+ "GROUP BY staff_id, YEAR(payment_date)";
    try {
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       while(rs.next()) {
          Map<String, Object> m = new HashMap<>();
          m.put("staffId",rs.getInt("staffId"));
          m.put("sum",rs.getInt("year"));
          m.put("sum",rs.getInt("sum"));
          list.add(m);
       }
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          rs.close();
          stmt.close();
          conn.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return list;
 }
public List<Map<String, Object>> salesByStore() {
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    conn = DBUtil.getConnection();
    String sql = "SELECT t.category_id categoryId, c.name name, t.cnt cnt"
    		+ "FROM (SELECT category_id, COUNT(*) cnt"
    		+ "      FROM film_category"
    		+ "      GROUP BY category_id"
    		+ "      ORDER BY COUNT(*) DESC) t"
    		+ "      INNER JOIN  "
    		+ "      category c"
    		+ "      ON t.category_id = c.category_id"
    		+ "ORDER BY t.cnt DESC";
    try {
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       while(rs.next()) {
          Map<String, Object> m = new HashMap<>();
          m.put("storeId",rs.getInt("categoryId"));
          m.put("sum",rs.getString("name"));
          m.put("cnt",rs.getInt("cnt"));
          list.add(m);
       }
    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       try {
          rs.close();
          stmt.close();
          conn.close();
       } catch (SQLException e) {
          e.printStackTrace();
       }
    }
    return list;
 }
}