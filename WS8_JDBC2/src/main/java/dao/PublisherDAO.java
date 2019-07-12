package dao;

import entity.Publisher;
import java.sql.SQLException;
import java.util.ArrayList;

public class PublisherDAO extends DAO{
    
    public ArrayList<Publisher> getAllPublishers(){
        ArrayList<Publisher> list = new ArrayList<>();
        String getAllQuery = "select * from publisher";
        openConnection();
        try {
            ps = con.prepareStatement(getAllQuery);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int pubId = rs.getInt(1);
            
                String pubName = rs.getString(2);
          
                String addr = rs.getString(3);
              
                Publisher pub = new Publisher(pubId, pubName, addr);
            
                list.add(pub);
            
            }
            
        } catch (SQLException ex) {
            System.out.println("Error when get all publishers");
        }
        finally{
            closeConnection();
           
        }
      
        return list;
    }
    
    public int findIndex(String name){
        ArrayList<Publisher> publishers = getAllPublishers();
        for (int i = 0; i < publishers.size(); i++) {
            if(name.equals(publishers.get(i).getName())){
                return i;
            }
        }
        return -1;
    }
}
