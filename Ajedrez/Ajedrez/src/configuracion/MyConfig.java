package configuracion;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyConfig {

	private static MyConfig instance = new MyConfig();
	
	private String defaultFile  = "default.properties";
	private String appFile = "app.properties";
	private Properties properties;
	
	private MyConfig() {
		
		Properties defaultProperties = new Properties();
		
		try(FileInputStream fis = new FileInputStream(new File(defaultFile))) {
			
			defaultProperties.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		properties = new Properties(defaultProperties);
		
		try(FileInputStream fis = new FileInputStream(new File(appFile))) {
			
			properties.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static MyConfig getInstance() {
		return instance;
	}
	
	public int getWhiteCellColor() {
		
		return  Integer.parseInt(properties.getProperty("color_celda_blanca"));
		
	}
	
	public void setWhiteCellColor(Color color) {
		
		properties.setProperty("color_celda_blanca",String.valueOf(color.getRGB()));
		guardar();
		
	}
	
	public void setBlackCellColor(Color color) {
		
		properties.setProperty("color_celda_negra",String.valueOf(color.getRGB()));
		guardar();
		
	}
	
	
	public int getBlackCellColor() {
		
		return  Integer.parseInt(properties.getProperty("color_celda_negra"));
		
	}
	
	public int getBorderColorYellow() {
		
		return Integer.parseInt(properties.getProperty("color_resaltar_amarillo"));
		
	}
	
	public int getBorderColorRed() {
		
		return Integer.parseInt(properties.getProperty("color_resaltar_rojo"));
		
	}
	
	public void guardar() {
		
		try(FileOutputStream fos = new FileOutputStream(new File(appFile))) {
			
			properties.store(fos, "Store new Color");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
