package test;

import dao.HistoriquePartieDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;




public class Test {
    public static void main(String[] args) throws SQLException, ParseException {
        Date date=new Date();
        SimpleDateFormat form=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        SimpleDateFormat form1=new SimpleDateFormat("HH:mm:ss");
        Date date1 = null;
        String date2String=null;
        Date date2=null;
       
            date1 = form1.parse("07:16:00");
            date2String = form1.format(date);
            date2=form1.parse(date2String);
        
        long rest=date2.getTime()-date1.getTime();
        long delail=64382;
        System.out.println(rest);
        
    }

}
