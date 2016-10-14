package Classes;

import javax.swing.UnsupportedLookAndFeelException;

import Classes.data;
import View.menuWindow;

public class principal {
	
	public static void main(String[] args){
		data dataFiles = new data();
		menuWindow menu = new menuWindow(dataFiles);
		
		try {
			if(checkClasses()){
				dataFiles.Inicialize();
				menu.Inicialize();
				menu.setVisible(true);
			}
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private static boolean checkClasses() throws UnsupportedLookAndFeelException{
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(directory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		return true;
	}

}
