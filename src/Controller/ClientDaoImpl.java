package Controller;

import dao.ClientDAO;
import db.DataConnection;
import model.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl  implements ClientDAO {
    Connection cnx=null;
    Statement stm=null;
    ResultSet rs=null;

    public ClientDaoImpl() {
        try {
            cnx = DataConnection.getInstance().getConnection();
            this.stm = this.cnx.createStatement();
            this.rs = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client find(int id) {
        String sql="select *from clients where id="+id;
        try {
            rs=stm.executeQuery(sql);
            if(rs.next()) {
                return new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("telephone"),rs.getString("city"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void create(Client p) {
        String sql="insert into clients(nom,prenom,telephone,city) values('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getTelephone()+"','"+p.getCity()+"')";
        try {
             stm.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Client p) throws SQLException {
        String sql="delete from clients where id="+p.getId();
        try {
             stm.execute(sql);
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void update(Client p) {
        String sql="update clients set nom ='"+p.getNom()+"',prenom='"+p.getPrenom()+"',telephone='"+p.getTelephone()+"',city='"+p.getCity()+"'  where id='"+p.getId()+"' ";
        try {
            if (stm.execute(sql))
                System.out.println("client updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> findAll() {
        String sql="select *from clients";
        ArrayList<Client> c= new ArrayList<>();
        try {
            rs=stm.executeQuery(sql);
            while(rs.next()) {
                c.add(new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("telephone"),rs.getString("city")));
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client findAll(String key) {
        key="%"+key+"%";
        String sql="select *from clients WHERE nom LIKE  '"+key+"' OR  prenom LIKE  '"+key+"' OR telephone LIKE  '"+key+"'";
        try {
            rs=stm.executeQuery(sql);
            while(rs.next()) {
                return new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("telephone"),rs.getString("city"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getLastId() {
        String sql="select MAX(id) from clients";
        try {
            rs=stm.executeQuery(sql);
            if(rs.next()) {
                return rs.getInt(1)+1;
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;

    }
}
