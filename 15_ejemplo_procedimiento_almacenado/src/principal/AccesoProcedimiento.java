package principal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoProcedimiento {

	public static void main(String[] args) {
		String driver="com.mysql.jdbc.Driver";
		//jdbc:mysql://servidor:puerto/basedatos
		String url="jdbc:mysql://localhost:3306/libros";
		String user="root";
		String pwd="root";
		try {
			//carga del driver
			Class.forName(driver);
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
			return;
		}

		//acceso al procedimiento
		String sql="{call librostema(?)}";
		try(Connection con=DriverManager.getConnection(url,user,pwd);){
			CallableStatement prod=con.prepareCall(sql);
			prod.setInt(1, 1);
			ResultSet rs=prod.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("titulo"));
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

}
