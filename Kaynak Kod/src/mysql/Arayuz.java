package mysql;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Arayuz extends JPanel {

	// işçi eklemesi yapılıcak -----yapıldı
	// menüye isimleri yazıcaz ----- yapıldı
	// açılır bilgi panelini güncelle (çalıştığı yer,çalışma saatleri, satın
	// alımlar) ------yapıldı

	// görsel güzelleştirme
	// yöneticide işletmebilgi update yapılıcak
	// databaseyi doldur

	JFrame frame = new JFrame();
	static int konumx;
	static int konumy;
	static Connection connection;
	static Statement stat;
	static Statement stat2;
	static Statement stat3;

	static Statement stat4;

	static int saat = 10;
	static int dakika = 0;
	static int gun;
	static int ay;
	static int yil;
	static boolean durdur = true;
	static boolean markettecalisiyo = false;
	static boolean magazadacalisiyo = false;
	static boolean emlakcidacalisiyo = false;
	static int alanno = 0;
	static int paradeger;
	static int yemekdeger;
	static int esyadeger;
	static int index;
	static int emlakkomisyonu = 10;
	JFrame frame2 = new JFrame();

	JFrame frame3 = new JFrame();
	JPanel databasegoster = new JPanel();
	JPanel databasebilgidegistir = new JPanel();

	JLabel para = new JLabel();
	JLabel yemek = new JLabel();
	JLabel esya = new JLabel();
	JLabel isim = new JLabel();
	JLabel soyad = new JLabel();
	JLabel kisino = new JLabel();
	static String alanlar[];
	static int no;
	static int satinalmafiyat;

	public Arayuz(JFrame frame, Connection connection, Statement stat) {
		this.frame = frame;

		this.setSize(1440, 768);
		this.setVisible(true);
		this.setLayout(null);
		this.connection = connection;
		this.stat = stat;
		try {
			this.stat2 = connection.createStatement();
			this.stat3 = connection.createStatement();

			this.stat4 = connection.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Arayuz(JFrame frame) {
		this.frame = frame;
		this.setSize(1440, 768);
		this.setVisible(true);
		this.setLayout(null);
	}

	public void AnaEkran() {

//		JButton yonetici = new JButton("Yonetici");
//		yonetici.setBounds(550, 320, 100, 50);
//		this.add(yonetici);
		JButton oyuncu = new JButton("Oyuncu");
		oyuncu.setBounds(650, 320, 100, 50);
		this.setBackground(Color.red);
		this.add(oyuncu);
//		yonetici.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				Arayuz panel = new Arayuz(frame);
//				frame.add(panel);
//				// yonet(panel);
//			}
//		});
		oyuncu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Arayuz panel = new Arayuz(frame);
				frame.add(panel);
				oyun(panel);
				yonet();
			}
		});

	}

	void haritagoster(JPanel panelbilgi, JPanel panel) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] kolonlar = { "AlanNo", "Tur", "SahipNo", "Deger", "EmlakKomisyonu" };

		// JLabel[] labellar = new JLabel[3];
		// JTextField[] textfieldlar = new JTextField[5];
		// JTextField[] oku = new JTextField[3];
		/*
		 * for (int i = 0; i < textfieldlar.length; i++) { JTextField t1 = new
		 * JTextField(); t1.setBounds(130, 20 + (i * 25), 100, 20); panelbilgi.add(t1);
		 * JLabel l1 = new JLabel(kolonlar[i] + " :"); l1.setBounds(20, 20 + (i * 25),
		 * 100, 20); panelbilgi.add(l1);
		 * 
		 * // labellar[i]=l1; textfieldlar[i] = t1;
		 * 
		 * }
		 * 
		 * JButton update = new JButton("UPDATE"); update.setBounds(300, 150, 100, 30);
		 * panelbilgi.add(update);
		 * 
		 * update.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
		 * method stub String sorgu = "Update harita Set Tur = \"" +
		 * textfieldlar[1].getText() + "\" , SahipNo = " + textfieldlar[2].getText() +
		 * " Where AlanNo = " + textfieldlar[0].getText(); Db.update(sorgu);
		 * System.out.println(sorgu); } });
		 */
		Object[] satirlar = new Object[5];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 450);
		panel.add(scrollPane);
		JTable tablo = new JTable();
		model.setColumnIdentifiers(kolonlar);
		tablo.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(tablo);
		try {
			ResultSet tabloveri = stat4.executeQuery("select * from harita");
			while (tabloveri.next()) {
				satirlar[0] = tabloveri.getString("AlanNo");
				satirlar[1] = tabloveri.getString("Tur");
       			satirlar[2] = tabloveri.getString("SahipNo");
				satirlar[3] = tabloveri.getString("Deger");
				satirlar[4] = tabloveri.getString("EmlakKomisyonu");
				model.addRow(satirlar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablo.setModel(model);
	}

	void kisigoster(JPanel panelbilgi, JPanel panel) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] kolonlar = { "No", "Ad", "Soyad", "Sifre", "BaslangicTarihi", "Yemek", "Esya", "Para" };

		JLabel[] labellar = new JLabel[8];
		JTextField[] textfieldlar = new JTextField[8];
		for (int i = 0; i < textfieldlar.length; i++) {
			JTextField t1 = new JTextField();
			t1.setBounds(130, 20 + (i * 25), 100, 20);
			panelbilgi.add(t1);
			JLabel l1 = new JLabel(kolonlar[i] + " :");
			l1.setBounds(20, 20 + (i * 25), 100, 20);
			panelbilgi.add(l1);
			textfieldlar[i] = t1;

		}

		JButton update = new JButton("UPDATE");
		update.setBounds(300, 150, 100, 30);
		panelbilgi.add(update);

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sorgu = "Update kisi Set Ad = \"" + textfieldlar[1].getText() + "\" , Soyad = \""
						+ textfieldlar[2].getText() + " \" , Sifre = \"" + textfieldlar[3].getText()
						+ " \" , BaslangicTarihi " + "=  \"" + textfieldlar[4].getText() + "\" , Yemek = "
						+ textfieldlar[5].getText() + " , Esya = " + textfieldlar[6].getText() + " , Para = "
						+ textfieldlar[7].getText() + " Where No = " + textfieldlar[0].getText();
				Db.update(sorgu);
				System.out.println(sorgu);
			}
		});

		Object[] satirlar = new Object[8];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 450);
		panel.add(scrollPane);
		JTable tablo = new JTable();
		model.setColumnIdentifiers(kolonlar);
		tablo.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(tablo);
		try {
			ResultSet tabloveri = stat4.executeQuery("select * from kisi");
			while (tabloveri.next()) {
				satirlar[0] = tabloveri.getString("No");
				satirlar[1] = tabloveri.getString("Ad");
				satirlar[2] = tabloveri.getString("Soyad");
				satirlar[3] = tabloveri.getString("Sifre");
				satirlar[4] = tabloveri.getString("BaslangicTarihi");
				satirlar[5] = tabloveri.getString("Yemek");
				satirlar[6] = tabloveri.getString("Esya");
				satirlar[7] = tabloveri.getString("Para");
				model.addRow(satirlar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablo.setModel(model);
	}

	void oyungoster(JPanel panelbilgi, JPanel panel) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] kolonlar = { "OyunAlanix", "OyunAlaniy", "MarketKurmaUcreti", "MagazaKurmaUcreti", "EmlakKurmaUcreti",
				"OyunTarihi" };

		JLabel[] labellar = new JLabel[6];
		JTextField[] textfieldlar = new JTextField[6];
		for (int i = 0; i < textfieldlar.length; i++) {
			JTextField t1 = new JTextField();
			t1.setBounds(130, 20 + (i * 25), 100, 20);
			panelbilgi.add(t1);
			JLabel l1 = new JLabel(kolonlar[i] + " :");
			l1.setBounds(20, 20 + (i * 25), 100, 20);
			panelbilgi.add(l1);
			textfieldlar[i] = t1;

		}

		JButton update = new JButton("UPDATE");
		update.setBounds(300, 150, 100, 30);
		panelbilgi.add(update);

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sorgu = "Update oyun set OyunAlanix = " + textfieldlar[0].getText() + ", OyunAlaniy = "
						+ textfieldlar[1].getText() + " , MarketKurmaUcreti = " + textfieldlar[2].getText()
						+ " , MagazaKurmaUcreti = " + textfieldlar[3].getText() + " , EmlakKurmaUcreti =  "
						+ textfieldlar[4].getText() + " , OyunTarihi = \"" + textfieldlar[5].getText() + "\"";
				Db.update(sorgu);
				System.out.println(sorgu);
			}
		});

		Object[] satirlar = new Object[6];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 450);
		panel.add(scrollPane);
		JTable tablo = new JTable();
		model.setColumnIdentifiers(kolonlar);
		tablo.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(tablo);
		try {
			ResultSet tabloveri = stat4.executeQuery("select * from oyun");
			while (tabloveri.next()) {
				satirlar[0] = tabloveri.getString("OyunAlanix");
				satirlar[1] = tabloveri.getString("OyunAlaniy");
				satirlar[2] = tabloveri.getString("MarketKurmaUcreti");
				satirlar[3] = tabloveri.getString("MagazaKurmaUcreti");
				satirlar[4] = tabloveri.getString("EmlakKurmaUcreti");
				satirlar[5] = tabloveri.getString("OyunTarihi");
				model.addRow(satirlar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablo.setModel(model);
	}

	void baslangicgoster(JPanel panelbilgi, JPanel panel) {

		DefaultTableModel model = new DefaultTableModel();
		Object[] kolonlar = { "Yemek", "Esya", "Para" };

		JLabel[] labellar = new JLabel[3];
		JTextField[] textfieldlar = new JTextField[3];
		for (int i = 0; i < textfieldlar.length; i++) {
			JTextField t1 = new JTextField();
			t1.setBounds(130, 20 + (i * 25), 100, 20);
			panelbilgi.add(t1);
			JLabel l1 = new JLabel(kolonlar[i] + " :");
			l1.setBounds(20, 20 + (i * 25), 100, 20);
			panelbilgi.add(l1);
			textfieldlar[i] = t1;

		}

		JButton update = new JButton("UPDATE");
		update.setBounds(300, 150, 100, 30);
		panelbilgi.add(update);

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sorgu = "Update baslangic set Yemek = " + textfieldlar[0].getText() + ", Esya = "
						+ textfieldlar[1].getText() + ", Para = " + textfieldlar[2].getText();
				Db.update(sorgu);
				// System.out.println(sorgu);
			}
		});

		Object[] satirlar = new Object[3];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 450);
		panel.add(scrollPane);
		JTable tablo = new JTable();
		model.setColumnIdentifiers(kolonlar);
		tablo.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(tablo);
		try {
			ResultSet tabloveri = stat4.executeQuery("select * from baslangic");
			while (tabloveri.next()) {
				satirlar[0] = tabloveri.getString("Yemek");
				satirlar[1] = tabloveri.getString("Esya");
				satirlar[2] = tabloveri.getString("Para");
				model.addRow(satirlar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablo.setModel(model);
	}

	void calismadurumugoster(JPanel panelbilgi, JPanel panel) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] kolonlar = { "No", "CalismaBaslangicTarihi", "BitisTarihi", "CalismaBaslangicSaati",
				"CalismaBitisSaati", "CalistigiAlanNo", "GunlukUcret" };

		/*
		 * JLabel[] labellar = new JLabel[7]; JTextField[] textfieldlar = new
		 * JTextField[7]; for (int i = 0; i < textfieldlar.length; i++) { JTextField t1
		 * = new JTextField(); t1.setBounds(130, 20 + (i * 25), 100, 20);
		 * panelbilgi.add(t1); JLabel l1 = new JLabel(kolonlar[i] + " :");
		 * l1.setBounds(20, 20 + (i * 25), 100, 20); panelbilgi.add(l1); }
		 */

		Object[] satirlar = new Object[7];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 450);
		panel.add(scrollPane);
		JTable tablo = new JTable();
		model.setColumnIdentifiers(kolonlar);
		tablo.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(tablo);
		try {
			ResultSet tabloveri = stat4.executeQuery("select * from calismadurumu");
			while (tabloveri.next()) {
				satirlar[0] = tabloveri.getString("No");
				satirlar[1] = tabloveri.getString("CalismaBaslangicTarihi");
				satirlar[2] = tabloveri.getString("BitisTarihi");
				satirlar[3] = tabloveri.getString("CalismaBaslangicSaati");
				satirlar[4] = tabloveri.getString("CalismaBitisSaati");
				satirlar[5] = tabloveri.getString("CalistigiAlanNo");
				satirlar[6] = tabloveri.getString("GunlukUcret");
				model.addRow(satirlar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablo.setModel(model);
	}

	void giderlergoster(JPanel panelbilgi, JPanel panel) {

		DefaultTableModel model = new DefaultTableModel();
		Object[] kolonlar = { "No", "Yemek", "Esya", "Para" };

		JLabel[] labellar = new JLabel[4];
		JTextField[] textfieldlar = new JTextField[4];
		for (int i = 0; i < textfieldlar.length; i++) {
			JTextField t1 = new JTextField();
			t1.setBounds(130, 20 + (i * 25), 100, 20);
			panelbilgi.add(t1);
			JLabel l1 = new JLabel(kolonlar[i] + " :");
			l1.setBounds(20, 20 + (i * 25), 100, 20);
			panelbilgi.add(l1);
			textfieldlar[i] = t1;

		}

		JButton update = new JButton("UPDATE");
		update.setBounds(300, 150, 100, 30);
		panelbilgi.add(update);

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sorgu = "Update giderler set Yemek = " + textfieldlar[1].getText() + ", Esya = "
						+ textfieldlar[2].getText() + ", " + "Para = " + textfieldlar[1].getText() + " Where No = "
						+ textfieldlar[0].getText();
				Db.update(sorgu);
				System.out.println(sorgu);
			}
		});

		Object[] satirlar = new Object[4];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 450);
		panel.add(scrollPane);
		JTable tablo = new JTable();
		model.setColumnIdentifiers(kolonlar);
		tablo.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(tablo);
		try {
			ResultSet tabloveri = stat4.executeQuery("select * from giderler");
			while (tabloveri.next()) {
				satirlar[0] = tabloveri.getString("No");
				satirlar[1] = tabloveri.getString("Yemek");
				satirlar[2] = tabloveri.getString("Esya");
				satirlar[3] = tabloveri.getString("Para");
				model.addRow(satirlar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablo.setModel(model);

	}

	void kiraliktablosugoster(JPanel panelbilgi, JPanel panel) {

		// panelbilgi.setBackground(Color.yellow);

		DefaultTableModel model = new DefaultTableModel();
		Object[] kolonlar = { "AlanNo", "BaslangicTarihi", "BitisTarihi" };

		/*
		 * JLabel[] labellar = new JLabel[3]; JTextField[] textfieldlar = new
		 * JTextField[3]; for (int i = 0; i < textfieldlar.length; i++) { JTextField t1
		 * = new JTextField(); t1.setBounds(130, 20 + (i * 25), 100, 20);
		 * panelbilgi.add(t1); JLabel l1 = new JLabel(kolonlar[i] + " :");
		 * l1.setBounds(20, 20 + (i * 25), 100, 20); panelbilgi.add(l1); }
		 */

		Object[] satirlar = new Object[3];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 450);
		panel.add(scrollPane);
		JTable tablo = new JTable();
		model.setColumnIdentifiers(kolonlar);
		tablo.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(tablo);
		try {
			ResultSet tabloveri = stat4.executeQuery("select * from kiralıktablosu");
			while (tabloveri.next()) {
				satirlar[0] = tabloveri.getString("AlanNo");
				satirlar[1] = tabloveri.getString("BaslangicTarihi");
				satirlar[2] = tabloveri.getString("BitisTarihi");

				model.addRow(satirlar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablo.setModel(model);

	}

	void isletmebilgigoster(JPanel panelbilgi, JPanel panel) {
		

		DefaultTableModel model = new DefaultTableModel();
		Object[] kolonlar = { "AlanNo", "KiralikFiyat", "UrunUcreti", "Seviye", "Kapasite", "CalisanSayisi",
				"SabitGelir", "GelirOrani", "YoneticiUcreti", "KullaniciUcreti", "BirkisilikUcretOdemesi",
				"SuankiSahipNo", "TamKapasiteGun" };
		
		Object [] kolonlar2 = { "AlanNo", "KiralikFiyat", "UrunUcreti", "SabitGelir", "GelirOrani", "YoneticiUcreti", "KullaniciUcreti", "BirkisilikUcretOdemesi" };

		JLabel[] labellar = new JLabel[8];
		JTextField[] textfieldlar = new JTextField[8];
		for (int i = 0; i < textfieldlar.length; i++) {
			JTextField t1 = new JTextField();
			t1.setBounds(130, 20 + (i * 25), 100, 20);
			panelbilgi.add(t1);
			JLabel l1 = new JLabel(kolonlar2[i] + " :");
			l1.setBounds(20, 20 + (i * 25), 100, 20);
			panelbilgi.add(l1);
			textfieldlar[i] = t1;
		}
		
		JButton update = new JButton("UPDATE");
		update.setBounds(300, 150, 100, 30);
		panelbilgi.add(update);

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sorgu = "Update ısletmebilgi Set KiralikFiyat = "+textfieldlar[1].getText()+" , UrunUcreti = "+textfieldlar[2].getText()+" , SabitGelir = "+textfieldlar[3].getText()+" ,  GelirOrani = "+textfieldlar[4].getText()+" , YoneticiUcreti = "+textfieldlar[5].getText()+" , KullaniciUcreti = "+textfieldlar[6].getText()+" , BirkisilikUcretOdemesi = "+textfieldlar[7].getText()+" Where AlanNo = " + textfieldlar[0].getText();
				Db.update(sorgu);
				System.out.println(sorgu);
			}
		});
		
		

		Object[] satirlar = new Object[13];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 450);
		panel.add(scrollPane);
		JTable tablo = new JTable();
		model.setColumnIdentifiers(kolonlar);
		tablo.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(tablo);
		try {
			ResultSet tabloveri = stat4.executeQuery("select * from ısletmebilgi");
			while (tabloveri.next()) {
				satirlar[0] = tabloveri.getString("AlanNo");
				// satirlar[1] = tabloveri.getString("SatınAlmaFiyat");
				satirlar[1] = tabloveri.getString("KiralikFiyat");
				// satirlar[3] = tabloveri.getString("EmlakKomisyonu");
				satirlar[2] = tabloveri.getString("UrunUcreti");
				satirlar[3] = tabloveri.getString("Seviye");
				satirlar[4] = tabloveri.getString("Kapasite");
				satirlar[5] = tabloveri.getString("CalisanSayisi");
				satirlar[6] = tabloveri.getString("SabitGelir");
				satirlar[7] = tabloveri.getString("GelirOrani");
				satirlar[8] = tabloveri.getString("YoneticiUcreti");
				satirlar[9] = tabloveri.getString("KullaniciUcreti");
				satirlar[10] = tabloveri.getString("BirkisilikUcretOdemesi");
				satirlar[11] = tabloveri.getString("SuankiSahipNo");
				satirlar[12] = tabloveri.getString("TamKapasiteGun");
				model.addRow(satirlar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablo.setModel(model);

	}

	void satinalimlargoster(JPanel panelbilgi, JPanel panel) {

		DefaultTableModel model = new DefaultTableModel();
		Object[] kolonlar = { "SatinAlanNo", "SatanNo", "AlinanUrun", "Ucret" };

		// JLabel[] labellar = new JLabel[4];
		// JTextField[] textfieldlar = new JTextField[4];
		/*
		 * for (int i = 0; i < textfieldlar.length; i++) { JTextField t1 = new
		 * JTextField(); t1.setBounds(130, 20 + (i * 25), 100, 20); panelbilgi.add(t1);
		 * JLabel l1 = new JLabel(kolonlar[i] + " :"); l1.setBounds(20, 20 + (i * 25),
		 * 100, 20); panelbilgi.add(l1); textfieldlar[i] = t1;
		 * 
		 * }
		 */

		/*
		 * JButton update = new JButton("UPDATE"); update.setBounds(300, 150, 100, 30);
		 * panelbilgi.add(update);
		 * 
		 * update.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
		 * method stub String sorgu = "Update satinalimlar set SatinAlanNo = " +
		 * textfieldlar[0].getText() + ", SatanNo = " + textfieldlar[1].getText() + ", "
		 * + "AlinanUrun = " + textfieldlar[2].getText() + " , Ucret = "
		 * +textfieldlar[3].getText(); Db.update(sorgu); System.out.println(sorgu); }
		 * });
		 */

		Object[] satirlar = new Object[4];
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 450);
		panel.add(scrollPane);
		JTable tablo = new JTable();
		model.setColumnIdentifiers(kolonlar);
		tablo.setBounds(200, 200, 200, 200);
		scrollPane.setViewportView(tablo);
		try {
			ResultSet tabloveri = stat4.executeQuery("select * from satinalimlar");
			while (tabloveri.next()) {
				satirlar[0] = tabloveri.getString("SatinAlanNo");
				satirlar[1] = tabloveri.getString("SatanNo");
				satirlar[2] = tabloveri.getString("AlinanUrun");
				satirlar[3] = tabloveri.getString("Ucret");
				model.addRow(satirlar);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablo.setModel(model);

	}

	void yonet() {
		// JFrame frameyonetici = new JFrame();
		frame3.setSize(1440, 768);
		frame3.setLayout(null);
		frame3.setExtendedState(JFrame.ICONIFIED);
		String a[] = { "baslangic", "calismadurumu", "giderler", "harita", "kiraliktablosu", "kisi", "oyun",
				"satinalimlar", "isletmebilgi" };
		JComboBox box = new JComboBox(a);
		box.setBounds(100, 30, 150, 30);
		frame3.add(box);
		JButton goster = new JButton("Goster");
		goster.setBounds(300, 30, 80, 30);
		frame3.add(goster);

		databasegoster.setBounds(20, 100, 800, 450);
		databasegoster.setBackground(Color.blue);
		frame3.add(databasegoster);

		databasebilgidegistir.setBounds(850, 100, 500, 450);
		databasebilgidegistir.setBackground(Color.yellow);
		frame3.add(databasebilgidegistir);

		goster.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tut = "" + box.getSelectedItem();
				System.out.println(tut);
				if (tut.equals("baslangic")) {
					databasegoster.removeAll();
					databasebilgidegistir.removeAll();
					baslangicgoster(databasebilgidegistir, databasegoster);
					databasebilgidegistir.repaint();
				} else if (tut.equals("calismadurumu")) {
					databasegoster.removeAll();
					databasebilgidegistir.removeAll();
					calismadurumugoster(databasebilgidegistir, databasegoster);
					databasebilgidegistir.repaint();
				} else if (tut.equals("giderler")) {
					databasegoster.removeAll();
					databasebilgidegistir.removeAll();
					giderlergoster(databasebilgidegistir, databasegoster);
					databasebilgidegistir.repaint();
				} else if (tut.equals("harita")) {
					databasegoster.removeAll();
					databasebilgidegistir.removeAll();
					haritagoster(databasebilgidegistir, databasegoster);
					databasebilgidegistir.repaint();
				} else if (tut.equals("kiraliktablosu")) {
					databasegoster.removeAll();
					databasebilgidegistir.removeAll();
					kiraliktablosugoster(databasebilgidegistir, databasegoster);
					databasebilgidegistir.repaint();
				} else if (tut.equals("kisi")) {
					databasegoster.removeAll();
					databasebilgidegistir.removeAll();
					kisigoster(databasebilgidegistir, databasegoster);
					databasebilgidegistir.repaint();
				} else if (tut.equals("oyun")) {
					databasegoster.removeAll();
					databasebilgidegistir.removeAll();
					oyungoster(databasebilgidegistir, databasegoster);
					databasebilgidegistir.repaint();
				} else if (tut.equals("isletmebilgi")) {
					databasegoster.removeAll();
					System.out.println("Removeladik");
					databasebilgidegistir.removeAll();
					isletmebilgigoster(databasebilgidegistir, databasegoster);
					databasebilgidegistir.repaint();
				} else if (tut.equals("satinalimlar")) {
					databasegoster.removeAll();
					databasebilgidegistir.removeAll();
					satinalimlargoster(databasebilgidegistir, databasegoster);
					databasebilgidegistir.repaint();
				}

			}
		});

		/*
		 * JPanel databasegoster = new JPanel();
		 * databasegoster.setBounds(600,100,500,350);
		 * databasegoster.setBackground(Color.blue); frameyonetici.add(databasegoster);
		 */

		JLabel tarihyazisi = new JLabel("Gün giriniz (Örneğin : 10) ");
		tarihyazisi.setBounds(50, 560, 150, 35);
		frame3.add(tarihyazisi);

		JTextField tarih = new JTextField();
		tarih.setBounds(50, 600, 150, 35);
		frame3.add(tarih);

		JButton ilerle = new JButton("Tarihi Ilerlet");
		ilerle.setBounds(250, 600, 150, 35);
		frame3.add(ilerle);

		ilerle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int x = Integer.parseInt(tarih.getText());
				for (int i = 0; i < x; i++) {
					guncelle1(new JPanel());
					sabitgelirekle();
					isciucreti();
					kirakontrol();
					gun++;
					System.out.println(ay + 1);
				}

			}
		});

		databasebilgidegistir.setLayout(null);
		databasegoster.setLayout(null);
		frame3.setVisible(true);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@SuppressWarnings("deprecation")
	void oyun(JPanel panel) {
		this.setVisible(false);
		JPanel login = new JPanel();
		login.setBackground(Color.yellow);
		login.setBounds(0, 0, 300, 150);
		panel.add(login);
		JTextField id = new JTextField("id");
		JTextField sifre = new JTextField("sifre");
		id.setBounds(20, 50, 110, 20);
		sifre.setBounds(20, 80, 110, 20);
		login.add(id);
		login.add(sifre);
		JButton giris = new JButton("Giriş");
		giris.setBounds(180, 62, 80, 30);
		login.add(giris);
		login.setLayout(null);
		panel.setLayout(null);
		JPanel oyunpaneli = new JPanel();
		panel.add(oyunpaneli);
		oyunpaneli.setBackground(Color.blue);
		oyunpaneli.setBounds(0, 200, 1000, 500);
		oyunpaneli.setVisible(false);
		JLabel zamanlabel = new JLabel();
		JLabel saatlabel = new JLabel();
		JLabel dakikalabel = new JLabel();
		giris.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				JButton oyuncubilgileri = new JButton("Oyuncu Bilgileri");
				oyuncubilgileri.setBounds(25, 75, 150, 30);
				panel.add(oyuncubilgileri);

				oyuncubilgileri.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame a = new JFrame();
						a.setSize(1400, 700);
						a.setLayout(null);
						try {
							ResultSet rseren = stat4.executeQuery("select * from giderler Where No = " + no);
							rseren.next();
							JLabel yemekgideri = new JLabel("Günlük Yemek Gideri : " + rseren.getInt("Yemek"));
							yemekgideri.setBounds(0, 0, 200, 30);
							a.add(yemekgideri);
							JLabel esyagideri = new JLabel("Günlük Esya Gideri : " + rseren.getInt("Esya"));
							esyagideri.setBounds(0, 20, 200, 30);
							a.add(esyagideri);
							JLabel paragideri = new JLabel("Günlük Para Gideri : " + rseren.getInt("Para"));
							paragideri.setBounds(0, 40, 200, 30);
							a.add(paragideri);

							ResultSet l = stat4.executeQuery("select * from calismadurumu Where No = " + no);

							if (l.next()) {

								JLabel l0 = new JLabel("Çalışma Bilgisi :");
								l0.setBounds(850, 0, 200, 30);
								a.add(l0);

								JLabel l1 = new JLabel("Çalışılan Alan No : " + l.getString("CalistigiAlanNo"));
								l1.setBounds(850, 30, 200, 30);
								a.add(l1);

								JLabel l2 = new JLabel("İşe Başlangıç Saati : " + l.getString("CalismaBaslangicSaati"));
								l2.setBounds(850, 60, 200, 30);
								a.add(l2);

								JLabel l3 = new JLabel("İşten Çıkış Saati : " + l.getString("CalismaBitisSaati"));
								l3.setBounds(850, 90, 200, 30);
								a.add(l3);

								JLabel l4 = new JLabel("Alınan Maaş : " + l.getString("GunlukUcret"));
								l4.setBounds(850, 120, 200, 30);
								a.add(l4);

								JLabel l5 = new JLabel("Kontrat Bilgisi :");
								l5.setBounds(850, 150, 200, 30);
								a.add(l5);

								JLabel l6 = new JLabel(
										"İşe Başlangıç Tarihi : " + l.getString("CalismaBaslangicTarihi"));
								l6.setBounds(850, 180, 200, 30);
								a.add(l6);

								JLabel l7 = new JLabel("Kontrat Bilgisi :" + l.getString("BitisTarihi"));
								l7.setBounds(850, 210, 200, 30);
								a.add(l7);

							} else {
								JLabel l0 = new JLabel("Çalışma Bilgisi :");
								l0.setBounds(850, 0, 200, 30);
								a.add(l0);

								JLabel l1 = new JLabel("Şu anda çalışılmıyor");
								l1.setBounds(850, 30, 200, 30);
								a.add(l1);

							}

							//
							System.out.println("No : " + no);
							ResultSet rseren2 = stat4
									.executeQuery("select AlanNo,Tur from harita Where SahipNo = " + no);
							// rseren2.next();
							// System.out.println(rseren2.getString("AlanNo"));
							String k = "";
							int i19 = 0;
							JLabel SahipOlunanArsalar = new JLabel("Sahip Olunan Mülkler :");
							SahipOlunanArsalar.setBounds(600, 0, 200, 30);
							a.add(SahipOlunanArsalar);
							while (rseren2.next()) {
								k = "AlanNo : " + rseren2.getString("AlanNO") + "  Tür : " + rseren2.getString("Tur")
										+ "\n";
								JLabel SahipArsalar = new JLabel(k);
								SahipArsalar.setBounds(600, 30 + (i19 * 30), 200, 30);
								a.add(SahipArsalar);
								i19++;
							}

							ArrayList<Integer> kiranotut = new ArrayList<Integer>();
							ResultSet kiravarmi = stat4.executeQuery("select * from kiralıktablosu");
							while (kiravarmi.next()) {
								kiranotut.add(kiravarmi.getInt("AlanNo"));
							}

							ResultSet kiravarmi2 = stat4
									.executeQuery("select * from ısletmebilgi where SuankiSahipNo = " + no);
							while (kiravarmi2.next()) {
								for (int i = 0; i < kiranotut.size(); i++) {
									if (kiranotut.get(i) == kiravarmi2.getInt("AlanNo"))
										;
									{
										JLabel yaz = new JLabel("Kiralık Arsa No : " + kiranotut.get(i));
										yaz.setBounds(600, 30 + (i19 * 30), 200, 30);
										a.add(yaz);
										i19++;
										break;
									}
								}
							}

							//

							DefaultTableModel model = new DefaultTableModel();
							Object[] kolonlar = { "SatinAlanNo", "SatanNo", "AlinanUrun", "Ucret" };

							JLabel[] labellar = new JLabel[4];
							JTextField[] textfieldlar = new JTextField[4];
							/*
							 * for (int i = 0; i < textfieldlar.length; i++) { JTextField t1 = new
							 * JTextField(); t1.setBounds(130, 20 + (i * 25), 100, 20); a.add(t1); JLabel l1
							 * = new JLabel(kolonlar[i] + " :"); l1.setBounds(20, 20 + (i * 25), 100, 20);
							 * a.add(l1); textfieldlar[i] = t1;
							 * 
							 * }
							 */

							Object[] satirlar = new Object[4];
							JScrollPane scrollPane = new JScrollPane();
							scrollPane.setBounds(10, 100, 500, 350);
							a.add(scrollPane);
							JTable tablo = new JTable();
							model.setColumnIdentifiers(kolonlar);
							tablo.setBounds(200, 200, 200, 200);
							scrollPane.setViewportView(tablo);
							try {
								ResultSet tabloveri = stat4.executeQuery(
										"select * from satinalimlar Where SatinAlanNo = " + no + " || SatanNo = " + no);
								while (tabloveri.next()) {
									satirlar[0] = tabloveri.getString("SatinAlanNo");
									satirlar[1] = tabloveri.getString("SatanNo");
									satirlar[2] = tabloveri.getString("AlinanUrun");
									satirlar[3] = tabloveri.getString("Ucret");
									model.addRow(satirlar);
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							tablo.setModel(model);

							//

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						/*
						 * JLabel yemekgideri = new JLabel("Günlük Yemek Gideri : "); JLabel
						 * yiyecekgideri = new JLabel(); JLabel paragideri = new JLabel();
						 */

						a.setVisible(true);

					}
				});

//				String a = "select * from kisi WHERE ad = \"" + id.getText() + "\"" ;
				try {
//					ResultSet rs = stat.executeQuery(a);
//					rs.next();
//					if(sifre.getText().equals(rs.getString("Sifre")))
//					{
					login.setVisible(false);
					oyunpaneli.setVisible(true);
					ResultSet rs2 = stat.executeQuery("select * from oyun");
					ResultSet rs3 = stat2.executeQuery("select Tur from harita");
					ResultSet rs4 = stat3.executeQuery("SELECT * FROM kisi Where Ad = \"Eren\"");

					rs4.next();
					rs3.next();
					rs2.next();
					paradeger = Integer.parseInt(rs4.getString("Para"));
					esyadeger = Integer.parseInt(rs4.getString("Esya"));
					yemekdeger = Integer.parseInt(rs4.getString("Yemek"));
					isim.setText("Ad : " + rs4.getString("Ad"));
					soyad.setText("Soyad : " + rs4.getString("Soyad"));
					kisino.setText("No : " + rs4.getString("No"));
					para.setText("Para : " + paradeger);
					esya.setText("Eşya : " + esyadeger);
					yemek.setText("Yemek : " + yemekdeger);

					no = Integer.parseInt(rs4.getString("No"));

					isim.setBounds(0, 0, 100, 50);
					soyad.setBounds(200, 0, 100, 50);
					kisino.setBounds(400, 0, 100, 50);
					para.setBounds(0, 25, 100, 50);
					esya.setBounds(200, 25, 100, 50);
					yemek.setBounds(400, 25, 100, 50);

					panel.add(isim);
					panel.add(soyad);
					panel.add(kisino);
					panel.add(para);
					panel.add(esya);
					panel.add(yemek);

					int x = Integer.parseInt(rs2.getString("OyunAlanix"));
					int y = Integer.parseInt(rs2.getString("OyunAlaniy"));
					int k = 0; // alanların sırasını tutmak için
					alanlar = new String[x * y]; // yer ataması
					JButton[][] butonlar = new JButton[x][y];
//						System.out.println(rs3.getString(1));
					oyunpaneli.setLayout(new GridLayout(x, y));
					for (int i = 0; i < x; i++) {
						for (int j = 0; j < y; j++) {
							JButton b = new JButton();
							butonlar[i][j] = b;
							alanlar[k] = rs3.getString("Tur") + " - " + (k + 1);
							k++;
							if (rs3.getString("Tur").equals("market")) {
								butonlar[i][j].setIcon(new ImageIcon(new ImageIcon(("E:\\İndirilenler\\food.png"))
										.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
							}
							if (rs3.getString("Tur").equals("magaza")) {
								butonlar[i][j].setIcon(new ImageIcon(new ImageIcon(("E:\\İndirilenler\\store.png"))
										.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
							}
							if (rs3.getString("Tur").equals("emlak")) {
								butonlar[i][j].setIcon(new ImageIcon(new ImageIcon(("E:\\İndirilenler\\realestate.png"))
										.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
							}
							if (rs3.getString("Tur").equals("arsa")) {
								butonlar[i][j].setIcon(new ImageIcon(new ImageIcon(("E:\\İndirilenler\\for-sale.png"))
										.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
							}

							b.setText(rs3.getString("Tur"));
							rs3.next();
							oyunpaneli.add(b);

						}
					}
//					}
					JButton yukarı = new JButton();
					JButton asagi = new JButton();
					JButton sag = new JButton();
					JButton sol = new JButton();
					JButton enter = new JButton();
					JButton durbuton = new JButton("Durdur");
					JButton ilerlebuton = new JButton("İlerle");
					JTextField ilerledeger = new JTextField();

//					for (int i = 0; i < x; i++) {
//						for (int j = 0; j < y; j++) {
//							System.out.println(butonlar[i][j].getText());
//						}
//					}

					frame2.setSize(1024, 768);
					frame2.setLayout(null);
					yukarı.setText("↑");
					asagi.setText("↓");
					sag.setText("→");
					sol.setText("←");
					enter.setText("⏎");
					ilerledeger.setBounds(1200, 215, 80, 25);
					ilerlebuton.setBounds(1300, 200, 80, 50);
					durbuton.setBounds(1300, 250, 80, 50);
					yukarı.setBounds(1100, 550, 50, 50);
					enter.setBounds(1100, 600, 50, 50);
					asagi.setBounds(1100, 650, 50, 50);
					sag.setBounds(1150, 600, 50, 50);
					sol.setBounds(1050, 600, 50, 50);

					panel.add(ilerlebuton);
					panel.add(ilerledeger);
					panel.add(durbuton);
					panel.add(yukarı);
					panel.add(asagi);
					panel.add(sag);
					panel.add(sol);
					panel.add(enter);
					panel.repaint();

					butonlar[0][0].setBackground(Color.orange);
					ilerlebuton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub

							int ilerial = Integer.parseInt(ilerledeger.getText());
							saat += ilerial;
						}
					});
					durbuton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (durdur == false)
								durdur = true;
							else
								durdur = false;
						}
					});
					yukarı.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (konumx > 0) {
								butonlar[konumx][konumy].setBackground(new JButton().getBackground());
								konumx--;
								butonlar[konumx][konumy].setBackground(Color.orange);
							}

						}
					});
					asagi.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (konumx < x - 1) {
								butonlar[konumx][konumy].setBackground(new JButton().getBackground());
								konumx++;
								butonlar[konumx][konumy].setBackground(Color.orange);
							}

						}
					});

					sag.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (konumy < y - 1) {
								butonlar[konumx][konumy].setBackground(new JButton().getBackground());
								konumy++;
								butonlar[konumx][konumy].setBackground(Color.orange);
							}

						}
					});
					sol.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (konumy > 0) {
								butonlar[konumx][konumy].setBackground(new JButton().getBackground());
								konumy--;
								butonlar[konumx][konumy].setBackground(Color.orange);
							}

						}
					});
					enter.addActionListener(new ActionListener() {
						// UPDATE kisi set Yemek = dasdas,Esya = asdasd Where No = no;
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							ResultSet rssaat;
							int baslangicsaati = 24;
							int bitissaati = 0;
							try {
								rssaat = stat.executeQuery(
										"SELECT CalismaBaslangicSaati,CalismaBitisSaati FROM calismadurumu WHERE No = "
												+ no);
								boolean calisiyormu = rssaat.next();

								if (calisiyormu == true) {
									baslangicsaati = rssaat.getInt("CalismaBaslangicSaati");
									bitissaati = rssaat.getInt("CalismaBitisSaati");
								} else {
									baslangicsaati = 24;
									bitissaati = 0;
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							if (butonlar[konumx][konumy].getText().equals("market")) {

								if (saat < bitissaati && saat >= baslangicsaati)// calisma saatlerine göre duzenlenecek
								{
									JOptionPane.showMessageDialog(panel,
											"Şu anda çalışmaktasınız, markete gidemezsiniz!");
								} else {
									frame2.removeAll();
									frame2 = new JFrame();
									frame2.setSize(1024, 768);
									frame2.setLayout(null);
									frame2.setTitle("Market");
									frame2.setVisible(true);
									alanno = (konumy + 1) + (y * konumx);
									try {
										ResultSet rsoyun = stat.executeQuery(
												"SELECT UrunUcreti,BirKisilikUcretOdemesi,CalisanSayisi,Kapasite FROM ısletmebilgi WHERE AlanNo = "
														+ alanno);
										rsoyun.next();
										int urunucreti, gunlukucret, calisansayisi, kapasite;
										urunucreti = rsoyun.getInt("UrunUcreti");
										gunlukucret = rsoyun.getInt("BirKisilikUcretOdemesi");
										calisansayisi = rsoyun.getInt("CalisanSayisi");
										kapasite = rsoyun.getInt("Kapasite");

//											ImagePanel resim = new ImagePanel(
//												new ImageIcon("D:\\İndirilenler\\marketf.jpg")
//														.getImage());
//										resim.setBounds(0, 0, 1024, 768);
//										frame2.add(resim);
										JLabel gunlukucretlabel = new JLabel("Günlük Ücret: " + gunlukucret);
										JLabel urunucretilabel = new JLabel("Ürün ücreti : " + urunucreti);
										JLabel calisansayisilabel = new JLabel("Çalışan sayısı : " + calisansayisi);
										JButton urunalb = new JButton("Ürün al");
										JButton isegir = new JButton("İşe gir");

										calisansayisilabel.setBounds(800, 300, 150, 20);
										urunucretilabel.setBounds(420, 350, 100, 50);
										isegir.setBounds(800, 180, 80, 30);
										gunlukucretlabel.setBounds(800, 120, 100, 50);
										urunalb.setBounds(420, 400, 80, 30);

										frame2.add(calisansayisilabel);
										frame2.add(isegir);
										frame2.add(gunlukucretlabel);
										frame2.add(urunalb);
										frame2.add(urunucretilabel);
										urunalb.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												if (paradeger >= urunucreti) {
													paradeger -= urunucreti;
													yemekdeger += 1;
													para.setText("Para : " + paradeger);
													yemek.setText("Yemek : " + yemekdeger);
													// Arsanın sahibine para ekleme
													try {
														ResultSet rs5 = stat.executeQuery(
																"Select SahipNo FROM Harita Where AlanNo = " + alanno);
														rs5.next();
														Db.update("UPDATE kisi set Para = Para + " + urunucreti
																+ " Where No = "
																+ Integer.parseInt(rs5.getString("SahipNo")));
													} catch (SQLException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
													Db.update("UPDATE kisi set Yemek = " + yemekdeger + " , Para = "
															+ paradeger + " Where No = " + no);

												}

											}
										});
										isegir.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												try {
													ResultSet rs6 = stat.executeQuery(
															"Select No From calismadurumu Where No = " + no);
													boolean isempty = rs6.next();
													if (isempty == false) {
														if (calisansayisi == kapasite) {
															JOptionPane.showMessageDialog(panel,
																	"İşletme tam kapasite çalışmaktadır...");
														} else {
															ResultSet rs7 = stat2.executeQuery(
																	"Select BirKisilikUcretOdemesi From ısletmebilgi Where AlanNo = "
																			+ alanno);
															rs7.next();
															double ortucret = rs7.getInt("BirKisilikUcretOdemesi");
															double rand = (int) (Math.random() * 20) + 1;
															String gunsayisi = JOptionPane
																	.showInputDialog("Kaç gün çalışmak istiyorsunuz?"); // degistirilecek
															String calismabassaati = JOptionPane.showInputDialog(
																	"Saat kaçta çalışmaya başlamak istiyorsunuz?");
															String calismabitsaati = JOptionPane.showInputDialog(panel,
																	"Saat kaça kadar?");
															int bas = Integer.parseInt(calismabassaati);
															int bit = Integer.parseInt(calismabitsaati);
															int fark = bit - bas; // calismasaati
															boolean gir = false;
															if (fark > 8) {
																ortucret = ortucret + (ortucret * (rand / 100));
																int ortucret2 = (int) ortucret;

																int sonuc = JOptionPane.showConfirmDialog(panel,
																		"İşveren fazla çalıştığınız için bonuslarla "
																				+ ortucret2
																				+ " para teklif ediyor kabul ediyor musunuz?",
																		"İşveren Teklifi", JOptionPane.YES_NO_OPTION);
																if (sonuc == JOptionPane.YES_OPTION) {
																	gir = true;
																} else if (sonuc == JOptionPane.NO_OPTION) {
																	gir = false;
																}
															} else {
																ortucret = ortucret - (ortucret * (rand / 100));
																int ortucret2 = (int) ortucret;
																int sonuc = JOptionPane.showConfirmDialog(panel,
																		"İşveren az çalıştığınız için " + ortucret2
																				+ " para teklif ediyor kabul ediyor musunuz?",
																		"İşveren Teklifi", JOptionPane.YES_NO_OPTION);
																if (sonuc == JOptionPane.YES_OPTION) {
																	gir = true;
																} else if (sonuc == JOptionPane.NO_OPTION) {
																	gir = false;
																}
															}

															if (gir) { // kabul et etme
																int ortucret2 = (int) ortucret;
																int moday = Integer.parseInt(gunsayisi) / 30;
																int kalangun = Integer.parseInt(gunsayisi) % 30;
																Date bastarih = new Date(yil, ay, gun);
																Date bittarih = new Date(yil, ay + moday,
																		gun + kalangun);
																System.out.println(bastarih);
																System.out.println(bittarih);
																String query = "Insert INTO calismadurumu Values(" + no
																		+ " , \"" + bastarih + "\" , \"" + bittarih
																		+ "\" , " + bas + " , " + bit + " , " + alanno
																		+ " , " + ortucret2 + " )";
																System.out.println(query); // SET GLOBAL sql_mode = '';
																							// bu
																							// sorguyla sqlde tarih
																							// hatası
																							// giderildi
																Db.update(
																		"UPDATE ısletmebilgi set Calisansayisi = CalisanSayisi + 1 Where AlanNo = "
																				+ alanno);
																Db.insert(query);
																calisansayisilabel.setText(
																		"Çalışan sayısı : " + (calisansayisi + 1));
																magazadacalisiyo = true;
															}

														}

													} else {
														JOptionPane.showMessageDialog(panel,
																"Halihazırda zaten çalışmaktasınız...");
													}

												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}

											}
										});

									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							} else if (butonlar[konumx][konumy].getText().equals("magaza")) {
								if (saat < bitissaati && saat >= baslangicsaati) {
									JOptionPane.showMessageDialog(panel,
											"Şu anda çalışmaktasınız, mağazaya gidemezsiniz!");
								} else {
									frame2.removeAll();
									frame2 = new JFrame();
									frame2.setSize(1024, 768);
									frame2.setLayout(null);
									frame2.setTitle("Magaza");
									frame2.setVisible(true);
									alanno = (konumy + 1) + (y * konumx);
									try {
										ResultSet rsoyun = stat.executeQuery(
												"SELECT UrunUcreti,BirKisilikUcretOdemesi,CalisanSayisi,Kapasite FROM ısletmebilgi WHERE AlanNo = "
														+ alanno);
										rsoyun.next();
										int urunucreti, gunlukucret, calisansayisi, kapasite;
										urunucreti = rsoyun.getInt("UrunUcreti");
										gunlukucret = rsoyun.getInt("BirKisilikUcretOdemesi");
										calisansayisi = rsoyun.getInt("CalisanSayisi");
										kapasite = rsoyun.getInt("Kapasite");
//											ImagePanel resim = new ImagePanel(
//												new ImageIcon("D:\\İndirilenler\\marketf.jpg")
//														.getImage());
//										resim.setBounds(0, 0, 1024, 768);
//										frame2.add(resim);
										JLabel gunlukucretlabel = new JLabel("Günlük Ücret: " + gunlukucret);
										JLabel urunucretilabel = new JLabel("Ürün ücreti : " + urunucreti);
										JLabel calisansayisilabel = new JLabel("Çalışan sayısı : " + calisansayisi);
										JButton urunalb = new JButton("Ürün al");
										JButton isegir = new JButton("İşe gir");

										calisansayisilabel.setBounds(800, 300, 150, 20);
										urunucretilabel.setBounds(420, 350, 100, 50);
										isegir.setBounds(800, 180, 80, 30);
										gunlukucretlabel.setBounds(800, 120, 100, 50);
										urunalb.setBounds(420, 400, 80, 30);

										frame2.add(calisansayisilabel);
										frame2.add(isegir);
										frame2.add(gunlukucretlabel);
										frame2.add(urunalb);
										frame2.add(urunucretilabel);
										urunalb.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												if (paradeger >= urunucreti) {
													paradeger -= urunucreti;
													esyadeger += 1;
													para.setText("Para : " + paradeger);
													esya.setText("Eşya : " + esyadeger);
													// Arsanın sahibine para ekleme
													try {
														ResultSet rs5 = stat.executeQuery(
																"Select SahipNo FROM Harita Where AlanNo = " + alanno);
														rs5.next();
														Db.update("UPDATE kisi set Para = Para + " + urunucreti
																+ " Where No = "
																+ Integer.parseInt(rs5.getString("SahipNo")));
													} catch (SQLException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
													Db.update("UPDATE kisi set Esya = " + esyadeger + " , Para = "
															+ paradeger + " Where No = " + no);

												}

											}
										});
										isegir.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												try {
													ResultSet rs6 = stat.executeQuery(
															"Select No From calismadurumu Where No = " + no);
													boolean isempty = rs6.next();
													if (isempty == false) {
														if (calisansayisi == kapasite) {
															JOptionPane.showMessageDialog(panel,
																	"İşletme tam kapasite çalışmaktadır...");
														} else {
															ResultSet rs7 = stat2.executeQuery(
																	"Select BirKisilikUcretOdemesi From ısletmebilgi Where AlanNo = "
																			+ alanno);
															rs7.next();
															double ortucret = rs7.getInt("BirKisilikUcretOdemesi");
															double rand = (int) (Math.random() * 20) + 1;
															String gunsayisi = JOptionPane
																	.showInputDialog("Kaç gün çalışmak istiyorsunuz?"); // degistirilecek
															String calismabassaati = JOptionPane.showInputDialog(
																	"Saat kaçta çalışmaya başlamak istiyorsunuz?");
															String calismabitsaati = JOptionPane.showInputDialog(panel,
																	"Saat kaça kadar?");
															int bas = Integer.parseInt(calismabassaati);
															int bit = Integer.parseInt(calismabitsaati);
															int fark = bit - bas; // calismasaati
															boolean gir = false;
															if (fark > 8) {
																ortucret = ortucret + (ortucret * (rand / 100));
																int ortucret2 = (int) ortucret;

																int sonuc = JOptionPane.showConfirmDialog(panel,
																		"İşveren fazla çalıştığınız için bonuslarla "
																				+ ortucret2
																				+ " para teklif ediyor kabul ediyor musunuz?",
																		"İşveren Teklifi", JOptionPane.YES_NO_OPTION);
																if (sonuc == JOptionPane.YES_OPTION) {
																	gir = true;
																} else if (sonuc == JOptionPane.NO_OPTION) {
																	gir = false;
																}
															} else {
																ortucret = ortucret - (ortucret * (rand / 100));
																int ortucret2 = (int) ortucret;
																int sonuc = JOptionPane.showConfirmDialog(panel,
																		"İşveren az çalıştığınız için " + ortucret2
																				+ " para teklif ediyor kabul ediyor musunuz?",
																		"İşveren Teklifi", JOptionPane.YES_NO_OPTION);
																if (sonuc == JOptionPane.YES_OPTION) {
																	gir = true;
																} else if (sonuc == JOptionPane.NO_OPTION) {
																	gir = false;
																}
															}

															if (gir) { // kabul et etme
																int ortucret2 = (int) ortucret;
																int moday = Integer.parseInt(gunsayisi) / 30;
																int kalangun = Integer.parseInt(gunsayisi) % 30;
																Date bastarih = new Date(yil, ay, gun);
																Date bittarih = new Date(yil, ay + moday,
																		gun + kalangun);
																System.out.println(bas
);
																System.out.println(bittarih);
																String query = "Insert INTO calismadurumu Values(" + no
																		+ " , \"" + bastarih + "\" , \"" + bittarih
																		+ "\" , " + bas + " , " + bit + " , " + alanno
																		+ " , " + ortucret2 + " )";
																System.out.println(query); // SET GLOBAL sql_mode = '';
																							// bu
																							// sorguyla sqlde tarih
																							// hatası
																							// giderildi
																Db.update(
																		"UPDATE ısletmebilgi set Calisansayisi = CalisanSayisi + 1 Where AlanNo = "
																				+ alanno);
																Db.insert(query);
																calisansayisilabel.setText(
																		"Çalışan sayısı : " + (calisansayisi + 1));
																magazadacalisiyo = true;
															}

														}

													} else {
														JOptionPane.showMessageDialog(panel,
																"Halihazırda zaten çalışmaktasınız...");
													}

												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}

											}
										});

									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

								}
							} else if (butonlar[konumx][konumy].getText().equals("emlak")) {
								if (saat < bitissaati && saat >= baslangicsaati) {
									JOptionPane.showMessageDialog(panel,
											"Şu anda çalışmaktasınız, emlağa gidemezsiniz!");
								} else {
									frame2.removeAll(); // removeall sıkıntı çıkarttı yeniden jframe classını kullanmak
														// durumunda kaldım
									frame2 = new JFrame();
									frame2.setSize(1024, 768);
									frame2.setLayout(null);

									frame2.setTitle("Emlak");
									frame2.setVisible(true);
									alanno = (konumy + 1) + (y * konumx);
									ResultSet rsoyun;
									try {
										rsoyun = stat.executeQuery(
												"SELECT AlanNo,KiralikFiyat,Kapasite,CalisanSayisi,BirKisilikUcretOdemesi FROM ısletmebilgi"); // haritadan
																																				// satinalma
																																				// oku
										rsoyun.next();
										ResultSet rsemlak = stat2.executeQuery(
												"SELECT BirKisilikUcretOdemesi,CalisanSayisi,Kapasite FROM ısletmebilgi WHERE AlanNo = "
														+ alanno);
										rsemlak.next();
										ResultSet rsharita = stat3
												.executeQuery("Select * From harita Where AlanNo = " + 1);
										rsharita.next();

										int kiralikfiyat, calisansayisi, kapasite, gunlukucret;
										int emlakcalisansayisi, emlakkapasite, emlakgunlukucret;
										JComboBox satilanlarjc = new JComboBox(alanlar);

										gunlukucret = rsoyun.getInt("BirKisilikUcretOdemesi");
										calisansayisi = rsoyun.getInt("CalisanSayisi");
										kapasite = rsoyun.getInt("Kapasite"); // şu anlık bu kapasite ile işimiz yok

										emlakgunlukucret = rsemlak.getInt("BirKisilikUcretOdemesi");
										emlakcalisansayisi = rsemlak.getInt("CalisanSayisi");
										emlakkapasite = rsemlak.getInt("Kapasite");

										satinalmafiyat = rsharita.getInt("Deger");
										kiralikfiyat = rsoyun.getInt("KiralikFiyat");

//									JLabel alannolabel = new JLabel("Alan No : 1");
										JLabel satinalma = new JLabel("Satin alma fiyati : " + satinalmafiyat);
										JLabel kiralama = new JLabel("Kiralama ücreti : " + kiralikfiyat);
										JLabel calisansayisilabel = new JLabel("Çalışan sayısı : " + calisansayisi);
										JLabel gunlukucretlabel = new JLabel("Ortalama günlük ücret : " + gunlukucret);
										JLabel emlakcalisansayisilabel = new JLabel(
												"Emlakta çalışan sayısı : " + emlakcalisansayisi);
										JLabel emlakgunlukucretlabel = new JLabel(
												"Emlakta ortalama günlük ücret : " + emlakgunlukucret);

										JButton satinalb = new JButton("Satın al");
										JButton kiralab = new JButton("Kirala");
										JButton isegir = new JButton("İşe gir");

//									alannolabel.setBounds(200, 400, 100, 20);
										satilanlarjc.setBounds(300, 400, 100, 20);
										emlakcalisansayisilabel.setBounds(700, 300, 200, 20);
										isegir.setBounds(700, 180, 80, 30);
										emlakgunlukucretlabel.setBounds(700, 120, 250, 50);
										satinalb.setBounds(400, 460, 80, 30);
										kiralab.setBounds(400, 510, 80, 30);
										satinalma.setBounds(200, 450, 200, 50);
										kiralama.setBounds(200, 500, 150, 50);
										gunlukucretlabel.setBounds(200, 550, 200, 20);
										calisansayisilabel.setBounds(200, 600, 200, 20);

//									frame2.add(alannolabel);
										frame2.add(satilanlarjc);
										frame2.add(isegir);
										frame2.add(calisansayisilabel);
										frame2.add(gunlukucretlabel);
										frame2.add(emlakcalisansayisilabel);
										frame2.add(emlakgunlukucretlabel);
										frame2.add(satinalb);
										frame2.add(kiralab);
										frame2.add(satinalma);
										frame2.add(kiralama);
										// JComoboxla satın alım
										// 2'DEN FAZLA ARSASI OLAMAZ!! control
										isegir.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												try {
													ResultSet rs6 = stat.executeQuery(
															"Select No From calismadurumu Where No = " + no);
													boolean isempty = rs6.next();
													if (isempty == false) {
														if (calisansayisi == kapasite) {
															JOptionPane.showMessageDialog(panel,
																	"İşletme tam kapasite çalışmaktadır...");
														} else {
															ResultSet rs7 = stat2.executeQuery(
																	"Select BirKisilikUcretOdemesi From ısletmebilgi Where AlanNo = "
																			+ alanno);
															rs7.next();
															double ortucret = rs7.getInt("BirKisilikUcretOdemesi");
															double rand = (int) (Math.random() * 20) + 1;
															String gunsayisi = JOptionPane
																	.showInputDialog("Kaç gün çalışmak istiyorsunuz?"); // degistirilecek
															String calismabassaati = JOptionPane.showInputDialog(
																	"Saat kaçta çalışmaya başlamak istiyorsunuz?");
															String calismabitsaati = JOptionPane.showInputDialog(panel,
																	"Saat kaça kadar?");
															int bas = Integer.parseInt(calismabassaati);
															int bit = Integer.parseInt(calismabitsaati);
															int fark = bit - bas; // calismasaati
															boolean gir = false;
															if (fark > 8) {
																ortucret = ortucret + (ortucret * (rand / 100));
																int ortucret2 = (int) ortucret;

																int sonuc = JOptionPane.showConfirmDialog(panel,
																		"İşveren fazla çalıştığınız için bonuslarla "
																				+ ortucret2
																				+ " para teklif ediyor kabul ediyor musunuz?",
																		"İşveren Teklifi", JOptionPane.YES_NO_OPTION);
																if (sonuc == JOptionPane.YES_OPTION) {
																	gir = true;
																} else if (sonuc == JOptionPane.NO_OPTION) {
																	gir = false;
																}
															} else {
																ortucret = ortucret - (ortucret * (rand / 100));
																int ortucret2 = (int) ortucret;
																int sonuc = JOptionPane.showConfirmDialog(panel,
																		"İşveren az çalıştığınız için " + ortucret2
																				+ " para teklif ediyor kabul ediyor musunuz?",
																		"İşveren Teklifi", JOptionPane.YES_NO_OPTION);
																if (sonuc == JOptionPane.YES_OPTION) {
																	gir = true;
																} else if (sonuc == JOptionPane.NO_OPTION) {
																	gir = false;
																}
															}

															if (gir) { // kabul et etme
																int ortucret2 = (int) ortucret;
																int moday = Integer.parseInt(gunsayisi) / 30;
																int kalangun = Integer.parseInt(gunsayisi) % 30;
																Date bastarih = new Date(yil, ay, gun);
																Date bittarih = new Date(yil, ay + moday,
																		gun + kalangun);
																System.out.println(bastarih);
																System.out.println(bittarih);
																String query = "Insert INTO calismadurumu Values(" + no
																		+ " , \"" + bastarih + "\" , \"" + bittarih
																		+ "\" , " + bas + " , " + bit + " , " + alanno
																		+ " , " + ortucret2 + " )";
																System.out.println(query); // SET GLOBAL sql_mode = '';
																							// bu
																							// sorguyla sqlde tarih
																							// hatası
																							// giderildi
																Db.update(
																		"UPDATE ısletmebilgi set Calisansayisi = CalisanSayisi + 1 WHERE AlanNo = "
																				+ alanno);
																Db.insert(query);
																emlakcalisansayisilabel
																		.setText("Emlakta çalışan sayısı : "
																				+ (emlakcalisansayisi + 1));
																magazadacalisiyo = true;
															}

														}

													} else {
														JOptionPane.showMessageDialog(panel,
																"Halihazırda zaten çalışmaktasınız...");
													}

												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}

											}
										});

										satilanlarjc.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												index = satilanlarjc.getSelectedIndex() + 1;

												try {
													ResultSet harita = stat3.executeQuery(
															"SELECT * From harita Where AlanNo = " + index);
													harita.next();
													ResultSet sonuc = stat2.executeQuery(
															"SELECT AlanNo,KiralikFiyat,Kapasite,CalisanSayisi,BirKisilikUcretOdemesi FROM ısletmebilgi WHERE AlanNo = "
																	+ index);
													sonuc.next();
													emlakkomisyonu = harita.getInt("EmlakKomisyonu");

													if (harita.getString("Tur").equals("arsa")) {
														satinalma.setText(
																"Satin alma fiyati : " + harita.getInt("Deger"));
														satinalmafiyat = harita.getInt("Deger");
														kiralab.setVisible(false);
														kiralama.setVisible(false);
														calisansayisilabel.setVisible(false);
														gunlukucretlabel.setVisible(false);
													} else {
														kiralab.setVisible(true);
														kiralama.setVisible(true);
														calisansayisilabel.setVisible(true);
														gunlukucretlabel.setVisible(true);
														satinalmafiyat = harita.getInt("Deger");
														satinalma.setText(
																"Satin alma fiyati : " + harita.getInt("Deger"));
														kiralama.setText(
																"Kiralama fiyati : " + sonuc.getInt("KiralikFiyat"));
														calisansayisilabel.setText(
																"Çalışan sayısı : " + sonuc.getInt("CalisanSayisi"));
													}

												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}

//											satinalma.setText();

											}
										});
										satinalb.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												try {
													int arsasayici = 0;
													ResultSet rs7 = stat3
															.executeQuery("SELECT * From harita Where SahipNo = " + no);
													rs7.next();
													while (rs7.next()) {
														if (rs7.getString("Tur").equals("arsa"))
															arsasayici++;
														System.out.println(arsasayici);
													}

													ResultSet rs6 = stat2.executeQuery(
															"SELECT SahipNo FROM Harita WHERE AlanNo = " + index);
													rs6.next();
													if (rs6.getInt("SahipNo") == no) {
														JOptionPane.showMessageDialog(panel,
																"Bu mülke zaten sahipsiniz!!");
													} else if (arsasayici == 2) {
														JOptionPane.showMessageDialog(panel,
																"2'den fazla arsaya sahip olamazsınız!!");
													} else {
														if (paradeger >= satinalmafiyat) {
															paradeger -= satinalmafiyat;
															para.setText("Para : " + paradeger);

															try {
																ResultSet rs5 = stat.executeQuery(
																		"Select SahipNo FROM Harita Where AlanNo = "
																				+ index);
																rs5.next();
																Db.update("UPDATE kisi set Para = Para + "
																		+ satinalmafiyat + " Where No = "
																		+ Integer.parseInt(rs5.getString("SahipNo")));
															} catch (SQLException e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
															int komisyon = satinalmafiyat * (emlakkomisyonu / 100);
															Db.update("UPDATE kisi set Para = Para + " + komisyon
																	+ " WHERE No = " + alanno);
															Db.update("UPDATE kisi set Para = " + paradeger
																	+ " Where No = " + no);
															Db.update("UPDATE harita set SahipNo = " + no
																	+ " Where AlanNo = " + index);
															Db.update("UPDATE ısletmeBilgi set SuankiSahipNo = " + no
																	+ " Where AlanNo = " + index);

														}
													}
												} catch (SQLException e1) {
													e1.printStackTrace();
												}

											}
										});
										kiralab.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(ActionEvent e) {
												ResultSet rs6;
												try {
													rs6 = stat2.executeQuery(
															"SELECT SuankiSahipNo From ısletmebilgi WHERE AlanNo = "
																	+ index);
													rs6.next();
													if (rs6.getInt("SuankiSahipNo") == no) {
														JOptionPane.showMessageDialog(panel,
																"Bu mülkü zaten kiraladınız!!");
													} else {
														ResultSet rs7 = stat3.executeQuery(
																"SELECT KiralikFiyat From ısletmebilgi WHERE AlanNo = "
																		+ index);
														rs7.next();
														int kiralamafiyati = rs7.getInt("KiralikFiyat");
														String kiralamagunsayisi = JOptionPane.showInputDialog(panel,
																"Kaç günlük kiralamak istiyorsunuz?");
														int kiragunsayisi = Integer.parseInt(kiralamagunsayisi);
														int moday = kiragunsayisi / 30;
														int modgun = kiragunsayisi % 30;

														Db.insert("INSERT INTO kiraliktablosu Values (" + index + " ,"
																+ " \" 2023-" + ay + "-" + gun + "\" , " + " \" 2023-"
																+ (moday + ay) + "-" + (modgun + gun) + "\"" + ")");

														if (paradeger >= kiralamafiyati) {
															paradeger -= kiralamafiyati;
															para.setText("Para : " + paradeger);

															try {
																ResultSet rs5 = stat.executeQuery(
																		"Select SahipNo FROM Harita Where AlanNo = "
																				+ index);
																rs5.next();
																Db.update("UPDATE kisi set Para = Para + "
																		+ kiralamafiyati + " Where No = "
																		+ Integer.parseInt(rs5.getString("SahipNo"))); // Eski
																														// sahibine
																														// para
																														// transferi
															} catch (SQLException e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
															int komisyon = kiralamafiyati * (emlakkomisyonu / 100);
															Db.update("UPDATE kisi set Para = Para + " + komisyon // emlakcı
																													// komisyonu
																	+ " WHERE No = " + alanno);
															Db.update("UPDATE kisi set Para = " + paradeger // bizim
																											// paramız
																	+ " Where No = " + no);
//															Db.update("UPDATE harita set SahipNo = " + no
//																	+ " Where AlanNo = " + index);
															Db.update("UPDATE ısletmeBilgi set SuankiSahipNo = " + no
																	+ " Where AlanNo = " + index);

														}

													}

												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}

											}
										});

									} catch (SQLException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}

								}
							} else if (butonlar[konumx][konumy].getText().equals("arsa")) {

								if (saat < bitissaati && saat >= baslangicsaati)// calisma saatlerine göre duzenlenecek
								{
									JOptionPane.showMessageDialog(panel,
											"Şu anda çalışmaktasınız, markete gidemezsiniz!");
								} else {
									frame2.removeAll();
									frame2 = new JFrame();
									frame2.setSize(1024, 768);
									frame2.setLayout(null);
									alanno = (konumy + 1) + (y * konumx);
									System.out.println(alanno);
									System.out.println(no);
									try {
										ResultSet rs1 = stat
												.executeQuery("SELECT SahipNo From harita Where AlanNo = " + alanno);
										rs1.next();
										ResultSet rs2 = stat3.executeQuery("Select * From oyun");
										rs2.next();
										int marketfiyat = rs2.getInt("MarketKurmaUcreti");
										int magazafiyat = rs2.getInt("MagazaKurmaUcreti");
										int emlakfiyat = rs2.getInt("EmlakKurmaUcreti");
										System.out.println(marketfiyat + magazafiyat + emlakfiyat);
										if (no == rs1.getInt("SahipNo")) {
											System.out.println(rs1.getString("SahipNo"));
											frame2.setTitle("Arsa");
											frame2.setVisible(true);
											JLabel marketyazi = new JLabel(
													"Arsayı Market Yapmak : " + marketfiyat + " para");
											marketyazi.setBounds(70, 70, 200, 30);
											frame2.add(marketyazi);
											JButton market = new JButton("Market");
											market.setBounds(100, 100, 100, 30);
											frame2.add(market);
											JLabel magazayazi = new JLabel(
													"Arsayı Mağaza Yapmak : " + magazafiyat + " para");
											magazayazi.setBounds(370, 70, 200, 30);
											frame2.add(magazayazi);
											JButton magaza = new JButton("Magaza");
											magaza.setBounds(400, 100, 100, 30);
											frame2.add(magaza);
											JLabel emlakyazi = new JLabel(
													"Arsayı Emlak Yapmak : " + emlakfiyat + " para");
											emlakyazi.setBounds(670, 70, 200, 30);
											frame2.add(emlakyazi);
											JButton emlak = new JButton("Emlak");
											emlak.setBounds(700, 100, 100, 30);
											frame2.add(emlak);

											market.addActionListener(new ActionListener() {

												@Override
												public void actionPerformed(ActionEvent e) {
													if (paradeger >= marketfiyat) {
														// TODO Auto-generated method stub

														JFrame bilgigir = new JFrame();
														bilgigir.setSize(400, 300);
														bilgigir.setLayout(null);

														JTextField satinal = new JTextField("İşletmenin fiyatı!!!");
														satinal.setBounds(10, 20, 200, 30);
														bilgigir.add(satinal);

														JTextField kiralik = new JTextField(
																"İşletmenin kiralık fiyatı!!!");
														kiralik.setBounds(10, 60, 200, 30);
														bilgigir.add(kiralik);

														JTextField UrunUcreti = new JTextField(
																"İşletmenin Ürün Ücreti!!!");
														UrunUcreti.setBounds(10, 100, 200, 30);
														bilgigir.add(UrunUcreti);

														JTextField BirkisilikUcretOdemesi = new JTextField(
																"Çalışan Ödemesi!!!");
														BirkisilikUcretOdemesi.setBounds(10, 140, 200, 30);
														bilgigir.add(BirkisilikUcretOdemesi);

														JButton onay = new JButton("ONAYLA");
														onay.setBounds(250, 80, 100, 30);
														bilgigir.add(onay);

														onay.addActionListener(new ActionListener() {

															@Override
															public void actionPerformed(ActionEvent e) {

																paradeger -= marketfiyat;
																para.setText("Para : " + paradeger);
																Db.update("UPDATE kisi set  Para = " + paradeger
																		+ " Where No = " + no);

																// TODO Auto-generated method stub
																System.out.println(
																		"Update harita set Tur = \"market\" , Deger = "
																				+ satinal.getText()
																				+ " , EmlakKomisyonu = 100 Where AlanNo = "
																				+ alanno);
																// INSERT INTO tabloadı (name, email, phone) VALUES
																// ('John
																// Smith','john@example.com', '555-1234');
																System.out.println(
																		"INSERT INTO ısletmebilgi (AlanNo,KiralikFiyat,UrunUcreti,Seviye,Kapasite,CalisanSayisi,SabitGelir,GelirOrani,YoneticiUcreti,KullaniciUcreti,BirkisilikUcretOdemesi,SuankiSahipNo)  VALUES  ( "
																				+ alanno + " , " + kiralik.getText()
																				+ ", " + UrunUcreti.getText()
																				+ ", 1 , 3 , 0 , 100 , 1 , 100 , 100 , "
																				+ BirkisilikUcretOdemesi.getText()
																				+ " , " + no + " ) ");
																Db.insert(
																		"INSERT INTO ısletmebilgi (AlanNo,KiralikFiyat,UrunUcreti,Seviye,Kapasite,CalisanSayisi,SabitGelir,GelirOrani,YoneticiUcreti,KullaniciUcreti,BirkisilikUcretOdemesi,SuankiSahipNo)  VALUES  ( "
																				+ alanno + " , " + kiralik.getText()
																				+ ", " + UrunUcreti.getText()
																				+ ", 1 , 3 , 0 , 100 , 1 , 100 , 100 , "
																				+ BirkisilikUcretOdemesi.getText()
																				+ " , " + no + " ) ");
																Db.update(
																		"Update harita set Tur = \"market\" , Deger = "
																				+ satinal.getText()
																				+ " , EmlakKomisyonu = 100 Where AlanNo = "
																				+ alanno);
																butonlar[konumx][konumy].setText("market");
																butonlar[konumx][konumy]
																		.setIcon(new ImageIcon(new ImageIcon(
																				("E:\\İndirilenler\\for-sale.png"))
																				.getImage().getScaledInstance(150, 150,
																						Image.SCALE_DEFAULT)));
																alanlar[alanno - 1] = "market - " + alanno;
																System.out.println("Girdi");
																bilgigir.setVisible(false);
																frame2.removeAll();
																frame2.setVisible(false);
															}
														});

														bilgigir.setVisible(true);
														// bilgigir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
														// Db.update("Update harita set Tur = \"market\" , Deger = "
														// + deger.getText() + " , EmlakKomisyonu = 100 Where AlanNo = "
														// + alanno);
														// butonlar[konumx][konumy].setText("market");
														// butonlar[konumx][konumy].setIcon(new ImageIcon(new
														// ImageIcon(("E:\\İndirilenler\\for-sale.png")).getImage().getScaledInstance(150,
														// 150, Image.SCALE_DEFAULT)));
													} else {
														JOptionPane.showMessageDialog(null, "Paranız yeterli değil");
													}
												}
											});

											magaza.addActionListener(new ActionListener() {

												@Override
												public void actionPerformed(ActionEvent e) {
													if (paradeger >= magazafiyat) {
														// TODO Auto-generated method stub

														JFrame bilgigir = new JFrame();
														bilgigir.setSize(400, 300);
														bilgigir.setLayout(null);

														JTextField satinal = new JTextField("İşletme fiyatı !!!");
														satinal.setBounds(10, 20, 200, 30);
														bilgigir.add(satinal);

														JTextField kiralik = new JTextField("Kiralık fiyatı!!!");
														kiralik.setBounds(10, 60, 200, 30);
														bilgigir.add(kiralik);

														JTextField UrunUcreti = new JTextField("Ürün Ücreti!!!");
														UrunUcreti.setBounds(10, 100, 200, 30);
														bilgigir.add(UrunUcreti);

														JTextField BirkisilikUcretOdemesi = new JTextField(
																"Çalışan Ödemesi!!!");
														BirkisilikUcretOdemesi.setBounds(10, 140, 200, 30);
														bilgigir.add(BirkisilikUcretOdemesi);

														JButton onay = new JButton("ONAYLA");
														onay.setBounds(250, 80, 100, 30);
														bilgigir.add(onay);

														onay.addActionListener(new ActionListener() {

															@Override
															public void actionPerformed(ActionEvent e) {

																paradeger -= magazafiyat;
																para.setText("Para : " + paradeger);
																Db.update("UPDATE kisi set  Para = " + paradeger
																		+ " Where No = " + no);

																// TODO Auto-generated method stub
																System.out.println(
																		"Update harita set Tur = \"magaza\" , Deger = "
																				+ satinal.getText()
																				+ " , EmlakKomisyonu = 100 Where AlanNo = "
																				+ alanno);
																// INSERT INTO tabloadı (name, email, phone) VALUES
																// ('John
																// Smith','john@example.com', '555-1234');
																System.out.println(
																		"INSERT INTO ısletmebilgi (AlanNo,KiralikFiyat,UrunUcreti,Seviye,Kapasite,CalisanSayisi,SabitGelir,GelirOrani,YoneticiUcreti,KullaniciUcreti,BirkisilikUcretOdemesi,SuankiSahipNo)  VALUES  ( "
																				+ alanno + " , " + kiralik.getText()
																				+ ", " + UrunUcreti.getText()
																				+ ", 1 , 3 , 0 , 100 , 1 , 100 , 100 , "
																				+ BirkisilikUcretOdemesi.getText()
																				+ " , " + no + " ) ");
																Db.insert(
																		"INSERT INTO ısletmebilgi (AlanNo,KiralikFiyat,UrunUcreti,Seviye,Kapasite,CalisanSayisi,SabitGelir,GelirOrani,YoneticiUcreti,KullaniciUcreti,BirkisilikUcretOdemesi,SuankiSahipNo)  VALUES  ( "
																				+ alanno + " , " + kiralik.getText()
																				+ ", " + UrunUcreti.getText()
																				+ ", 1 , 3 , 0 , 100 , 1 , 100 , 100 , "
																				+ BirkisilikUcretOdemesi.getText()
																				+ " , " + no + " ) ");
																Db.update(
																		"Update harita set Tur = \"magaza\" , Deger = "
																				+ satinal.getText()
																				+ " , EmlakKomisyonu = 100 Where AlanNo = "
																				+ alanno);
																butonlar[konumx][konumy].setText("magaza");
																butonlar[konumx][konumy].setIcon(new ImageIcon(
																		new ImageIcon(("D:\\İndirilenler\\store.png"))
																				.getImage().getScaledInstance(150, 150,
																						Image.SCALE_DEFAULT)));
																alanlar[alanno - 1] = "magaza - " + alanno;
																System.out.println("Girdi");
																bilgigir.setVisible(false);
																frame2.removeAll();
																frame2.setVisible(false);

															}
														});

														bilgigir.setVisible(true);
														// bilgigir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
														// Db.update("Update harita set Tur = \"market\" , Deger = "
														// + deger.getText() + " , EmlakKomisyonu = 100 Where AlanNo = "
														// + alanno);
														// butonlar[konumx][konumy].setText("market");
														// butonlar[konumx][konumy].setIcon(new ImageIcon(
														// new ImageIcon(("E:\\İndirilenler\\for-sale.png")).getImage()
														// .getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
													} else {
														JOptionPane.showMessageDialog(null, "Paranız yeterli değil");
													}
												}
											});

											emlak.addActionListener(new ActionListener() {

												@Override
												public void actionPerformed(ActionEvent e) {
													// TODO Auto-generated method stub
													if (paradeger >= emlakfiyat) {

														// TODO Auto-generated method stub

														JFrame bilgigir = new JFrame();
														bilgigir.setSize(400, 300);
														bilgigir.setLayout(null);

														JTextField satinal = new JTextField(
																"İşletmenin fiyatını belirleyiniz!!!");
														satinal.setBounds(10, 20, 200, 30);
														bilgigir.add(satinal);

														JTextField kiralik = new JTextField(
																"İşletmenin kiralık fiyatını belirleyiniz!!!");
														kiralik.setBounds(10, 60, 200, 30);
														bilgigir.add(kiralik);

														/*
														 * JTextField UrunUcreti = new
														 * JTextField("İşletmenin Ürün Ücretini belirleyiniz!!!");
														 * UrunUcreti.setBounds(10, 100, 200, 30);
														 * bilgigir.add(UrunUcreti);
														 */

														JTextField BirkisilikUcretOdemesi = new JTextField(
																"Çalışan Ödemesi!!!");
														BirkisilikUcretOdemesi.setBounds(10, 100, 200, 30);
														bilgigir.add(BirkisilikUcretOdemesi);

														JButton onay = new JButton("ONAYLA");
														onay.setBounds(250, 80, 100, 30);
														bilgigir.add(onay);

														onay.addActionListener(new ActionListener() {

															@Override
															public void actionPerformed(ActionEvent e) {
																paradeger -= emlakfiyat;
																para.setText("Para : " + paradeger);
																Db.update("UPDATE kisi set  Para = " + paradeger
																		+ " Where No = " + no);

																// TODO Auto-generated method stub
																System.out.println(
																		"Update harita set Tur = \"emlak\" , Deger = "
																				+ satinal.getText()
																				+ " , EmlakKomisyonu = 100 Where AlanNo = "
																				+ alanno);
																// INSERT INTO tabloadı (name, email, phone) VALUES
																// ('John
																// Smith','john@example.com', '555-1234');
																System.out.println(
																		"INSERT INTO ısletmebilgi (AlanNo,KiralikFiyat,UrunUcreti,Seviye,Kapasite,CalisanSayisi,SabitGelir,GelirOrani,YoneticiUcreti,KullaniciUcreti,BirkisilikUcretOdemesi,SuankiSahipNo)  VALUES  ( "
																				+ alanno + " , " + kiralik.getText()
																				+ ", " + "0"
																				+ ", 1 , 3 , 0 , 100 , 1 , 100 , 100 , "
																				+ BirkisilikUcretOdemesi.getText()
																				+ " , " + no + " ) ");
																Db.insert(
																		"INSERT INTO ısletmebilgi (AlanNo,KiralikFiyat,UrunUcreti,Seviye,Kapasite,CalisanSayisi,SabitGelir,GelirOrani,YoneticiUcreti,KullaniciUcreti,BirkisilikUcretOdemesi,SuankiSahipNo)  VALUES  ( "
																				+ alanno + " , " + kiralik.getText()
																				+ ", " + "0"
																				+ ", 1 , 3 , 0 , 100 , 1 , 100 , 100 , "
																				+ BirkisilikUcretOdemesi.getText()
																				+ " , " + no + " ) ");
																Db.update("Update harita set Tur = \"emlak\" , Deger = "
																		+ satinal.getText()
																		+ " , EmlakKomisyonu = 100 Where AlanNo = "
																		+ alanno);
																butonlar[konumx][konumy].setText("emlak");
																butonlar[konumx][konumy].setIcon(new ImageIcon(
																		new ImageIcon(("D:\\İndirilenler\\store.png"))
																				.getImage().getScaledInstance(150, 150,
																						Image.SCALE_DEFAULT)));
																alanlar[alanno - 1] = "emlak - " + alanno;
																System.out.println("Girdi");
																bilgigir.setVisible(false);
																frame2.removeAll();
																frame2.setVisible(false);

															}
														});

														bilgigir.setVisible(true);
														// bilgigir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
														// Db.update("Update harita set Tur = \"market\" , Deger = "
														// + deger.getText() + " , EmlakKomisyonu = 100 Where AlanNo = "
														// + alanno);
														// butonlar[konumx][konumy].setText("market");
														// butonlar[konumx][konumy].setIcon(new ImageIcon(
														// new ImageIcon(("E:\\İndirilenler\\for-sale.png")).getImage()
														// .getScaledInstance(150, 150, Image.SCALE_DEFAULT)));

													} else {
														JOptionPane.showMessageDialog(null, "Paranız yeterli değil");
													}
													/*
													 * Db.update( "Update harita set Tur = \"emlak\" , Deger = " +
													 * deger.getText() + " , EmlakKomisyonu = 100 Where AlanNo = " +
													 * alanno);
													 */
												}
											});

										}

									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									// frame2.repaint();

								}

							}

						}
					});

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
//					System.out.println("Hatalı Giriş!");
					e1.printStackTrace();
				}
			}
		});

		Date zaman = new Date(15); // Date günler için,
		
		
		Thread clock = new Thread() { 
			public void run() { 
				try {
					while (true) {

						while (durdur) {

							sleep(500);
							if (dakika == 60) {
								dakika = 0;
								saat++;
							}
							if (saat >= 24) {

								// checkpoint
								guncelle1(panel);
								sabitgelirekle();
								// isciucreti();
								// kirakontrol();
								levelkontrol();

								saat = 0;
								gun++;
								System.out.println(
										"Update oyun set OyunTarihi = \"" + 2023 + "-" + ay + "-" + gun + "\"");
								Db.update("Update oyun set OyunTarihi = \"" + 2023 + "-" + ay + "-" + gun + "\"");
								// checkpoint

								// Çalışma süresi biterse işten çıkacak delete işlemi gerçekleştir
								// Çalıştığı süre zarfında para kazanacak işverenden para düş.
								// kiralama süreleri kontrol
								// sabit gelir ekleme -----Yapıldı

							}
							if (gun == 30) {
								gun = 1;
								ay++;
							}
							if (ay == 12) {
								ay = 1;
								yil++;
							}
							saatlabel.setText(Integer.toString(saat) + "  :");
							dakikalabel.setText(Integer.toString(dakika));
							zamanlabel.setText(2023 + "-" + ay + "-" + gun);
							dakika += 1;
						}

					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		try {
			ResultSet rsgetdate = stat.executeQuery("SELECT OyunTarihi FROM oyun");
			rsgetdate.next();
			zaman = rsgetdate.getDate("OyunTarihi");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gun = zaman.getDate();
		System.out.println(gun);
		ay = zaman.getMonth() + 1;
		System.out.println(ay);
		yil = zaman.getYear();
		System.out.println(yil);

//		System.out.println(zaman.getYear()); // yılı yanlış yazıyo niye anlamadım
		zamanlabel.setBounds(1250, 20, 100, 50);
		saatlabel.setBounds(1150, 20, 100, 50);
		dakikalabel.setBounds(1180, 20, 100, 50);
		panel.add(dakikalabel);
		panel.add(saatlabel);
		panel.add(zamanlabel);
		clock.start();

	}

	public void guncelle1(JPanel panel) {

		int gideryemek, gideresya, giderpara;
		ResultSet rs;
		try {
			rs = stat.executeQuery("SELECT * FROM giderler");

			int i = 1;
			while (rs.next()) {
				ResultSet rs3 = stat3.executeQuery("SELECT CalistigiAlanNo From calismadurumu Where No = " + i);
				boolean isempty = rs3.next();
				gideryemek = rs.getInt("Yemek");
				gideresya = rs.getInt("Esya");
				giderpara = rs.getInt("Para");
				if (isempty == true) {
					int calistigialanno = rs3.getInt("CalistigiAlanNo");
					ResultSet rs4 = stat4.executeQuery("SELECT Tur FROM harita Where AlanNo = " + calistigialanno);
					rs4.next();

					if (rs4.getString("Tur").equals("market"))
						gideryemek = 0;
					else if (rs4.getString("Tur").equals("magaza"))
						gideresya = 0;
					else if (rs4.getString("Tur").equals("emlak"))
						giderpara = 0;

					Db.update("UPDATE kisi set Yemek = Yemek - " + gideryemek + " , Esya = Esya - " + gideresya
							+ " , Para = Para - " + giderpara + " WHERE No = " + i);
					ResultSet rs2 = stat2.executeQuery("Select * From kisi Where No = " + i);
					rs2.next();
					if (i == no) {
						paradeger -= giderpara;
						esyadeger -= gideresya;
						yemekdeger -= gideryemek;
					}
					if (rs2.getInt("Yemek") < 0 || rs2.getInt("Esya") < 0) {
						JOptionPane.showMessageDialog(panel, i + " Numaralı kişi oyundan elenmiştir!!");

						// x numaralı kişinin mülkleri yöneticiye aktarılacaktır.

					}

				} else if (isempty == false) {
					Db.update("UPDATE kisi set Yemek = Yemek - " + gideryemek + " , Esya = Esya - " + gideresya
							+ " , Para = Para - " + giderpara + " WHERE No = " + i);
					ResultSet rs2 = stat2.executeQuery("Select * From kisi Where No = " + i);
					rs2.next();
					if (i == no) {
						paradeger -= giderpara;
						esyadeger -= gideresya;
						yemekdeger -= gideryemek;
					}
					if (rs2.getInt("Yemek") < 0 || rs2.getInt("Esya") < 0) {
						JOptionPane.showMessageDialog(panel, i + " Numaralı kişi oyundan elenmiştir!!");
						// x numaralı kişinin mülkleri yöneticiye aktarılacaktır.
					}
				}
				// Çalışma süresi biterse işten çıkacak delete işlemi gerçekleştir
				// Çalıştığı süre zarfında para kazanacak işverenden para düş.
				// kiralama süreleri kontrol
				// sabit gelir ekleme
				i++;
			}
			para.setText("Para : " + paradeger);
			esya.setText("Eşya : " + esyadeger);
			yemek.setText("Yemek : " + yemekdeger);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sabitgelirekle() {
		ResultSet gelirekle;
		try {
			gelirekle = stat.executeQuery("SELECT SuankiSahipNo,SabitGelir,GelirOrani FROM ısletmebilgi");
			while (gelirekle.next()) {
				System.out.println("Update kisi set Para = Para + (" + gelirekle.getInt("SabitGelir") + "*"
						+ gelirekle.getInt("GelirOrani") + ")  Where No = " + gelirekle.getInt("SuankiSahipNo"));
				Db.update("Update kisi set Para = Para + (" + gelirekle.getInt("SabitGelir") + "*"
						+ gelirekle.getInt("GelirOrani") + ")  Where No = " + gelirekle.getInt("SuankiSahipNo"));
				if (gelirekle.getInt("SuankiSahipNo") == no) {
					paradeger = paradeger + gelirekle.getInt("SabitGelir") * gelirekle.getInt("GelirOrani");
					para.setText("Para : " + paradeger);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void kirakontrol() {
		try {
			ResultSet kirakontrol = stat.executeQuery("SELECT * FROM kiraliktablosu");
			while (kirakontrol.next()) {
				int i = kirakontrol.getInt("AlanNo");
				ResultSet suankisahipkim = stat2
						.executeQuery("Select SuankiSahipNo FROM ısletmebilgi Where AlanNo = " + i);
				suankisahipkim.next();
				ResultSet gerceksahipkim = stat3.executeQuery("SELECT SahipNo FROM harita WHERE AlanNo = " + i);
				gerceksahipkim.next();

				int bitisayi = kirakontrol.getDate("BitisTarihi").getMonth() + 1;
				int bitisgunu = kirakontrol.getDate("BitisTarihi").getDate();
				if (bitisayi == ay && bitisgunu == gun) {
					Db.update("UPDATE ısletmebilgi Set SuankiSahipNo = " + gerceksahipkim.getInt("SahipNo")
							+ " WHERE AlanNo = " + i);
					Db.delete("DELETE FROM kiraliktablosu WHERE AlanNo = " + i);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void isciucreti() // İşten çıkılınca işletmebilgideki işçi sayısı azalt
								// Günlük seviye kontrolü, parasız suriyeli işçi çalıştır
	{ // Yapılan her işlemi satın alma tablosuna ekle
		try {
			ResultSet isciucretiode = stat
					.executeQuery("SELECT CalismaBitisTarihi, CalistigiAlanNo, No,GunlukUcret FROM calismadurumu");
			ArrayList<Integer> z = new ArrayList<Integer>();
			while (isciucretiode.next()) {
				Date zaman = new Date(1); // Date günler için,
				zaman = isciucretiode.getDate("CalismaBitisTarihi");
				System.out.println(gun);
				System.out.println(zaman.getDate());
				System.out.println(ay);
				System.out.println(zaman.getMonth() + 1);

				if (zaman.getDate() == gun && zaman.getMonth() + 1 == ay) {
					System.out.println("Bu kisinin isi bitti silebilirsin");
					z.add(isciucretiode.getInt("No"));
				} else {
					ResultSet x = stat2.executeQuery(
							"SELECT SahipNo FROM harita Where AlanNo= " + isciucretiode.getInt("CalistigiAlanNo"));
					x.next();

					System.out.println("Update kisi Set Para = Para - " + isciucretiode.getInt("GunlukUcret")
							+ " Where No = " + x.getInt("SahipNo"));
					Db.update("Update kisi Set Para = Para - " + isciucretiode.getInt("GunlukUcret") + " Where No = "
							+ x.getInt("SahipNo"));
					System.out.println("Update kisi Set Para = Para + " + isciucretiode.getInt("GunlukUcret")
							+ " Where No = " + isciucretiode.getInt("No"));
					Db.update("Update kisi Set Para = Para + " + isciucretiode.getInt("GunlukUcret") + " Where No = "
							+ isciucretiode.getInt("No"));
					System.out.println();
					//
					/*
					 * y = stat3.executeQuery("SELECT Para FROM kisi Where No = "
					 * +x.getInt("SahipNo")); y.next();
					 */
				}

			}
			for (int i = 0; i < z.size(); i++) {
				System.out.println("DELETE  From calismadurumu  Where No = " + z.get(i));
				Db.delete("DELETE  From calismadurumu  Where No = " + z.get(i));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void levelkontrol() {
		try {
			ResultSet x = stat.executeQuery("Select * From ısletmebilgi ");
			while (x.next()) {
				if (x.getInt("Seviye") == 1) {

					if (x.getInt("CalisanSayisi") == 3) {
						if (x.getInt("TamKapasiteGun") == 6) {
							Db.update(
									"UPDATE ısletmebilgi set Seviye = 2 , Kapasite = 6, GelirOrani = 2 * GelirOrani ,  TamKapasiteGun=0  Where AlanNo ="
											+ x.getInt("AlanNo"));
						} else {
							Db.update("UPDATE ısletmebilgi set TamKapasiteGun = TamKapasiteGun + " + 1
									+ " Where AlanNo =" + x.getInt("AlanNo"));
						}

					} else {
						Db.update("UPDATE ısletmebilgi set TamKapasiteGun = 0 Where AlanNo =" + x.getInt("AlanNo"));
					}
				}

				if (x.getInt("Seviye") == 2) {

					if (x.getInt("CalisanSayisi") == 6) {
						if (x.getInt("TamKapasiteGun") == 6) {
							Db.update(
									"UPDATE ısletmebilgi set Seviye = 3 , Kapasite = 9 , GelirOrani = 2 * GelirOrani ,  TamKapasiteGun=0  Where AlanNo ="
											+ x.getInt("AlanNo"));
						} else {
							Db.update("UPDATE ısletmebilgi set TamKapasiteGun = TamKapasiteGun + " + 1
									+ " Where AlanNo =" + x.getInt("AlanNo"));
						}

					} else {
						Db.update("UPDATE ısletmebilgi set TamKapasiteGun = 0 Where AlanNo =" + x.getInt("AlanNo"));
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

//yeni işletme eklendiğinde seviye ile Kapasiteyi ayarlamayı unutma
