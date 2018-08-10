package acc.blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class H2Utils {
	Connection conn;
	
	H2Utils() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			ResultSet rs=conn.getMetaData().getTables(null, null, "POSTS", new String[] {"TABLE"});
			if(rs.next()==false) {
				PreparedStatement pst=conn.prepareStatement("create table posts (ID int primary key auto_increment,Title varchar(100), Body varchar(MAX),Date date Default(getDate()) )");
				pst.execute();
				PreparedStatement ps=conn.prepareStatement("insert into posts(title,body) values (?,?)");
				ps.setString(1, "post1 title");
				ps.setString(2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras feugiat viverra scelerisque. In non lacus vel dolor sodales mattis sit amet eget metus. Fusce ac malesuada lectus. Vestibulum facilisis vitae mauris eget fringilla. Aliquam erat volutpat. Nulla facilisi. Pellentesque sed aliquet lorem. Cras interdum lacus neque, vitae luctus enim vestibulum. ");
				ps.execute();
				ps.setString(1, "post2 title");
				ps.setString(2, "Maecenas sit amet consequat felis. Donec nec metus tempor, varius libero sit amet, ultrices mi. Nulla tempus metus magna, ac tempus nunc dictum non. Duis ut justo posuere, tincidunt ipsum in, posuere turpis. Vestibulum finibus, tortor ut ultrices tincidunt, magna metus ullamcorper.");
				ps.execute();
				ps.setString(1, "post3 title");
				ps.setString(2, "Praesent mollis, sem eget fringilla molestie, ante ante mollis metus, dictum suscipit libero nibh et ante. Nunc eu magna vitae lorem scelerisque dictum. Mauris molestie lacus nec congue feugiat. Integer eu libero et augue mattis molestie et nec magna. Aenean scelerisque, nunc vel auctor luctus, mi purus tincidunt augue, at. ");
				ps.execute();
				conn.close();
			}
			else conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void addPost(String title,String body) throws SQLException{
		conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement ps=conn.prepareStatement("insert into posts(title,body) values (?,?)");
		ps.setString(1, title);
		ps.setString(2, body);
		ps.execute();
		conn.close();
	}
	
	ArrayList<Post> getPosts() throws SQLException {
		conn=DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		ArrayList<Post> arr=new ArrayList<Post>();
		PreparedStatement ps=conn.prepareStatement("Select * from posts");
		ResultSet res=ps.executeQuery();
		while(res.next()) arr.add(new Post(res.getInt("ID"),res.getString("Title"),res.getString("Body"),res.getDate("Date")));
		conn.close();
		return arr;
	}
	
	void deletePostByTitle(String title) throws SQLException {
		conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement ps=conn.prepareStatement("delete from posts where Title=?");
		ps.setString(1, title);
		ps.execute();
		conn.close();
	}
	
	void replacePostByTitle(String title,Post post) throws SQLException {
		conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement ps=conn.prepareStatement("update posts set Title=?, Body=?, Date=? where Title=?");
		ps.setString(1, post.getTitle());
		ps.setString(2, post.getBody());
		ps.setDate(3, post.getDate());
		ps.setString(4, title);
		ps.execute();
		conn.close();
	}

}
