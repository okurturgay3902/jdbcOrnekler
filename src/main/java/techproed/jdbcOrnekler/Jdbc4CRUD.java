package techproed.jdbcOrnekler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Jdbc4CRUD {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "Melih.11");
				
		Statement st=  con.createStatement();	
		
		// 3. YONTEM
		//-----------------------------------------------------
		// batch metoduyla birlikte PreparedStatement kullanmak en efektif yontemdir.
		// bir sonraki ornekte bunu gerceklestirecegiz.	
		
		
		
		/*=======================================================================
		  ORNEK1: urunler adinda bir tablo olusturalim id int, 
		  isim VARCHAR(10) fiyat int 
		========================================================================*/ 
	
		/*
		st.execute("CREATE TABLE urunler("
	       + " id int,"
	       + " isim VARCHAR(10),"
	       + " fiyat double)");
		System.out.println("tablo olusturuldu");
		*/
		
		/*=======================================================================
		  ORNEK2: urunler tablosuna asagidaki kayitlari toplu bir sekilde ekleyelim
		========================================================================*/ 
		
		// Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir. 
		// PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari icin) bir yontemdir. 
		// Bunun icin; 
		// 	1) Veri girisine uygun bir POJO(Plain Old Java Object) sinifi olusturulur.
		// 	2) POJO Class nesnelerini saklayacak bir collection olusturulur
		// 	3) bir dongu ile kayitlar eklenir. 

		/*
		List<Urun> kayitlar=new ArrayList<>();

		kayitlar.add(new Urun(101,"laptop", 6500));
		kayitlar.add(new Urun(102,"PC", 4500));
		kayitlar.add(new Urun(103,"Telefon", 4500));
		kayitlar.add(new Urun(104,"Anakart", 1500));
		kayitlar.add(new Urun(105,"Klavye", 200));
		kayitlar.add(new Urun(106,"Fare", 100));
		
		PreparedStatement veri=con.prepareStatement("INSERT INTO urunler VALUES(?,?,?)");
		
		// guvenli veri gondermek icin ? leri yukardaki verilerin yerine tanimliyor
		
		for (Urun each : kayitlar) {
			veri.setInt(1, each.getId());
			veri.setString(2, each.getIsim());
			veri.setDouble(3, each.getFiyat());
			
			veri.addBatch(); //Hepsini toparla tek veri haline getir
		}
		
		veri.executeBatch(); // database yolla
		
		System.out.println("veriler yollandi");
		*/
		
		/*=======================================================================
		  ORNEK3: urunler tablosundaki PC'nin fiyatini %10 zam yapiniz
		========================================================================*/
		
	/*	
	int s1= st.executeUpdate("update urunler set fiyat=fiyat*1.1 where isim= 'PC' ");
	
	System.out.println(s1+ " fiyat guncellendi");
	*/
	
	/*=======================================================================
	  ORNEK4: urunler tablosuna Marka adinda ve Default degeri ASUS olan yeni 
	  bir sutun ekleyiniz.
	========================================================================*/
		
	/*	
	st.executeUpdate(" alter table urunler add marka VARCHAR(10) DEFAULT 'ASUS'");
	
	System.out.println("yeni sutun eklendi");
	*/	
		
		
		/*=======================================================================
		  ORNEK5: urunler tablosunu siliniz.
		========================================================================*/ 	
		
		st.execute("DROP TABLE urunler");
		
        System.out.println("Silindi");
		
        con.close();
        st.close();
		
	}

}
